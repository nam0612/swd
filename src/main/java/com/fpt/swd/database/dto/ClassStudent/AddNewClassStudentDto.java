package com.fpt.swd.database.dto.ClassStudent;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AddNewClassStudentDto implements Serializable {
    private int class_id;
    private int student_id;
    private byte status;
    private String created_by;
    private Date created_date;
}
