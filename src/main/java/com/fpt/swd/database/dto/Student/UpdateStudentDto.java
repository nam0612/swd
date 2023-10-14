package com.fpt.swd.database.dto.Student;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Setter
@Getter
public class UpdateStudentDto implements Serializable {
    private int id;
    private String name;
    private int gender;
    private boolean status;
    private String updated_by;
    private Date updated_date;
}
