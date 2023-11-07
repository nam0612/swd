package com.fpt.swd.database.dto.Assignment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AddNewAssignmentDto implements Serializable {
    private String name;
    private int subject_id;
    private String description;
    private String created_by;
}
