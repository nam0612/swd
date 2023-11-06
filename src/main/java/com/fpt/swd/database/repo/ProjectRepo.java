package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Integer> {
    @Query("SELECT p FROM Project p JOIN p.memberList m WHERE p.id = :id")
    Project findByIdWithMembers(@Param("id") int id);
}
