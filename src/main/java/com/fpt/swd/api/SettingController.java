package com.fpt.swd.api;

import com.fpt.swd.business.SettingBusiness;
import com.fpt.swd.database.dto.KeyValueDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fpt.swd.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/settings")
public class SettingController {

    private final SettingBusiness settingBusiness;

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("/{configCode}")
    public List<KeyValueDTO> getCurrentUser(@PathVariable String configCode) {
        return settingBusiness.getItemByConfigCode(configCode);
    }
}
