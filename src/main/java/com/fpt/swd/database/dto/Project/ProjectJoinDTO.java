package com.fpt.swd.database.dto.Project;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ProjectJoinDTO implements Serializable {
    private int id;
    private String name;
    private int team_leader_id;
    private int class_id;
    private String english_name;
    private String vietnamese_name;
    private String status;
    private String created_by;
    private String updated_by;
    private String tlFirstName;
    private String tlLastName;
    private String class_name;

    public ProjectJoinDTO(int id, String name, int team_leader_id, int class_id, String english_name, String vietnamese_name, String status, String created_by, String updated_by, String tlFirstName, String tlLastName, String class_name) {
        this.id = id;
        this.name = name;
        this.team_leader_id = team_leader_id;
        this.class_id = class_id;
        this.english_name = english_name;
        this.vietnamese_name = vietnamese_name;
        this.status = status;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.tlFirstName = tlFirstName;
        this.tlLastName = tlLastName;
        this.class_name = class_name;
    }
}
