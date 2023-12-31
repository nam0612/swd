package com.fpt.swd.business.ProjectMemberBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.ProjectMember.AddProjectMemberDTO;
import com.fpt.swd.database.dto.ProjectMember.GetProjectMemberDTO;
import com.fpt.swd.database.dto.ProjectMember.ProjectMemberJoinDTO;
import com.fpt.swd.database.dto.ProjectMember.UpdateProjectMemberDTO;



public interface IProjectMemberBusiness {
    APIResponse<Iterable<ProjectMemberJoinDTO>> getAllProjectMember(int pageNo, int pageSize);
    APIResponse<Iterable<GetProjectMemberDTO>> addNewProjectMember(AddProjectMemberDTO requestProjectMemberDTO);

    APIResponse<Iterable<GetProjectMemberDTO>> removeProjectMember(int projectMemberId);

    APIResponse<GetProjectMemberDTO> updateProjectMember(UpdateProjectMemberDTO requestProjectMemberDTO);

    APIResponse<ProjectMemberJoinDTO> getProjectMember(int projectMemberId);

}
