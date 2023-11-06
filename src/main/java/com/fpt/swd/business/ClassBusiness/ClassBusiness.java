package com.fpt.swd.business.ClassBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.Class.AddNewClasDto;
import com.fpt.swd.database.dto.Class.GetClassDto;
import com.fpt.swd.database.dto.Class.GetClassVer2Dto;
import com.fpt.swd.database.dto.Class.UpdateClassDto;
import com.fpt.swd.database.entity.Class;
import com.fpt.swd.database.repo.IClassRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClassBusiness implements IClassBusiness {
    private final IClassRepository _classRepository;
    private final ModelMapper _mapper;

    @Autowired
    public ClassBusiness(IClassRepository classRepository, ModelMapper mapper) {
        this._classRepository = classRepository;
        _mapper = mapper;
    }

    @Override
    public APIResponse<Iterable<GetClassVer2Dto>> GetAllClass(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<GetClassVer2Dto> dbClass = _classRepository.findAllList(pageable);
        var serviceResponse = new APIResponse<Iterable<GetClassVer2Dto>>();
        serviceResponse.Data = dbClass.getContent().stream().map(c -> _mapper.map(c, GetClassVer2Dto.class)).toList();
        serviceResponse.pagination.pageNo = dbClass.getNumber();
        serviceResponse.pagination.pageSize = dbClass.getSize();
        serviceResponse.pagination.totalElements = dbClass.getTotalElements();
        serviceResponse.pagination.totalPages = dbClass.getTotalPages();
        return serviceResponse;
    }

    @Override
    @Transactional
    public APIResponse<Iterable<GetClassDto>> AddNewClass(AddNewClasDto requestClassDto) {
        var serviceResponse = new APIResponse<Iterable<GetClassDto>>();
        serviceResponse.pagination = null;
        var dbClass = _classRepository.findAll().stream().filter(c -> Objects.equals(c.getName(), requestClassDto.getName())).findFirst();
        if (dbClass.isPresent()) {
            serviceResponse.status = false;
            serviceResponse.message = "Class already exists !!!";
            serviceResponse.Data = null;
            return serviceResponse;
        }
        Class newClass = _mapper.map(requestClassDto, Class.class);
        _classRepository.save(newClass);
        serviceResponse.Data = _classRepository.findAll().stream().map(c -> _mapper.map(c, GetClassDto.class)).toList();
        return serviceResponse;
    }

    @Override
    public APIResponse<GetClassDto> UpdateClass(UpdateClassDto requestClassDto) {
        var serviceResponse = new APIResponse<GetClassDto>();
        serviceResponse.pagination = null;
        Optional<Class> findClass = _classRepository.findById(requestClassDto.getId());
        if (findClass.isPresent()) {
            Class existingClass = findClass.get();
            _mapper.map(requestClassDto, existingClass);
            _classRepository.save(existingClass);
            serviceResponse.Data = _mapper.map(existingClass, GetClassDto.class);
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "The class does not exist !!!";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<GetClassVer2Dto> GetClass(int classId) {
        var serviceResponse = new APIResponse<GetClassVer2Dto>();
        serviceResponse.pagination = null;
        Optional<GetClassVer2Dto> responseClass = _classRepository.findByUserSettingSubject(classId);
        serviceResponse.pagination = null;
        if (responseClass.isPresent()) {
            serviceResponse.Data = _mapper.map(responseClass.get(), GetClassVer2Dto.class);
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "Class with id " + classId + " does not exists";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetClassDto>> RemoveClass(int classId) {
        var serviceResponse = new APIResponse<Iterable<GetClassDto>>();
        serviceResponse.pagination = null;
        Optional<Class> responseClass = _classRepository.findById(classId);
        if (responseClass.isPresent()) {
            Class classObj = responseClass.get();
            _classRepository.delete(classObj);
            serviceResponse.Data = _classRepository.findAll().stream().map(c -> _mapper.map(c, GetClassDto.class)).toList();
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "Class with id " + classId + " does not exists";
        }
        return serviceResponse;
    }
}
