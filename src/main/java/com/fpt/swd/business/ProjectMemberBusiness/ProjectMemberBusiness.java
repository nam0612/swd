package com.fpt.swd.business.ProjectMemberBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.ProjectMember.AddProjectMemberDTO;
import com.fpt.swd.database.dto.ProjectMember.GetProjectMemberDTO;
import com.fpt.swd.database.dto.ProjectMember.UpdateProjectMemberDTO;
import com.fpt.swd.database.entity.Project;
import com.fpt.swd.database.entity.ProjectMember;
import com.fpt.swd.database.repo.ProjectMemberRepo;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.checkerframework.checker.units.qual.A;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class ProjectMemberBusiness implements IProjectMemberBusiness{

    private final ModelMapper _mapper;
    private final ProjectMemberRepo _projectMemberRepo;

    public ProjectMemberBusiness(ModelMapper _mapper, ProjectMemberRepo _projectMemberRepo) {
        this._mapper = _mapper;
        this._projectMemberRepo = _projectMemberRepo;
    }

    @Override
    public APIResponse<Iterable<GetProjectMemberDTO>> getAllProjectMember() {
        var responseService= new APIResponse<Iterable<GetProjectMemberDTO>>();
        var listFromDb= _projectMemberRepo.findAll();
        responseService.Data=listFromDb.stream().map(c->_mapper.map(c,GetProjectMemberDTO.class)).toList();

        return responseService;
    }

    @Override
    public APIResponse<Iterable<GetProjectMemberDTO>> addNewProjectMember(AddProjectMemberDTO requestProjectMemberDTO) {
        var responseService =new APIResponse<Iterable<GetProjectMemberDTO>>();
        _projectMemberRepo.save(_mapper.map(requestProjectMemberDTO,ProjectMember.class));
        responseService.Data=_projectMemberRepo.findAll().stream().map(c->_mapper.map(c,GetProjectMemberDTO.class)).toList();
        return responseService;
    }

    @Override
    public APIResponse<Iterable<GetProjectMemberDTO>> removeProjectMember(int projectMemberId) {
        var responseService =new APIResponse<Iterable<GetProjectMemberDTO>>();
        Optional<ProjectMember> projectMember=_projectMemberRepo.findById(projectMemberId);
        if(projectMember.isPresent()){
            ProjectMember pj=projectMember.get();
            _projectMemberRepo.delete(pj);
            responseService.Data=_projectMemberRepo.findAll().stream().map(c->_mapper.map(c,GetProjectMemberDTO.class)).toList();

        }else{
            responseService.Data=null;
            responseService.status=false;
            responseService.message="Project with id "+projectMemberId+" is already existed!";
        }
        return responseService;
    }

    @Override
    public APIResponse<GetProjectMemberDTO> updateProjectMember(UpdateProjectMemberDTO requestProjectMemberDTO) {
        var responseService= new APIResponse<GetProjectMemberDTO>();
        Optional<ProjectMember> projectMember=_projectMemberRepo.findById(requestProjectMemberDTO.getId());
        if(projectMember.isPresent()){
            ProjectMember pj=projectMember.get();
            _mapper.map(requestProjectMemberDTO,pj);
            _projectMemberRepo.save(pj);
            responseService.Data=_mapper.map(pj,GetProjectMemberDTO.class);
        }else {
            responseService.Data=null;
            responseService.status=false;
            responseService.message="Project with id "+requestProjectMemberDTO.getId()+" is already existed!";
        }
        return responseService;
    }

    @Override
    public APIResponse<GetProjectMemberDTO> getProjectMember(int projectMemberId) {
        var responseService= new APIResponse<GetProjectMemberDTO>();
        Optional<ProjectMember> projectMember=_projectMemberRepo.findById(projectMemberId);
        if(projectMember.isPresent()){
            ProjectMember pj=projectMember.get();
            responseService.Data=_mapper.map(pj,GetProjectMemberDTO.class);
            return responseService;
        }else {
            responseService.Data=null;
            responseService.status=false;
            responseService.message="Project with id "+projectMemberId+" is already existed!";
        }
        return responseService;
    }
}
