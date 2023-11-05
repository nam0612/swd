package com.fpt.swd.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Subject", schema = "swd392_database")
public class Subject {
    @Id
    private int id;
    private int manager_id;
    private String subject_code;
    private String subject_name;
    private String description;
    private String start_date;
    private String created_by;
    private Date created_date;
    private String updated_by;
    private Date updated_date;
}
