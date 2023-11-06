package com.fpt.swd.business;

import com.fpt.swd.database.dto.MilestoneDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MilestoneBusiness {
    List<MilestoneDTO> getAllMilestone();

    MilestoneDTO updateMilestone(MilestoneDTO milestoneDTO, Integer id) throws Exception;

    MilestoneDTO createMilestone(MilestoneDTO milestoneDTO);

    MilestoneDTO deleteMilestone(Integer id) throws Exception;

    MilestoneDTO detailMilestone(Integer id) throws Exception;

}
