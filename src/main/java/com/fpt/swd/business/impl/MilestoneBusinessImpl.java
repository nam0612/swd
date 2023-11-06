package com.fpt.swd.business.impl;

import com.fpt.swd.business.MilestoneBusiness;
import com.fpt.swd.database.dto.MilestoneDTO;
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
    public MilestoneDTO updateMilestone(MilestoneDTO milestoneDTO, Integer id) throws Exception {
        Optional<Milestone> milestone = milestoneRepository.findById(id);

        if(milestone.isPresent()) {
            Milestone milestoneEntity = milestone.get();
            BeanUtils.copyProperties(milestoneDTO, milestoneEntity);
            milestoneRepository.save(milestoneEntity);
            return MilestoneDTO.builder()
                    .id(id)
                    .name(milestoneEntity.getName())
                    .build();
        } else {
            throw new Exception("Milestone not found");
        }
    }

    @Override
    public MilestoneDTO createMilestone(MilestoneDTO milestoneDTO) {
        Milestone milestone = new Milestone();
        BeanUtils.copyProperties(milestoneDTO, milestone);
        milestoneRepository.save(milestone);
        return MilestoneDTO.builder()
                .id(milestone.getId())
                .name(milestone.getName())
                .build();
    }

    @Override
    public MilestoneDTO deleteMilestone(Integer id) throws Exception {
        Optional<Milestone> milestone = milestoneRepository.findById(id);

        if(milestone.isPresent()) {
            Milestone milestoneEntity = milestone.get();
            milestoneEntity.setStatus(0);
            milestoneRepository.save(milestoneEntity);
            return MilestoneDTO.builder()
                    .id(id)
                    .name(milestoneEntity.getName())
                    .build();
        } else {
            throw new Exception("Milestone not found");
        }
    }

    @Override
    public MilestoneDTO detailMilestone(Integer id) throws Exception {
        Optional<Milestone> milestone = milestoneRepository.findById(id);

        if(milestone.isPresent()) {
            MilestoneDTO milestoneDTO = MilestoneDTO.builder().build();
            BeanUtils.copyProperties(milestone.get(), milestoneDTO);
            return milestoneDTO;
        } else {
            throw new Exception("Milestone not found");
        }
    }
}
