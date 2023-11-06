package com.fpt.swd.database.dto.Class;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class GetClassVer2Dto implements Serializable {
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
    private String settingCode;
    private String userFirstName;
    private String userLastName;
    private String subjectCode;
    private String subjectName;

    public GetClassVer2Dto(int id, String name, String description, int semester_id, int teacher_id, int subject_id, Date start_date, Date end_date, byte status, String created_by, String updated_by, String settingCode, String userFirstName, String userLastName, String subjectCode, String subjectName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.semester_id = semester_id;
        this.teacher_id = teacher_id;
        this.subject_id = subject_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.settingCode = settingCode;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }
}
