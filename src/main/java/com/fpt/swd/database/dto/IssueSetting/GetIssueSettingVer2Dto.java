package com.fpt.swd.database.dto.IssueSetting;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class GetIssueSettingVer2Dto {
    private int id;
    private int project_id;
    private String project_name;
    private int class_id;
    private String class_name;
    private int subject_id;
    private String subject_name;
    private String description;
    private byte status;
    private String created_by;
    private Date created_date;
    private String updated_by;
    private Date updated_date;
    public GetIssueSettingVer2Dto() {
    }
    public GetIssueSettingVer2Dto(int id, int project_id, String project_name, int class_id, String class_name, int subject_id, String subject_name, String description, byte status, String created_by, Date created_date, String updated_by, Date updated_date) {
        this.id = id;
        this.project_id = project_id;
        this.project_name = project_name;
        this.class_id = class_id;
        this.class_name = class_name;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.description = description;
        this.status = status;
        this.created_by = created_by;
        this.created_date = created_date;
        this.updated_by = updated_by;
        this.updated_date = updated_date;
    }
}

