package com.fpt.swd.api;

import com.fpt.swd.api.request.UserDto;
import com.fpt.swd.business.MilestoneBusiness;
import com.fpt.swd.database.dto.MilestoneDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<MilestoneDTO> getUsers() {
        return milestoneBusiness.getAllMilestone();
    }
}
