package com.fpt.swd.mapper;


import com.fpt.swd.api.request.UserDto;
import com.fpt.swd.database.entity.User;

public interface UserMapper {

    UserDto toUserDto(User user);
}