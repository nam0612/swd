package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepo extends JpaRepository<ProjectMember,Integer> {
}
