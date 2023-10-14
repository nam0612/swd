package com.fpt.swd.business.StudentBusiness;

import com.fpt.swd.database.dto.Student.AddNewStudentDto;
import com.fpt.swd.database.dto.Student.GetStudentDto;
import com.fpt.swd.database.dto.Student.UpdateStudentDto;
import com.fpt.swd.database.entity.APIResponse;

public interface IStudentBusiness {
    APIResponse<Iterable<GetStudentDto>> GetAllStudent();

    APIResponse<Iterable<GetStudentDto>> AddNewStudent(AddNewStudentDto requestStudentDto);

    APIResponse<GetStudentDto> UpdateStudent(UpdateStudentDto requestStudentDto);

    APIResponse<GetStudentDto> GetStudent(int studentId);

    APIResponse<Iterable<GetStudentDto>> RemoveStudent(int studentId);
}
