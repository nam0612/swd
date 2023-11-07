package com.fpt.swd.database.dto.Project;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetProjectDTO implements Serializable {
    private String name;
    private int team_leader_id;
    private int class_id;
    private String english_name;
    private String vietnamese_name;
    private String status;
    private String created_by;
    private String updated_by;
}
