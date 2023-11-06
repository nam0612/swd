package com.fpt.swd.business.ProjectBusiness;

import com.fpt.swd.database.dto.Project.AddProjectDTO;
import com.fpt.swd.database.dto.Project.GetProjectDTO;
import com.fpt.swd.database.dto.Project.UpdateProjectDTO;
import com.fpt.swd.database.dto.Subject.AddSubjectDTO;
import com.fpt.swd.Response.APIResponse;

public interface IProjectBusiness {
    APIResponse<Iterable<GetProjectDTO>> getAllProject(int pageNo, int pageSize);
    APIResponse<Iterable<GetProjectDTO>> addNewProject(AddProjectDTO requestProjectDTO);

    APIResponse<Iterable<GetProjectDTO>> removeProject(int projectId);

    APIResponse<GetProjectDTO> updateProject(UpdateProjectDTO requestProjectDTO);

    APIResponse<GetProjectDTO> getProject(int projectId);
}
