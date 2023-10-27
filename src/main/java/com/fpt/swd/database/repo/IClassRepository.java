package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.Class;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClassRepository extends JpaRepository<Class, Integer> { }
