package com.fpt.swd.database.dto.ProjectMember;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMemberJoinDTO implements Serializable {
    private int id;
    private int project_id;
    private int member_id;
    private boolean status;
    private String created_by;
    private String updated_by;
    private String userFirstName;
    private String userLastName;
    private String projectName;

    public ProjectMemberJoinDTO(int id, int project_id, int member_id, boolean status, String created_by, String updated_by, String userFirstName, String userLastName, String projectName) {
        this.id = id;
        this.project_id = project_id;
        this.member_id = member_id;
        this.status = status;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.projectName = projectName;
    }
}
