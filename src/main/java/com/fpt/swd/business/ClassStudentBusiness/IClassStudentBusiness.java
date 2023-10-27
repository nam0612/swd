package com.fpt.swd.business.ClassStudentBusiness;

import com.fpt.swd.database.dto.ClassStudent.AddNewClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.GetClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.UpdateClassStudentDto;
import com.fpt.swd.Response.APIResponse;

public interface IClassStudentBusiness {
    APIResponse<Iterable<GetClassStudentDto>> GetAllClassStudent(int pageNo, int pageSize);

    APIResponse<Iterable<GetClassStudentDto>> AddNewClassStudent(AddNewClassStudentDto requestClassStudentDto);

    APIResponse<GetClassStudentDto> UpdateClassStudent(UpdateClassStudentDto requestClassStudentDto);

    APIResponse<GetClassStudentDto> GetClassStudent(int classStudentId);

    APIResponse<Iterable<GetClassStudentDto>> RemoveClassStudent(int classStudentId);
}
