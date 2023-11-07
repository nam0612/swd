package com.fpt.swd.database.repo;

import com.fpt.swd.database.dto.Project.ProjectJoinDTO;
import com.fpt.swd.database.dto.ProjectMember.ProjectMemberJoinDTO;
import com.fpt.swd.database.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Integer> {
    @Query("SELECT new com.fpt.swd.database.dto.Project.ProjectJoinDTO(p.id,p.name, p.team_leader_id, p.class_id, p.english_name, p.vietnamese_name, p.status, p.created_by, p.updated_by," +
            "u.firstName,u.lastName,c.name) from Project as p JOIN  User as u ON p.team_leader_id = u.id JOIN FETCH Class as c ON p.class_id = c.id")
    Page<ProjectJoinDTO> findAllList(Pageable pageable);
}
