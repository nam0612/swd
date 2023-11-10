package com.fpt.swd.database.dto.ProjectMember;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AddProjectMemberDTO implements Serializable {
    private int project_id;
    private int member_id;
    private boolean status;
    private String created_by;
    private Date created_date;
}
