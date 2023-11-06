package com.fpt.swd.api;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.business.ProjectMemberBusiness.IProjectMemberBusiness;
import com.fpt.swd.database.dto.ProjectMember.AddProjectMemberDTO;
import com.fpt.swd.database.dto.ProjectMember.GetProjectMemberDTO;
import com.fpt.swd.database.dto.ProjectMember.UpdateProjectMemberDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/project_member")
public class ProjectMemberController {
    private final IProjectMemberBusiness _projectMemberBussiness;

    @Autowired
    public ProjectMemberController(IProjectMemberBusiness _projectMemberBussiness) {
        this._projectMemberBussiness = _projectMemberBussiness;
    }


    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetProjectMemberDTO>>> GetAll(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                             @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize  ){
        return new ResponseEntity<>(_projectMemberBussiness.getAllProjectMember(pageNo,pageSize), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetProjectMemberDTO>>> AddNewProjectMember(@RequestBody AddProjectMemberDTO addPM){
        return new ResponseEntity<>(_projectMemberBussiness.addNewProjectMember(addPM),HttpStatus.CREATED);
    }

    @GetMapping("GetProjectMember/{projectMemberId}")
    public ResponseEntity<APIResponse<GetProjectMemberDTO>> GetProjectMember(@PathVariable("projectMemberId") int projectMemberId){
        return new ResponseEntity<>(_projectMemberBussiness.getProjectMember(projectMemberId),HttpStatus.OK);
    }

    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetProjectMemberDTO>> UpdateProjectMember(@RequestBody UpdateProjectMemberDTO updatePM){
        return new ResponseEntity<>(_projectMemberBussiness.updateProjectMember(updatePM),HttpStatus.OK);
    }

    @DeleteMapping("Delete/{projectMemberId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetProjectMemberDTO>>> DeleteProjectMember(@PathVariable("projectMemberId") int projectMemberId){
        return new ResponseEntity<>(_projectMemberBussiness.removeProjectMember(projectMemberId),HttpStatus.OK);
    }
}
