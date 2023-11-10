package com.fpt.swd.business.impl;

import com.fpt.swd.business.MilestoneBusiness;
import com.fpt.swd.database.dto.MilestoneDTO;
import com.fpt.swd.database.dto.milestone.UpdateMilstone;
import com.fpt.swd.database.entity.Milestone;
import com.fpt.swd.database.repo.MilestoneRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MilestoneBusinessImpl implements MilestoneBusiness {

    @Autowired
    private MilestoneRepository milestoneRepository;
    @Override
    public List<MilestoneDTO> getAllMilestone() {
        return milestoneRepository.findAllList();
    }

    @Override
    public void updateMilestone(UpdateMilstone milestoneDTO, Integer id) throws Exception {
        Optional<Milestone> milestone = milestoneRepository.findById(id);

        if(milestone.isPresent()) {
            Milestone milestoneEntity = milestone.get();
            milestoneEntity.setName(milestoneDTO.getName());
            milestoneEntity.setDescription(milestoneDTO.getDescription());
            milestoneEntity.setStatus(milestoneDTO.getStatus());
            milestoneEntity.setClassId(milestoneDTO.getClassId());
            milestoneEntity.setProjectId(milestoneDTO.getClassId());

            milestoneRepository.save(milestoneEntity);
        } else {
            throw new Exception("Milestone not found");
        }
    }

    @Override
    public void createMilestone(UpdateMilstone milestoneDTO) {
        Milestone milestone = new Milestone();
        BeanUtils.copyProperties(milestoneDTO, milestone);
        milestoneRepository.save(milestone);
    }

    @Override
    public void deleteMilestone(Integer id) throws Exception {
        Optional<Milestone> milestone = milestoneRepository.findById(id);

        if(milestone.isPresent()) {
            Milestone milestoneEntity = milestone.get();
            milestoneEntity.setStatus(0);
            milestoneRepository.save(milestoneEntity);
        } else {
            throw new Exception("Milestone not found");
        }
    }

    @Override
    public MilestoneDTO detailMilestone(Integer id) throws Exception {
        Optional<Milestone> milestone = milestoneRepository.findById(id);

        if(milestone.isPresent()) {
            MilestoneDTO milestoneDTO = new MilestoneDTO();
            BeanUtils.copyProperties(milestone.get(), milestoneDTO);
            return milestoneDTO;
        } else {
            throw new Exception("Milestone not found");
        }
    }
}
