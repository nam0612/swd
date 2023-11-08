package com.fpt.swd.api;

import com.fpt.swd.api.request.UserDto;
import com.fpt.swd.business.MilestoneBusiness;
import com.fpt.swd.database.dto.MilestoneDTO;
import com.fpt.swd.database.dto.milestone.UpdateMilstone;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.fpt.swd.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/milestone")
public class MilestoneController {

    private final MilestoneBusiness milestoneBusiness;

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping
    public List<MilestoneDTO> getAllMilestone() {
        return milestoneBusiness.getAllMilestone();
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PutMapping ("/{id}")
    public void updateMilestone(@RequestBody UpdateMilstone milestoneDTO, @PathVariable Integer id) throws Exception {
         milestoneBusiness.updateMilestone(milestoneDTO, id);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PostMapping
    public void createMilestone(@RequestBody UpdateMilstone milestoneDTO) throws Exception {
         milestoneBusiness.createMilestone(milestoneDTO);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PatchMapping("/{id}")
    public void deleteMilestone(@PathVariable Integer id) throws Exception{
         milestoneBusiness.deleteMilestone(id);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("/{id}")
    public MilestoneDTO detailMilestone(@PathVariable Integer id) throws Exception{
        return milestoneBusiness.detailMilestone(id);
    }
}
