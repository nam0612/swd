package com.fpt.swd.database.dto.Project;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UpdateProjectDTO implements Serializable{
    private int id;
    private String name;
    private int user_id;
    private int class_id;
    private int team_leader_id;
    private String english_name;
    private String vietnamese_name;
    private String status;
    private String updated_by;
    private Date updated_date;
}
