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
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int project_id;
    private String description;
    private byte status;
    private int type_id;
    private int assigner_id;
    private int assignee_id;
    private int milestone_id;
    private int status_id;
    private String work_process;
    private String created_by;
    private Date created_date;
    private String updated_by;
    private Date updated_date;
}
