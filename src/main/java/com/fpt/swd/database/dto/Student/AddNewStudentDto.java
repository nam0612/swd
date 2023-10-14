package com.fpt.swd.database.dto.Student;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class AddNewStudentDto implements Serializable {
    private String name;
    private int gender;
    private boolean status;
    private String created_by;
    private Date created_date;
}
