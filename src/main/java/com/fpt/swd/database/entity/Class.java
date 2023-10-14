package com.fpt.swd.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Class {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private boolean status;
    private String description;
    private String semester_name;
    private int team_leader_id;
    private int teacher_id;
    private Date start_date;
    private Date end_date;
    private String created_by;
    private Date created_date;
    private String updated_by;
    private Date updated_date;
}
