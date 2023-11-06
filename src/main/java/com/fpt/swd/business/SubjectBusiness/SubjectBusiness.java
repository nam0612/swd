package com.fpt.swd.business.SubjectBusiness;

import com.fpt.swd.database.dto.Project.GetProjectDTO;
import com.fpt.swd.database.dto.Subject.AddSubjectDTO;
import com.fpt.swd.database.dto.Subject.GetSubjectDTO;
import com.fpt.swd.database.dto.Subject.UpdateSubjectDTO;
import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.entity.Project;
import com.fpt.swd.database.entity.Subject;
import com.fpt.swd.database.repo.SubjectRepo;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SubjectBusiness implements ISubjectBusiness{

    private final SubjectRepo _subjectRepo;
    private final ModelMapper _mapper;

    public SubjectBusiness(SubjectRepo _subjectRepo, ModelMapper _mapper) {
        this._subjectRepo = _subjectRepo;
        this._mapper = _mapper;
    }

    @Override
    public APIResponse<Iterable<GetSubjectDTO>> getAllSubject(int pageNo, int pageSize) {
        Page<Subject> listFromDb=_subjectRepo.findAll(PageRequest.of(pageNo,pageSize));
        var responseService= new APIResponse<Iterable<GetSubjectDTO>>();
        responseService.Data=listFromDb.getContent().stream().map(c->_mapper.map(c, GetSubjectDTO.class)).toList();
        responseService.pagination.pageNo = listFromDb.getNumber();
        responseService.pagination.pageSize = listFromDb.getSize();
        responseService.pagination.totalElements = listFromDb.getTotalElements();
        responseService.pagination.totalPages = listFromDb.getTotalPages();
        return responseService;
    }

    @Override
    public APIResponse<Iterable<GetSubjectDTO>> addNewSubject(AddSubjectDTO requestSubjectDTO) {
        var responseService= new APIResponse<Iterable<GetSubjectDTO>>();
        _subjectRepo.save(_mapper.map(requestSubjectDTO, Subject.class));
        var takeFromDb=_subjectRepo.findAll();
        responseService.Data=takeFromDb.stream().map(c->_mapper.map(c,GetSubjectDTO.class)).toList();
        return responseService;
    }

    @Override
    public APIResponse<Iterable<GetSubjectDTO>> removeSubject(int subjectId) {
        var responseService = new APIResponse<Iterable<GetSubjectDTO>>();
        var ListFromDb =_subjectRepo.findAll();
        Optional<Subject> deleteSubject= _subjectRepo.findById(subjectId);
        if(deleteSubject.isPresent()){
            Subject subject= deleteSubject.get();
            _subjectRepo.delete(subject);
            responseService.Data=ListFromDb.stream().map(c->_mapper.map(c,GetSubjectDTO.class)).toList();
            return responseService;
        }else {
            responseService.Data=null;
            responseService.message="Subject with id "+subjectId+" does not exist!";
            responseService.status=false;
        }
    return responseService;
    }

    @Override
    public APIResponse<GetSubjectDTO> updateSubject(UpdateSubjectDTO requestSubjectDTO) {
        var responseService=new APIResponse<GetSubjectDTO>();

        Optional<Subject> subjectFromDb=_subjectRepo.findById(requestSubjectDTO.getId());
        if(subjectFromDb.isPresent()){
            Subject existedSubject=subjectFromDb.get();
            _mapper.map(requestSubjectDTO,existedSubject);
            _subjectRepo.save(existedSubject);
            return responseService;
        }
        else{
            responseService.Data=null;
            responseService.message="Subject with id "+requestSubjectDTO.getId()+" does not exist!";
            responseService.status=false;
        }
        return responseService;
    }

    @Override
    public APIResponse<GetSubjectDTO> getSubject(int subjectId) {
        var responseEntity= new APIResponse<GetSubjectDTO>();
        Optional<Subject> subjectFromDb= _subjectRepo.findById(subjectId);
        if(subjectFromDb.isPresent()){
            responseEntity.Data=_mapper.map(subjectFromDb.get(),GetSubjectDTO.class);
            return responseEntity;
        }
        else{
            responseEntity.Data=null;
            responseEntity.message="Subject with id "+subjectId+" does not exist!";
            responseEntity.status=false;
        }
        return responseEntity;
    }
}
