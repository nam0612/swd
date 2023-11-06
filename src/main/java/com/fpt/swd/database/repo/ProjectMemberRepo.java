package com.fpt.swd.database.repo;

import com.fpt.swd.database.dto.ClassStudent.GetClassStudentVer2Dto;
import com.fpt.swd.database.dto.ProjectMember.ProjectMemberJoinDTO;
import com.fpt.swd.database.entity.Project;
import com.fpt.swd.database.entity.ProjectMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjectMemberRepo extends JpaRepository<ProjectMember,Integer> {

    @Query("SELECT new com.fpt.swd.database.dto.ProjectMember.ProjectMemberJoinDTO(pm.id, pm.project_id, pm.member_id, pm.status, pm.created_by, pm.updated_by, p.name, u.firstName, u.lastName) from ProjectMember as pm JOIN  User as u ON pm.member_id = u.id JOIN FETCH Project as p ON pm.project_id = p.id")
    Page<ProjectMemberJoinDTO> findAllList(Pageable pageable);

    @Query("SELECT new com.fpt.swd.database.dto.ProjectMember.ProjectMemberJoinDTO(pm.id, pm.project_id, pm.member_id, pm.status, pm.created_by, pm.updated_by, p.name, u.firstName, u.lastName) from ProjectMember as pm JOIN  User as u ON pm.member_id = u.id JOIN FETCH Project as p ON pm.project_id = p.id where pm.id = :id")
    Optional<ProjectMemberJoinDTO>  findByUserProject(@Param("id") Integer id);
}
