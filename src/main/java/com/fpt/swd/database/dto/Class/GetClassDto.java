package com.fpt.swd.database.dto.Class;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class GetClassDto implements Serializable {
    private int id;
    private String name;
    private String description;
    private int semester_id;
    private int teacher_id;
    private int subject_id;
    private Date start_date;
    private Date end_date;
    private byte status;
    private String created_by;
    private String updated_by;
}
