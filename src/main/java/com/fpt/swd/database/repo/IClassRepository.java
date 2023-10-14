package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClassRepository extends JpaRepository<Class, Integer> {

    @Query("select c from Class c where c.teacher_id = ?1")
    Optional<Class> findClassByTeacher(int teacherId);

    @Query("select c from Class c where c.id = ?1")
    Optional<Class> findClassById(int id);

    @Query("select c from Class c where c.id = ?1")
    public boolean existsClassById(int id);
}
