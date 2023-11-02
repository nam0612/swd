package com.fpt.swd.business.IssueSettingBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.IssueSetting.AddNewIssueSettingDto;
import com.fpt.swd.database.dto.IssueSetting.GetIssueSettingDto;
import com.fpt.swd.database.dto.IssueSetting.UpdateIssueSettingDto;

public interface IIssueSettingBusiness {
    APIResponse<Iterable<GetIssueSettingDto>> GetAllIssueSetting(int pageNo, int pageSize);
    APIResponse<Iterable<GetIssueSettingDto>> AddNewIssueSetting(AddNewIssueSettingDto requestIssueSettingDto);
    APIResponse<GetIssueSettingDto> UpdateIssueSetting(UpdateIssueSettingDto requestIssueSettingDto);
    APIResponse<GetIssueSettingDto> GetIssueSetting(int issueSettingId);
    APIResponse<Iterable<GetIssueSettingDto>> RemoveIssueSetting(int issueSettingId);
}
