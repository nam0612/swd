package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends JpaRepository<Subject,Integer> {
}
