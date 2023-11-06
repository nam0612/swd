package com.fpt.swd.database.dto.ClassStudent;

import com.fpt.swd.database.entity.Class;
import com.fpt.swd.database.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class GetClassStudentDto implements Serializable {
    private int id;
    private int class_id;
    private int student_id;
    private byte status;
    private String created_by;
    private String updated_by;

}
