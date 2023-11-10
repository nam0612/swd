package com.fpt.swd.database.repo;

import com.fpt.swd.database.dto.Class.GetClassVer2Dto;
import com.fpt.swd.database.dto.Issue.GetIssueVer2Dto;
import com.fpt.swd.database.entity.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IIssueRepository extends JpaRepository<Issue, Integer> {
    @Query("SELECT new com.fpt.swd.database.dto.Issue.GetIssueVer2Dto(i.id,  i.project_id,  p.name,  i.description,  i.status,  i.type_id,  u.id as user_id,    i.assignee_id,  u.username, i.assignee_id,  u.username,  i.milestone_id,  i.status_id,  i.work_process,  i.created_by,  i.updated_by) " +
            "from Issue as i " +
            "JOIN FETCH Project as p ON i.project_id = p.id " +
            "JOIN FETCH User as u ON i.assigner_id = u.id "
           )
    Page<GetIssueVer2Dto> findAllList(Pageable pageable);

}
