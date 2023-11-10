package com.fpt.swd.database.repo;

import com.fpt.swd.database.dto.Class.GetClassVer2Dto;
import com.fpt.swd.database.dto.ClassStudent.GetClassStudentVer2Dto;
import com.fpt.swd.database.entity.Class;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface IClassRepository extends JpaRepository<Class, Integer> {
    @Query("SELECT new com.fpt.swd.database.dto.Class.GetClassVer2Dto(c.id,  c.name,  c.description,  c.semester_id,  c.teacher_id,  c.subject_id,  c.start_date,  c.end_date,  c.status,  c.created_by,  c.updated_by,  s.value,  u.firstName,  u.lastName,  sj.subject_code,  sj.subject_name) " +
            "from Class as c " +
            "JOIN FETCH Setting as s ON c.semester_id = CAST(s.code AS integer) " +
            "JOIN FETCH User as u ON c.teacher_id = u.id " +
            "JOIN FETCH Subject as sj ON c.subject_id = sj.id")
    Page<GetClassVer2Dto> findAllList(Pageable pageable);

    @Query("SELECT new com.fpt.swd.database.dto.Class.GetClassVer2Dto(c.id,  c.name,  c.description,  c.semester_id,  c.teacher_id,  c.subject_id,  c.start_date,  c.end_date,  c.status,  c.created_by,  c.updated_by,  s.value,  u.firstName,  u.lastName,  sj.subject_code,  sj.subject_name) " +
            "from Class as c " +
            "JOIN FETCH Setting as s ON c.semester_id = CAST(s.code AS integer) " +
            "JOIN FETCH User as u ON c.teacher_id = u.id " +
            "JOIN FETCH Subject as sj ON c.subject_id = sj.id " +
            "WHERE c.id = :id")
    Optional<GetClassVer2Dto> findByUserSettingSubject(@Param("id") Integer id);
}
