package com.fpt.swd.business.SubjectBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.Subject.AddSubjectDTO;
import com.fpt.swd.database.dto.Subject.GetSubjectDTO;
import com.fpt.swd.database.dto.Subject.UpdateSubjectDTO;

public interface ISubjectBusiness{
    APIResponse<Iterable<GetSubjectDTO>> getAllSubject(int pageNo, int pageSize);
    APIResponse<Iterable<GetSubjectDTO>> addNewSubject(AddSubjectDTO requestSubjectDTO);

    APIResponse<Iterable<GetSubjectDTO>> removeSubject(int subjectId);

    APIResponse<GetSubjectDTO> updateSubject(UpdateSubjectDTO requestSubjectDTO);

    APIResponse<GetSubjectDTO> getSubject(int subjectId);
}
