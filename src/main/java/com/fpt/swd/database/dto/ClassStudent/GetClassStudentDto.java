package com.fpt.swd.database.dto.ClassStudent;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetClassStudentDto implements Serializable {
    private int id;
    private int class_id;
    private int student_id;
    private boolean status;
}
