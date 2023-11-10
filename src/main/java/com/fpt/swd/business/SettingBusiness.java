package com.fpt.swd.business;

import com.fpt.swd.database.dto.KeyValueDTO;
import com.fpt.swd.database.entity.Setting;
import java.util.List;


public interface SettingBusiness {

    public List<KeyValueDTO> getItemByConfigCode(String configCode);
}
