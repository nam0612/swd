package com.fpt.swd.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ClassStudent {
    @Id
    private int id;
    private int class_id;
    private int student_id;
    private boolean status;
    private String created_by;
    private Date created_date;
    private String updated_by;
    private Date updated_date;
}
