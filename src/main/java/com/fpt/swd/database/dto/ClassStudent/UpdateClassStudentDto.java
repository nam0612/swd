package com.fpt.swd.database.dto.ClassStudent;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class UpdateClassStudentDto implements Serializable {
    private int id;
    private int class_id;
    private int student_id;
    private byte status;
    private String updated_by;
    private Date updated_date;
}
