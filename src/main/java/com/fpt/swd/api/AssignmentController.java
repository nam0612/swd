package com.fpt.swd.api;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.business.AssignmentBusiness.IAssignmentBusiness;
import com.fpt.swd.database.dto.Assignment.AddNewAssignmentDto;
import com.fpt.swd.database.dto.Assignment.GetAssignmentDto;
import com.fpt.swd.database.dto.Assignment.UpdateAssignmentDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/assignments")
public class AssignmentController {
    private final IAssignmentBusiness _assignmentBusiness;

    @Autowired
    public AssignmentController(IAssignmentBusiness assignmentBusiness) {
        _assignmentBusiness = assignmentBusiness;
    }

    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetAssignmentDto>>> GetAllClasses(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                                 @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        return new ResponseEntity<>(_assignmentBusiness.GetAllAssignment(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping(path = "GetAssignment/{assignmentId}")
    public ResponseEntity<APIResponse<GetAssignmentDto>> GetClass(@PathVariable("assignmentId") int assignmentId) {
        return new ResponseEntity<>(_assignmentBusiness.GetAssignment(assignmentId), HttpStatus.OK);
    }

    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetAssignmentDto>>> AddNewClass(@Valid @RequestBody AddNewAssignmentDto newClass) {
        return new ResponseEntity<>(_assignmentBusiness.AddNewAssignment(newClass), HttpStatus.CREATED);
    }

    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetAssignmentDto>> UpdateClass(@Valid @RequestBody UpdateAssignmentDto newClass) {
        return new ResponseEntity<>(_assignmentBusiness.UpdateAssignment(newClass), HttpStatus.OK);
    }

    @DeleteMapping(path = "Delete/{assignmentId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetAssignmentDto>>> DeleteClass(@PathVariable("assignmentId") int assignmentId) {
        return new ResponseEntity<>(_assignmentBusiness.RemoveAssignment(assignmentId), HttpStatus.OK);
    }
}
