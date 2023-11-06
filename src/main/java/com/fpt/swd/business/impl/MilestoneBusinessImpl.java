package com.fpt.swd.business.impl;

import com.fpt.swd.business.MilestoneBusiness;
import com.fpt.swd.database.dto.MilestoneDTO;
import com.fpt.swd.database.repo.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneBusinessImpl implements MilestoneBusiness {

    @Autowired
    private MilestoneRepository milestoneRepository;
    @Override
    public List<MilestoneDTO> getAllMilestone() {
        return milestoneRepository.findAllList();
    }
}
