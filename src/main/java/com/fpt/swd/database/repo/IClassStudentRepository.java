package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.ClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClassStudentRepository extends JpaRepository<ClassStudent, Integer> {
    @Query("update ClassStudent e set e.status = :status where e.id = :id")
    Optional<ClassStudent> UpdateStatus(@Param("id") Integer id, @Param("status") boolean status);
}
