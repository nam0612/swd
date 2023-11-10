package com.fpt.swd.database.dto.milestone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMilstone {
    private String name;
    private String description;
    private Integer projectId;
    private Integer classId;
    private Integer status;
}
