package com.fpt.swd.database.dto.Class;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class UpdateClassDto implements Serializable {
    private int id;
    private String name;
    private boolean status;
    private String description;
    private String semester_name;
    private int team_leader_id;
    private int teacher_id;
    private Date start_date;
    private Date end_date;
    private String updated_by;
    private Date updated_date;
}
