package com.fpt.swd.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
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
@Table(name = "Project", schema = "swd392_database")
public class Project {
    @Id
    private int id;
    private String name;
    private int team_leader_id;
    private int class_id;
    private String english_name;
    private String vietnamese_name;
    private String status;
    private String created_by;
    private Date created_date;
    private String updated_by;
    private Date updated_date;

}

