package com.fpt.swd.business.AssignmentBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.Assignment.AddNewAssignmentDto;
import com.fpt.swd.database.dto.Assignment.GetAssignmentDto;
import com.fpt.swd.database.dto.Assignment.UpdateAssignmentDto;

public interface IAssignmentBusiness {
    APIResponse<Iterable<GetAssignmentDto>> GetAllAssignment(int pageNo, int pageSize);

    APIResponse<Iterable<GetAssignmentDto>> AddNewAssignment(AddNewAssignmentDto requestAssignmentDto);

    APIResponse<GetAssignmentDto> UpdateAssignment(UpdateAssignmentDto requestAssignmentDto);

    APIResponse<GetAssignmentDto> GetAssignment(int assignmentId);

    APIResponse<Iterable<GetAssignmentDto>> RemoveAssignment(int assignmentId);
}
