package com.fpt.swd.business.impl;

import com.fpt.swd.api.request.ChangePasswordRequest;
import com.fpt.swd.api.request.LoginRequest;
import com.fpt.swd.api.request.UserDto;
import com.fpt.swd.business.UserService;
import com.fpt.swd.database.dto.UserDTOVer2;
import com.fpt.swd.database.entity.User;
import com.fpt.swd.database.repo.UserRepository;
import com.fpt.swd.enums.TokenUtils;
import com.fpt.swd.event.OnAccountRecover;
import com.fpt.swd.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;
    private final RecoveryTokenRepository recoveryTokenRepository;
    private final long resetPasswordTokenLifetime = 2;




    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean hasUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User validateAndGetUserByUsername(String username) {
        return getUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with username %s not found", username)));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        Optional<User> userOp = userRepository.findByUsername(request.getUsername());

        if (userOp.isPresent()) {
            throw new IllegalStateException("user name is not existed");
        }

        User user  = userOp.get();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    @Override
    public UserDTOVer2 updateProfile(UserDTOVer2 userDto, String username) {
        User user = validateAndGetUserByUsername(username);
        if(user != null) {
            BeanUtils.copyProperties(userDto, user);
            userRepository.save(user);
            return userDto;
        }
        return null;
    }


    @Override
    @Transactional
    public void recover(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            return;
        recoveryTokenRepository.deleteByUser(user.get());
        createRecoveryTokenForUser(user.get());
        eventPublisher.publishEvent(new OnAccountRecover(user.get()));
    }

    private String createRecoveryTokenForUser(User user) {
        String token = TokenUtils.generateToken();

        RecoveryToken recoveryToken = new RecoveryToken();
        recoveryToken.setToken(token);
        recoveryToken.setUser(user);
        recoveryToken.setValidUntilInEpoch(System.currentTimeMillis() + resetPasswordTokenLifetime);
        user.setRecoveryToken(recoveryToken);
        recoveryTokenRepository.save(recoveryToken);
        return token;
    }

}
