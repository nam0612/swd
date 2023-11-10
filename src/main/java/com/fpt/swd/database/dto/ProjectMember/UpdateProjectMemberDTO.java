package com.fpt.swd.database.dto.ProjectMember;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter

public class UpdateProjectMemberDTO implements Serializable {
    private int id;
    private int project_id;
    private int member_id;
    private boolean status;
    private String updated_by;
    private Date updated_date;
}
