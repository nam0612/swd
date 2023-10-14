package com.fpt.swd.api;

import com.fpt.swd.business.StudentBusiness.IStudentBusiness;
import com.fpt.swd.database.dto.Student.AddNewStudentDto;
import com.fpt.swd.database.dto.Student.GetStudentDto;
import com.fpt.swd.database.dto.Student.UpdateStudentDto;
import com.fpt.swd.database.entity.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {
    private final IStudentBusiness _studentBusiness;


    @Autowired
    public StudentController(IStudentBusiness studentBusiness) {
        this._studentBusiness = studentBusiness;
    }

    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetStudentDto>>> GetAllStudents() {
        return new ResponseEntity<>(_studentBusiness.GetAllStudent(), HttpStatus.OK);
    }

    @GetMapping(path = "GetStudents/{studentId}")
    public ResponseEntity<APIResponse<GetStudentDto>> GetStudent(@PathVariable("studentId") int studentId) {
        return new ResponseEntity<>(_studentBusiness.GetStudent(studentId), HttpStatus.OK);
    }

    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetStudentDto>>> AddNewStudent(@RequestBody AddNewStudentDto newStudent) {
        return new ResponseEntity<>(_studentBusiness.AddNewStudent(newStudent), HttpStatus.CREATED);
    }

    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetStudentDto>> UpdateStudent(@RequestBody UpdateStudentDto newStudent) {
        return new ResponseEntity<>(_studentBusiness.UpdateStudent(newStudent), HttpStatus.OK);
    }

    @DeleteMapping(path = "Delete/{studentId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetStudentDto>>> DeleteClass(@PathVariable("studentId") int studentId) {
        return new ResponseEntity<>(_studentBusiness.RemoveStudent(studentId), HttpStatus.OK);
    }
}
