package com.fpt.swd.database.dto.Issue;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class UpdateIssueDto implements Serializable {
    private int id;
    private int project_id;
    private String description;
    private byte status;
    private int type_id;
    private int assigner_id;
    private int assignee_id;
    private int milestone_id;
    private int status_id;
    private String work_process;
    private String updated_by;
    private Date updated_date;
}
