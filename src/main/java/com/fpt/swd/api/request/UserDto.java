package com.fpt.swd.api.request;

public record UserDto(Long id, String username, String name, String email, int role, int status) {
}