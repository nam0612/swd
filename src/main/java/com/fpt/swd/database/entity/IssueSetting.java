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
public class IssueSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int project_id;
    private int class_id;
    private int subject_id;
    private String description;
    private byte status;
    private String created_by;
    private Date created_date;
    private String updated_by;
    private Date updated_date;
}
