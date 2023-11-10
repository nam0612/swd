package com.fpt.swd.database.dto.IssueSetting;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class UpdateIssueSettingDto implements Serializable {
    private int id;
    private int project_id;
    private int class_id;
    private int subject_id;
    private String description;
    private String status;
    private String updated_by;
    private Date updated_date;
}
