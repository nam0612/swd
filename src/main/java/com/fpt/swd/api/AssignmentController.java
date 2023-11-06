package com.fpt.swd.api;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.business.AssignmentBusiness.IAssignmentBusiness;
import com.fpt.swd.database.dto.Assignment.AddNewAssignmentDto;
import com.fpt.swd.database.dto.Assignment.GetAssignmentDto;
import com.fpt.swd.database.dto.Assignment.GetAssignmentVer2Dto;
import com.fpt.swd.database.dto.Assignment.UpdateAssignmentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fpt.swd.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@RequestMapping(path = "/api/assignments")
public class AssignmentController {
    private final IAssignmentBusiness _assignmentBusiness;

    @Autowired
    public AssignmentController(IAssignmentBusiness assignmentBusiness) {
        _assignmentBusiness = assignmentBusiness;
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetAssignmentVer2Dto>>> GetAllClasses(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                                     @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        return new ResponseEntity<>(_assignmentBusiness.GetAllAssignment(pageNo, pageSize), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping(path = "GetAssignment/{assignmentId}")
    public ResponseEntity<APIResponse<GetAssignmentVer2Dto>> GetClass(@PathVariable("assignmentId") int assignmentId) {
        return new ResponseEntity<>(_assignmentBusiness.GetAssignment(assignmentId), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetAssignmentDto>>> AddNewClass(@Valid @RequestBody AddNewAssignmentDto newClass) {
        return new ResponseEntity<>(_assignmentBusiness.AddNewAssignment(newClass), HttpStatus.CREATED);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetAssignmentDto>> UpdateClass(@Valid @RequestBody UpdateAssignmentDto newClass) {
        return new ResponseEntity<>(_assignmentBusiness.UpdateAssignment(newClass), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping(path = "Delete/{assignmentId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetAssignmentDto>>> DeleteClass(@PathVariable("assignmentId") int assignmentId) {
        return new ResponseEntity<>(_assignmentBusiness.RemoveAssignment(assignmentId), HttpStatus.OK);
    }
}
