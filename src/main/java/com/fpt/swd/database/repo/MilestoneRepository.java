package com.fpt.swd.database.repo;

import com.fpt.swd.database.dto.MilestoneDTO;
import com.fpt.swd.database.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Integer> {
    @Query("SELECT new com.fpt.swd.database.dto.MilestoneDTO(mi.id, mi.name, mi.description, mi.status, pr.name, cl.name) " +
            "from Milestone mi " +
            "JOIN Project pr ON mi.projectId = pr.id " +
            "JOIN Class cl ON mi.classId = cl.id ")
    List<MilestoneDTO> findAllList();
}
