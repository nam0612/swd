package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IAssignmentRepository extends JpaRepository<Assignment, Integer> { }
