package com.fpt.swd.database.repo;

import com.fpt.swd.database.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIssueRepository extends JpaRepository<Issue, Integer> {

}
