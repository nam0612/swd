package com.fpt.swd.api;

import com.fpt.swd.business.ClassStudentBusiness.IClassStudentBusiness;
import com.fpt.swd.database.dto.ClassStudent.AddNewClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.GetClassStudentDto;
import com.fpt.swd.database.dto.ClassStudent.GetClassStudentVer2Dto;
import com.fpt.swd.database.dto.ClassStudent.UpdateClassStudentDto;
import com.fpt.swd.Response.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fpt.swd.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@RequestMapping(path = "/api/ClassStudent")
public class ClassStudentController {
    private final IClassStudentBusiness _classStudentBusiness;

    @Autowired
    public ClassStudentController(IClassStudentBusiness classStudentBusiness) {
        this._classStudentBusiness = classStudentBusiness;
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetClassStudentVer2Dto>>> GetAllClassStudent(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                                            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        return new ResponseEntity<>(_classStudentBusiness.GetAllClassStudent(pageNo, pageSize), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping(path = "GetClassStudent/{classStudentId}")
    public ResponseEntity<APIResponse<GetClassStudentVer2Dto>> GetClassStudent(@PathVariable("classStudentId") int classStudentId) {
        return new ResponseEntity<>(_classStudentBusiness.GetClassStudent(classStudentId), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetClassStudentDto>>> AddNewStudent(@Valid @RequestBody AddNewClassStudentDto newClassStudent) {
        return new ResponseEntity<>(_classStudentBusiness.AddNewClassStudent(newClassStudent), HttpStatus.CREATED);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetClassStudentDto>> UpdateStudent(@Valid @RequestBody UpdateClassStudentDto newClassStudent) {
        return new ResponseEntity<>(_classStudentBusiness.UpdateClassStudent(newClassStudent), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping(path = "Delete/{classStudentId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetClassStudentDto>>> DeleteClass(@PathVariable("classStudentId") int classStudentId) {
        return new ResponseEntity<>(_classStudentBusiness.RemoveClassStudent(classStudentId), HttpStatus.OK);
    }
}
