package com.fpt.swd.mapper;


import com.fpt.swd.api.request.UserDto;
import com.fpt.swd.database.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getUsername(), user.getFirstName() + " " + user.getLastName(), user.getEmail(), user.getRoleId());
    }
}
