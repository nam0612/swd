package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject,Integer> {
}
