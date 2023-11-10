package com.fpt.swd.business.AssignmentBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.Assignment.AddNewAssignmentDto;
import com.fpt.swd.database.dto.Assignment.GetAssignmentDto;
import com.fpt.swd.database.dto.Assignment.GetAssignmentVer2Dto;
import com.fpt.swd.database.dto.Assignment.UpdateAssignmentDto;

public interface IAssignmentBusiness {
    APIResponse<Iterable<GetAssignmentVer2Dto>> GetAllAssignment(int pageNo, int pageSize);

    APIResponse<Iterable<GetAssignmentDto>> AddNewAssignment(AddNewAssignmentDto requestAssignmentDto);

    APIResponse<GetAssignmentDto> UpdateAssignment(UpdateAssignmentDto requestAssignmentDto);

    APIResponse<GetAssignmentVer2Dto> GetAssignment(int assignmentId);

    APIResponse<Iterable<GetAssignmentDto>> RemoveAssignment(int assignmentId);
}
