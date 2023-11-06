package com.fpt.swd.api;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.business.ProjectMemberBusiness.IProjectMemberBusiness;
import com.fpt.swd.database.dto.ProjectMember.AddProjectMemberDTO;
import com.fpt.swd.database.dto.ProjectMember.GetProjectMemberDTO;
import com.fpt.swd.database.dto.ProjectMember.ProjectMemberJoinDTO;
import com.fpt.swd.database.dto.ProjectMember.UpdateProjectMemberDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fpt.swd.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@RequestMapping(path = "/api/project_member")
public class ProjectMemberController {
    private final IProjectMemberBusiness _projectMemberBussiness;

    @Autowired
    public ProjectMemberController(IProjectMemberBusiness _projectMemberBussiness) {
        this._projectMemberBussiness = _projectMemberBussiness;
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<ProjectMemberJoinDTO>>> GetAll(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                              @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize  ){
        return new ResponseEntity<>(_projectMemberBussiness.getAllProjectMember(pageNo,pageSize), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @Transactional
    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetProjectMemberDTO>>> AddNewProjectMember(@RequestBody AddProjectMemberDTO addPM) {
        return new ResponseEntity<>(_projectMemberBussiness.addNewProjectMember(addPM), HttpStatus.CREATED);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("GetProjectMember/{projectMemberId}")
    public ResponseEntity<APIResponse<ProjectMemberJoinDTO>> GetProjectMember(@PathVariable("projectMemberId") int projectMemberId){
        return new ResponseEntity<>(_projectMemberBussiness.getProjectMember(projectMemberId),HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetProjectMemberDTO>> UpdateProjectMember(@RequestBody UpdateProjectMemberDTO updatePM) {
        return new ResponseEntity<>(_projectMemberBussiness.updateProjectMember(updatePM), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping("Delete/{projectMemberId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetProjectMemberDTO>>> DeleteProjectMember(@PathVariable("projectMemberId") int projectMemberId) {
        return new ResponseEntity<>(_projectMemberBussiness.removeProjectMember(projectMemberId), HttpStatus.OK);
    }

}
