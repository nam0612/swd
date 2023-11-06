package com.fpt.swd.business.impl;

import com.fpt.swd.business.SettingBusiness;
import com.fpt.swd.database.dto.KeyValueDTO;
import com.fpt.swd.database.entity.Setting;
import com.fpt.swd.database.repo.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettingBusinessImpl implements SettingBusiness {

    @Autowired
    private SettingRepository settingRepository;
    @Override
    public List<KeyValueDTO> getItemByConfigCode(String configCode) {
        List<Setting> settLst =  settingRepository.findByConfigCode(configCode);
        List<KeyValueDTO> keyValueDTOList = new ArrayList<>();
        for (Setting setting : settLst) {
            keyValueDTOList.add(KeyValueDTO.builder()
                            .code(setting.getCode())
                            .value(setting.getValue())
                            .build());
        }
        return keyValueDTOList;
    }
}
