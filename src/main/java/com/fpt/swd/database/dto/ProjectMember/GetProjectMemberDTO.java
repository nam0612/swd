package com.fpt.swd.database.dto.ProjectMember;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetProjectMemberDTO implements Serializable {
    private int project_id;
    private int member_id;
    private boolean status;
    private String created_by;
    private String updated_by;
}
