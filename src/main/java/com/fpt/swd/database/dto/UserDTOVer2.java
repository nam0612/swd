package com.fpt.swd.database.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTOVer2 {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
