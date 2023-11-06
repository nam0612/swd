package com.fpt.swd.database.repo;

import com.fpt.swd.database.dto.Assignment.GetAssignmentDto;
import com.fpt.swd.database.dto.Assignment.GetAssignmentVer2Dto;
import com.fpt.swd.database.dto.Class.GetClassVer2Dto;
import com.fpt.swd.database.entity.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IAssignmentRepository extends JpaRepository<Assignment, Integer> {

    @Query("SELECT new com.fpt.swd.database.dto.Assignment.GetAssignmentVer2Dto(a.id,  a.name,  a.subject_id,  a.description,  a.created_by,  a.updated_by,  s.subject_code,  s.subject_name) " +
            "from Assignment as a " +
            "JOIN FETCH Subject as s ON a.subject_id = s.id")
    Page<GetAssignmentVer2Dto> findAllList(Pageable pageable);

    @Query("SELECT new com.fpt.swd.database.dto.Assignment.GetAssignmentVer2Dto(a.id,  a.name,  a.subject_id,  a.description,  a.created_by,  a.updated_by,  s.subject_code,  s.subject_name) " +
            "from Assignment as a " +
            "JOIN FETCH Subject as s ON a.subject_id = s.id " +
            "WHERE a.id = :id")
    Optional<GetAssignmentVer2Dto> findByAssignmentSubject(@Param("id") Integer id);
}
