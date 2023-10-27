package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAssignmentRepository extends JpaRepository<Assignment, Integer> { }
