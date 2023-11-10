package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer> {
    List<Setting> findByConfigCode(String configCode);
}
