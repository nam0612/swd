package com.fpt.swd.business.ClassBusiness;

import com.fpt.swd.database.dto.Class.AddNewClasDto;
import com.fpt.swd.database.dto.Class.GetClassDto;
import com.fpt.swd.database.dto.Class.UpdateClassDto;
import com.fpt.swd.database.entity.APIResponse;
import com.fpt.swd.database.entity.Class;
import com.fpt.swd.database.repo.IClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public APIResponse<Iterable<GetClassDto>> GetAllClass() {
        var dbClass = _classRepository.findAll();
        var serviceResponse = new APIResponse<Iterable<GetClassDto>>();
        serviceResponse.Data = dbClass.stream().map(c -> _mapper.map(c, GetClassDto.class)).toList();
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetClassDto>> AddNewClass(AddNewClasDto requestClassDto) {
        var serviceResponse = new APIResponse<Iterable<GetClassDto>>();
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
        Optional<Class> findClass = _classRepository.findClassById(requestClassDto.getId());
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
    public APIResponse<GetClassDto> GetClass(int classId) {
        var serviceResponse = new APIResponse<GetClassDto>();
        Optional<Class> responseClass = _classRepository.findClassById(classId);
        if (responseClass.isPresent()) {
            serviceResponse.Data = _mapper.map(responseClass.get(), GetClassDto.class);
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
        Optional<Class> responseClass = _classRepository.findClassById(classId);
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
