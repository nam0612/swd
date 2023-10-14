package com.fpt.swd.business.StudentBusiness;

import com.fpt.swd.database.dto.Student.AddNewStudentDto;
import com.fpt.swd.database.dto.Student.GetStudentDto;
import com.fpt.swd.database.dto.Student.UpdateStudentDto;
import com.fpt.swd.database.entity.APIResponse;
import com.fpt.swd.database.entity.Student;
import com.fpt.swd.database.repo.IStudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentBusiness implements IStudentBusiness {
    private final IStudentRepository _studentRepository;
    private final ModelMapper _mapper;

    @Autowired
    public StudentBusiness(IStudentRepository studentRepository, ModelMapper mapper) {
        this._studentRepository = studentRepository;
        _mapper = mapper;
    }

    @Override
    public APIResponse<Iterable<GetStudentDto>> GetAllStudent() {
        var dbStudent = _studentRepository.findAll();
        var serviceResponse = new APIResponse<Iterable<GetStudentDto>>();
        serviceResponse.Data = dbStudent.stream().map(s -> _mapper.map(s, GetStudentDto.class)).toList();
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetStudentDto>> AddNewStudent(AddNewStudentDto requestStudentDto) {
        var serviceResponse = new APIResponse<Iterable<GetStudentDto>>();
        Student newStudent = _mapper.map(requestStudentDto, Student.class);
        _studentRepository.save(newStudent);
        serviceResponse.Data = _studentRepository.findAll().stream().map(s -> _mapper.map(s, GetStudentDto.class)).toList();
        return serviceResponse;
    }

    @Override
    public APIResponse<GetStudentDto> UpdateStudent(UpdateStudentDto requestStudentDto) {
        var serviceResponse = new APIResponse<GetStudentDto>();
        Optional<Student> findStudent = _studentRepository.findById(requestStudentDto.getId());
        if (findStudent.isPresent()) {
            Student existingStudent = findStudent.get();
            _mapper.map(requestStudentDto, existingStudent);
            _studentRepository.save(existingStudent);
            serviceResponse.Data = _mapper.map(existingStudent, GetStudentDto.class);
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "The Student does not exist !!!";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<GetStudentDto> GetStudent(int studentId) {
        var serviceResponse = new APIResponse<GetStudentDto>();
        Optional<Student> responseStudent = _studentRepository.findById(studentId);
        if (responseStudent.isPresent()) {
            serviceResponse.Data = _mapper.map(responseStudent.get(), GetStudentDto.class);
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "Student with id " + studentId + " does not exists";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetStudentDto>> RemoveStudent(int studentId) {
        var serviceResponse = new APIResponse<Iterable<GetStudentDto>>();
        Optional<Student> responseStudent = _studentRepository.findById(studentId);
        if (responseStudent.isPresent()) {
            Student studentObj = responseStudent.get();
            _studentRepository.delete(studentObj);
            serviceResponse.Data = _studentRepository.findAll().stream().map(s -> _mapper.map(s, GetStudentDto.class)).toList();
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "Student with id " + studentId + " does not exists";
        }
        return serviceResponse;
    }
}
