package com.fpt.swd.database.dto;

public record UserDto(Long id, String username, String name, String email, int role) {
}