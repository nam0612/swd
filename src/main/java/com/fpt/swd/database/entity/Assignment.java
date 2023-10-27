package com.fpt.swd.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class Assignment {
    @Id
    private int id;
    private String name;
    private int subject_id;
    private String description;
    private String created_by;
    private Date created_date;
    private String updated_by;
    private Date updated_date;
}
