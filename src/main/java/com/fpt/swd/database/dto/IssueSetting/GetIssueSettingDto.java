package com.fpt.swd.database.dto.IssueSetting;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetIssueSettingDto implements Serializable {
    private int id;
    private int project_id;
    private int class_id;
    private int subject_id;
    private String description;
    private String status;
    private String created_by;
    private String updated_by;
}
