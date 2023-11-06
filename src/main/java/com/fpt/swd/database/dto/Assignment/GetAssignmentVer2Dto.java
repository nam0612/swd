package com.fpt.swd.database.dto.Assignment;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetAssignmentVer2Dto implements Serializable {
    private int id;
    private String name;
    private int subject_id;
    private String description;
    private String created_by;
    private String updated_by;
    private String subjectCode;
    private String subjectName;

    public GetAssignmentVer2Dto(int id, String name, int subject_id, String description, String created_by, String updated_by, String subjectCode, String subjectName) {
        this.id = id;
        this.name = name;
        this.subject_id = subject_id;
        this.description = description;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }
}
