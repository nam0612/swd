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
@Table(name = "Project_Member", schema = "swd392_database")
public class ProjectMember {
    @Id
    private int id;
    private int project_id;
    private int member_id;
    private boolean status;
    private String created_by;
    private Date created_date;
    private String updated_by;
    private Date updated_date;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project projectMemberList;

}
