package com.fpt.swd.database.dto;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValueDTO implements Serializable {
    private String code;

    private String value;
}
