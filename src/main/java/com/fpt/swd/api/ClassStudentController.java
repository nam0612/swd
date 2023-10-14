package com.fpt.swd.api;

import com.fpt.swd.business.ClassStudentBusiness.IClassStudentBusiness;
import com.fpt.swd.database.dto.ClassStudent.AddNewClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.GetClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.UpdateClassStudentDto;
import com.fpt.swd.database.entity.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/ClassStudent")
public class ClassStudentController {
    private final IClassStudentBusiness _classStudentBusiness;


    @Autowired
    public ClassStudentController(IClassStudentBusiness classStudentBusiness) {
        this._classStudentBusiness = classStudentBusiness;
    }

    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetClassStudentDto>>> GetAllClassStudent() {
        return new ResponseEntity<>(_classStudentBusiness.GetAllClassStudent(), HttpStatus.OK);
    }

    @GetMapping(path = "GetClassStudent/{classStudentId}")
    public ResponseEntity<APIResponse<GetClassStudentDto>> GetClassStudent(@PathVariable("classStudentId") int classStudentId) {
        return new ResponseEntity<>(_classStudentBusiness.GetClassStudent(classStudentId), HttpStatus.OK);
    }

    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetClassStudentDto>>> AddNewStudent(@RequestBody AddNewClassStudentDto newClassStudent) {
        return new ResponseEntity<>(_classStudentBusiness.AddNewClassStudent(newClassStudent), HttpStatus.CREATED);
    }

    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetClassStudentDto>> UpdateStudent(@RequestBody UpdateClassStudentDto newClassStudent) {
        return new ResponseEntity<>(_classStudentBusiness.UpdateClassStudent(newClassStudent), HttpStatus.OK);
    }

    @DeleteMapping(path = "Delete/{classStudentId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetClassStudentDto>>> DeleteClass(@PathVariable("classStudentId") int classStudentId) {
        return new ResponseEntity<>(_classStudentBusiness.RemoveClassStudent(classStudentId), HttpStatus.OK);
    }
}
