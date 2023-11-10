package com.fpt.swd.business.ClassStudentBusiness;

import com.fpt.swd.database.dto.ClassStudent.AddNewClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.GetClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.GetClassStudentVer2Dto;
import com.fpt.swd.database.dto.ClassStudent.UpdateClassStudentDto;
import com.fpt.swd.Response.APIResponse;

public interface IClassStudentBusiness {
    APIResponse<Iterable<GetClassStudentVer2Dto>> GetAllClassStudent(int pageNo, int pageSize);

    APIResponse<Iterable<GetClassStudentDto>> AddNewClassStudent(AddNewClassStudentDto requestClassStudentDto);

    APIResponse<GetClassStudentDto> UpdateClassStudent(UpdateClassStudentDto requestClassStudentDto);

    APIResponse<GetClassStudentVer2Dto> GetClassStudent(int classStudentId);

    APIResponse<Iterable<GetClassStudentDto>> RemoveClassStudent(int classStudentId);
}
