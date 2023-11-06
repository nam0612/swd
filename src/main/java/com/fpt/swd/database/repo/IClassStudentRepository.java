package com.fpt.swd.database.repo;

import com.fpt.swd.database.dto.ClassStudent.GetClassStudentDto;
import com.fpt.swd.database.entity.ClassStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassStudentRepository extends JpaRepository<ClassStudent, Integer> {
    @Query("SELECT new com.fpt.swd.database.dto.ClassStudent.GetClassStudentDto(cs.id,  cs.class_id,  cs.student_id,  cs.status,  cs.created_by,  cs.updated_by,  c.name,  u.firstName,  u.lastName) from ClassStudent as cs JOIN  User as u ON cs.student_id = u.id JOIN FETCH Class as c ON cs.class_id = c.id")
    Page<GetClassStudentDto> findAllList(Pageable pageable);

}