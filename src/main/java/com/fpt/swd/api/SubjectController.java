package com.fpt.swd.api;


import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.business.SubjectBusiness.SubjectBusiness;
import com.fpt.swd.database.dto.Subject.AddSubjectDTO;
import com.fpt.swd.database.dto.Subject.GetSubjectDTO;
import com.fpt.swd.database.dto.Subject.UpdateSubjectDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fpt.swd.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@RequestMapping(path = "/api/subjects/")
public class SubjectController{
    private final SubjectBusiness _subjectBusiness;

    @Autowired
    public SubjectController(SubjectBusiness _subjectBusiness) {
        this._subjectBusiness = _subjectBusiness;
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetSubjectDTO>>> GetAllSubject(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                              @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize ){
        return new ResponseEntity<>(_subjectBusiness.getAllSubject(pageNo,pageSize), HttpStatus.OK);
    }

    @GetMapping(path = "GetSubject/{subjectId}")
    public ResponseEntity<APIResponse<GetSubjectDTO>> GetSubject(@PathVariable("subjectId") int subjectId){
        return new ResponseEntity<>(_subjectBusiness.getSubject(subjectId),HttpStatus.OK);
    }

    @Transactional
    @PostMapping(path = "AddNew")
    public ResponseEntity<APIResponse<Iterable<GetSubjectDTO>>> AddNewSubject(@RequestBody AddSubjectDTO newSubject){
        return new ResponseEntity<>(_subjectBusiness.addNewSubject(newSubject),HttpStatus.CREATED);
    }

    @PutMapping(path = "Update")
    public ResponseEntity<APIResponse<GetSubjectDTO>> UpdateSubject(@RequestBody UpdateSubjectDTO newSubject){
        return new ResponseEntity<>(_subjectBusiness.updateSubject(newSubject),HttpStatus.OK);
    }

    @DeleteMapping(path = "Delete/{subjectId}")
    public ResponseEntity<APIResponse<Iterable<GetSubjectDTO>>> DeleteSubject(@PathVariable("subjectId") int subjectId){
        return new ResponseEntity<>(_subjectBusiness.removeSubject(subjectId),HttpStatus.OK);
    }
}
