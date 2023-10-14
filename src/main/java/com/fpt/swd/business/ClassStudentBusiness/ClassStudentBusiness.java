package com.fpt.swd.business.ClassStudentBusiness;

import com.fpt.swd.database.dto.ClassStudent.AddNewClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.GetClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.UpdateClassStudentDto;
import com.fpt.swd.database.entity.APIResponse;
import com.fpt.swd.database.entity.ClassStudent;
import com.fpt.swd.database.repo.IClassStudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public APIResponse<Iterable<GetClassStudentDto>> GetAllClassStudent() {
        var dbClassStudent = _classStudentRepository.findAll();
        var serviceResponse = new APIResponse<Iterable<GetClassStudentDto>>();
        serviceResponse.Data = dbClassStudent.stream().map(cs -> _mapper.map(cs, GetClassStudentDto.class)).toList();
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetClassStudentDto>> AddNewClassStudent(AddNewClassStudentDto requestClassStudentDto) {
        var serviceResponse = new APIResponse<Iterable<GetClassStudentDto>>();
        ClassStudent newClassStudent = _mapper.map(requestClassStudentDto, ClassStudent.class);
        _classStudentRepository.save(newClassStudent);
        serviceResponse.Data = _classStudentRepository.findAll().stream().map(cs -> _mapper.map(cs, GetClassStudentDto.class)).toList();
        return serviceResponse;
    }

    @Override
    public APIResponse<GetClassStudentDto> UpdateClassStudent(UpdateClassStudentDto requestClassStudentDto) {
        var serviceResponse = new APIResponse<GetClassStudentDto>();
        Optional<ClassStudent> findClassStudent = _classStudentRepository.findById(requestClassStudentDto.getId());
        if (findClassStudent.isPresent()) {
            ClassStudent existingClassStudent = findClassStudent.get();
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
    public APIResponse<GetClassStudentDto> GetClassStudent(int classStudentId) {
        var serviceResponse = new APIResponse<GetClassStudentDto>();
        Optional<ClassStudent> responseClassStudent = _classStudentRepository.findById(classStudentId);
        if (responseClassStudent.isPresent()) {
            serviceResponse.Data = _mapper.map(responseClassStudent.get(), GetClassStudentDto.class);
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
