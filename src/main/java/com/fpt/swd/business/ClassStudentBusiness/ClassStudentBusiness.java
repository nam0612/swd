package com.fpt.swd.business.ClassStudentBusiness;

import com.fpt.swd.database.dto.ClassStudent.AddNewClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.GetClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.GetClassStudentVer2Dto;
import com.fpt.swd.database.dto.ClassStudent.UpdateClassStudentDto;
import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.entity.ClassStudent;
import com.fpt.swd.database.repo.IClassStudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassStudentBusiness implements IClassStudentBusiness {
    private final IClassStudentRepository _classStudentRepository;
    private final ModelMapper _mapper;

    @Autowired
    public ClassStudentBusiness(IClassStudentRepository classStudentRepository, ModelMapper mapper) {
        this._classStudentRepository = classStudentRepository;
        _mapper = mapper;
    }

    @Override
    public APIResponse<Iterable<GetClassStudentVer2Dto>> GetAllClassStudent(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<GetClassStudentVer2Dto> dbClassStudent = _classStudentRepository.findAllList(pageable);
        var serviceResponse = new APIResponse<Iterable<GetClassStudentVer2Dto>>();
        serviceResponse.Data = dbClassStudent.getContent().stream().map(cs -> _mapper.map(cs, GetClassStudentVer2Dto.class)).toList();
        serviceResponse.pagination.pageNo = dbClassStudent.getNumber();
        serviceResponse.pagination.pageSize = dbClassStudent.getSize();
        serviceResponse.pagination.totalElements = dbClassStudent.getTotalElements();
        serviceResponse.pagination.totalPages = dbClassStudent.getTotalPages();
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetClassStudentDto>> AddNewClassStudent(AddNewClassStudentDto requestClassStudentDto) {
        var serviceResponse = new APIResponse<Iterable<GetClassStudentDto>>();
        ClassStudent newClassStudent = _mapper.map(requestClassStudentDto, ClassStudent.class);
        newClassStudent.setCreated_date(new Date());
        _classStudentRepository.save(newClassStudent);
        serviceResponse.Data = _classStudentRepository.findAll().stream().map(cs -> _mapper.map(cs, GetClassStudentDto.class)).toList();
        serviceResponse.pagination = null;
        return serviceResponse;
    }

    @Override
    public APIResponse<GetClassStudentDto> UpdateClassStudent(UpdateClassStudentDto requestClassStudentDto) {
        var serviceResponse = new APIResponse<GetClassStudentDto>();
        serviceResponse.pagination = null;
        Optional<ClassStudent> findClassStudent = _classStudentRepository.findById(requestClassStudentDto.getId());
        if (findClassStudent.isPresent()) {
            ClassStudent existingClassStudent = findClassStudent.get();
            existingClassStudent.setUpdated_date(new Date());
            _mapper.map(requestClassStudentDto, existingClassStudent);
            _classStudentRepository.save(existingClassStudent);
            serviceResponse.Data = _mapper.map(existingClassStudent, GetClassStudentDto.class);
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "The ClassStudent does not exist !!!";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<GetClassStudentVer2Dto> GetClassStudent(int classStudentId) {
        var serviceResponse = new APIResponse<GetClassStudentVer2Dto>();
        serviceResponse.pagination = null;
        Optional<GetClassStudentVer2Dto> responseClassStudent = _classStudentRepository.findByUserClass(classStudentId);
        if (responseClassStudent.isPresent()) {
            serviceResponse.Data = _mapper.map(responseClassStudent.get(), GetClassStudentVer2Dto.class);
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "ClassStudent with id " + classStudentId + " does not exists";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetClassStudentDto>> RemoveClassStudent(int classStudentId) {
        var serviceResponse = new APIResponse<Iterable<GetClassStudentDto>>();
        serviceResponse.pagination = null;
        Optional<ClassStudent> responseClassStudent = _classStudentRepository.findById(classStudentId);
        if (responseClassStudent.isPresent()) {
            ClassStudent classStudentObj = responseClassStudent.get();
            _classStudentRepository.delete(classStudentObj);
            serviceResponse.Data = _classStudentRepository.findAll().stream().map(cs -> _mapper.map(cs, GetClassStudentDto.class)).toList();
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "ClassStudent with id " + classStudentId + " does not exists";
        }
        return serviceResponse;
    }
}
