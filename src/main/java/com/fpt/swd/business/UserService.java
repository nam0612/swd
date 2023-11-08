package com.fpt.swd.business;


import com.fpt.swd.api.request.ChangePasswordRequest;
import com.fpt.swd.api.request.UserDto;
import com.fpt.swd.database.dto.UserDTOVer2;
import com.fpt.swd.database.entity.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    boolean hasUserWithUsername(String username);

    boolean hasUserWithEmail(String email);

    User validateAndGetUserByUsername(String username);

    User saveUser(User user);

    void deleteUser(User user);

    void changePassword(ChangePasswordRequest request);

    UserDTOVer2 updateProfile(UserDTOVer2 userDto, String username);

}
