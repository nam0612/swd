package com.fpt.swd.business.impl;

import com.fpt.swd.business.SettingBusiness;
import com.fpt.swd.database.entity.Setting;
import com.fpt.swd.database.repo.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingBusinessImpl implements SettingBusiness {

    @Autowired
    private SettingRepository settingRepository;
    @Override
    public List<Setting> getItemByConfigCode(String configCode) {
        return settingRepository.findByConfigCode(configCode);
    }
}
