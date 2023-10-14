package com.fpt.swd.database.dto.Student;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class GetStudentDto implements Serializable {
    private int id;
    private String name;
    private int gender;
    private boolean status;
}
