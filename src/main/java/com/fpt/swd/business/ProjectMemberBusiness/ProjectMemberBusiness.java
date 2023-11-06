package com.fpt.swd.business.ProjectMemberBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.ProjectMember.AddProjectMemberDTO;
import com.fpt.swd.database.dto.ProjectMember.GetProjectMemberDTO;
import com.fpt.swd.database.dto.ProjectMember.UpdateProjectMemberDTO;
import com.fpt.swd.database.entity.ProjectMember;
import com.fpt.swd.database.repo.ProjectMemberRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public APIResponse<Iterable<GetProjectMemberDTO>> getAllProjectMember(int pageNo, int pageSize) {
        Page<ProjectMember> listFromDb=_projectMemberRepo.findAll(PageRequest.of(pageNo,pageSize));
        var responseService= new APIResponse<Iterable<GetProjectMemberDTO>>();
        responseService.Data=listFromDb.getContent().stream().map(c->_mapper.map(c,GetProjectMemberDTO.class)).toList();
        responseService.pagination.pageNo = listFromDb.getNumber();
        responseService.pagination.pageSize = listFromDb.getSize();
        responseService.pagination.totalElements = listFromDb.getTotalElements();
        responseService.pagination.totalPages = listFromDb.getTotalPages();
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
