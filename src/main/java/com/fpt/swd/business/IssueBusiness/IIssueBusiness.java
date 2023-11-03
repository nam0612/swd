package com.fpt.swd.business.IssueBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.Issue.AddNewIssueDto;
import com.fpt.swd.database.dto.Issue.GetIssueDto;
import com.fpt.swd.database.dto.Issue.UpdateIssueDto;

public interface IIssueBusiness {
    APIResponse<Iterable<GetIssueDto>> GetAllIssue(int pageNo, int pageSize);
    APIResponse<Iterable<GetIssueDto>> AddNewIssue(AddNewIssueDto requestIssueDto);
    APIResponse<GetIssueDto> UpdateIssue(UpdateIssueDto requestIssueDto);
    APIResponse<GetIssueDto> GetIssue(int issueId);
    APIResponse<Iterable<GetIssueDto>> RemoveIssue(int issueId);
}
