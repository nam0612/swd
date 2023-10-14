package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.Class;
import com.fpt.swd.database.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IStudentRepository extends JpaRepository<Student, Integer> {
    @Query("select s from Student s where s.name = ?1")
    Optional<Iterable<Student>> findStudentByName(String name);
}
