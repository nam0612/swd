package com.fpt.swd.business;

import com.fpt.swd.database.dto.MilestoneDTO;
import com.fpt.swd.database.dto.milestone.UpdateMilstone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MilestoneBusiness {
    List<MilestoneDTO> getAllMilestone();

    void updateMilestone(UpdateMilstone milestoneDTO, Integer id) throws Exception;

    void createMilestone(UpdateMilstone milestoneDTO);

    void deleteMilestone(Integer id) throws Exception;

    MilestoneDTO detailMilestone(Integer id) throws Exception;

}
