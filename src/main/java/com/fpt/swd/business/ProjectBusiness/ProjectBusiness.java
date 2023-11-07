package com.fpt.swd.business.ProjectBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.Class.GetClassDto;
import com.fpt.swd.database.dto.Project.AddProjectDTO;
import com.fpt.swd.database.dto.Project.GetProjectDTO;
import com.fpt.swd.database.dto.Project.ProjectJoinDTO;
import com.fpt.swd.database.dto.Project.UpdateProjectDTO;
import com.fpt.swd.database.entity.Project;
import com.fpt.swd.database.repo.ProjectRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectBusiness implements IProjectBusiness{
    private final ModelMapper _mapper;
    private final ProjectRepo _projectRepo;

    public ProjectBusiness(ModelMapper _mapper, ProjectRepo _projectRepo) {
        this._mapper = _mapper;
        this._projectRepo = _projectRepo;
    }


    @Override
    public APIResponse<Iterable<ProjectJoinDTO>> getAllProject(int pageNo, int pageSize) {
        Page<ProjectJoinDTO> dbProject=_projectRepo.findAllList(PageRequest.of(pageNo, pageSize));
        var serviceResponse = new APIResponse<Iterable<ProjectJoinDTO>>();
        serviceResponse.Data=dbProject.getContent().stream().map(c->_mapper.map(c, ProjectJoinDTO.class)).toList();
        serviceResponse.pagination.pageNo = dbProject.getNumber();
        serviceResponse.pagination.pageSize = dbProject.getSize();
        serviceResponse.pagination.totalElements = dbProject.getTotalElements();
        serviceResponse.pagination.totalPages = dbProject.getTotalPages();
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetProjectDTO>> addNewProject(AddProjectDTO requestProjectDTO) {
        var serviceResponse=new APIResponse<Iterable<GetProjectDTO>>();
        var dbClass= _projectRepo.findAll().stream().filter(c-> Objects.equals(c.getName(),requestProjectDTO.getName())).findFirst();
        if(dbClass.isPresent()){
            serviceResponse.Data=null;
            serviceResponse.status=false;
            serviceResponse.message="Project with name "+requestProjectDTO.getName()+" is already existed!";
            return serviceResponse;
        }
        Project newProject=_mapper.map(requestProjectDTO,Project.class);
        _projectRepo.save(newProject);
        serviceResponse.Data=_projectRepo.findAll().stream().map(c->_mapper.map(c,GetProjectDTO.class)).toList();
        return serviceResponse;
    }


    @Override
    public APIResponse<Iterable<GetProjectDTO>> removeProject(int projectId) {
        var serviceResponse= new APIResponse<Iterable<GetProjectDTO>>();
        Optional<Project> responseProject= _projectRepo.findById(projectId);
        if(responseProject.isPresent()){
            Project existedProject = responseProject.get();
            _projectRepo.delete(existedProject);
            serviceResponse.Data=_projectRepo.findAll().stream().map(c->_mapper.map(c,GetProjectDTO.class)).toList();
            return serviceResponse;
        }else {
            serviceResponse.Data=null;
            serviceResponse.status=false;
            serviceResponse.message="Project with id "+projectId+" does not exist!";

        }
        return serviceResponse;
    }

    @Override
    public APIResponse<GetProjectDTO> updateProject(UpdateProjectDTO requestProjectDTO) {
        var serviceResponse=new APIResponse<GetProjectDTO>();
        Optional<Project> responseProject= _projectRepo.findById(requestProjectDTO.getId());
        if(responseProject.isPresent()){
            Project existedProject= responseProject.get();
//            _mapper.map(requestProjectDTO,existedProject);
            existedProject.setName(requestProjectDTO.getName());
            _projectRepo.save(existedProject);
            serviceResponse.Data = _mapper.map(existedProject, GetProjectDTO.class);

        }else{
            serviceResponse.Data=null;
            serviceResponse.message="Project with id "+requestProjectDTO.getId()+" does not exist";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<GetProjectDTO> getProject(int projectId) {
        var serviceResponse = new APIResponse<GetProjectDTO>();
        Optional<Project> responseProject = _projectRepo.findById(projectId);
        if(responseProject.isPresent()){
            serviceResponse.Data=_mapper.map(responseProject.get(), GetProjectDTO.class );
        }else{
            serviceResponse.Data=null;
            serviceResponse.status=false;
            serviceResponse.message="Class with id "+ projectId + " does not exists";
        }
        return serviceResponse;
    }
}
