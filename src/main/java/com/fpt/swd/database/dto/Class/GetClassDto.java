package com.fpt.swd.database.dto.Class;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class GetClassDto implements Serializable {
    private int id;
    private String name;
    private boolean status;
    private String description;
    private String semester_name;
    private int team_leader_id;
    private int teacher_id;
    private Date start_date;
    private Date end_date;
}
