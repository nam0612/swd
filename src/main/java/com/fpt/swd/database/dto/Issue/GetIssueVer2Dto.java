package com.fpt.swd.database.dto.Issue;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetIssueVer2Dto implements Serializable {
    private int id;
    private int project_id;
    private String project_name;
    private String description;
    private byte status;
    private int type_id;
    private long user_id;
    private int assigner_id;
    private String assigner_name;
    private int assignee_id;
    private String assignee_name;
    private int milestone_id;
    private int status_id;
    private String work_process;
    private String created_by;
    private String updated_by;

    public GetIssueVer2Dto(int id, int project_id, String project_name, String description, byte status, int type_id, long user_id, int assigner_id, String assigner_name, int assignee_id, String assignee_name, int milestone_id, int status_id, String work_process, String created_by, String updated_by) {
        this.id = id;
        this.project_id = project_id;
        this.project_name = project_name;
        this.description = description;
        this.status = status;
        this.type_id = type_id;
        this.user_id = user_id;
        this.assigner_id = assigner_id;
        this.assigner_name = assigner_name;
        this.assignee_id = assignee_id;
        this.assignee_name = assignee_name;
        this.milestone_id = milestone_id;
        this.status_id = status_id;
        this.work_process = work_process;
        this.created_by = created_by;
        this.updated_by = updated_by;
    }
}

