package com.fpt.swd.database.dto.Assignment;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UpdateAssignmentDto implements Serializable {
    private int id;
    private String name;
    private int subject_id;
    private String description;
    private String updated_by;
    private Date updated_date;
}
