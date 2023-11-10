package com.fpt.swd.database.repo;

import com.fpt.swd.database.dto.IssueSetting.GetIssueSettingVer2Dto;
import com.fpt.swd.database.entity.IssueSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IIssueSettingRepository extends JpaRepository<IssueSetting,Integer> {

    @Query("SELECT new com.fpt.swd.database.dto.IssueSetting.GetIssueSettingVer2Dto(is.id,  is.project_id,  p.name,  is.class_id,  c.name,  is.subject_id, s.subject_name,    is.description,  is.status, is.created_by,  is.created_date,  is.updated_by,  is.updated_date) " +
            "FROM IssueSetting AS is " +
            "JOIN FETCH Project AS p ON is.project_id = p.id " +
            "JOIN FETCH Class AS c ON is.class_id = c.id " +
            "JOIN FETCH Subject AS s ON is.subject_id = s.id"
    )
    Page<GetIssueSettingVer2Dto> findAllList(Pageable pageable);

}
