package com.fpt.swd.database.dto.ClassStudent;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetClassStudentVer2Dto implements Serializable {
    private int id;
    private int class_id;
    private int student_id;
    private byte status;
    private String created_by;
    private String updated_by;
    private String className;
    private String userFirstName;
    private String userLastName;

    public GetClassStudentVer2Dto(int id, int class_id, int student_id, byte status, String created_by, String updated_by, String className, String userFirstName, String userLastName) {
        this.id = id;
        this.class_id = class_id;
        this.student_id = student_id;
        this.status = status;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.className = className;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }
}
