package com.fpt.swd.api;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.business.ProjectBusiness.IProjectBusiness;
import com.fpt.swd.database.dto.Project.AddProjectDTO;
import com.fpt.swd.database.dto.Project.GetProjectDTO;
import com.fpt.swd.database.dto.Project.UpdateProjectDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/api/projects")
public class ProjectController{
    private final IProjectBusiness _projectBusiness;
    @Autowired
    public ProjectController(IProjectBusiness _projectBusiness) {
        this._projectBusiness = _projectBusiness;
    }

    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetProjectDTO>>> GetAllProject(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                              @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize  ){
        return new ResponseEntity<>(_projectBusiness.getAllProject(pageNo,pageSize), HttpStatus.OK);
    }

    @GetMapping(path = "GetProject/{projectId}")
    public ResponseEntity<APIResponse<GetProjectDTO>> GetProject(@PathVariable("projectId") int projectId) {
        return new ResponseEntity<>(_projectBusiness.getProject(projectId), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetProjectDTO>>> AddProject(@RequestBody AddProjectDTO newProject)  {
        return new ResponseEntity<>(_projectBusiness.addNewProject(newProject), HttpStatus.CREATED);
    }

    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetProjectDTO>> UpdateProject(@RequestBody UpdateProjectDTO updateProject){
        return new ResponseEntity<>(_projectBusiness.updateProject(updateProject),HttpStatus.OK);
    }

    @DeleteMapping(path = "Delete/{projectId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetProjectDTO>>> DeleteProject(@PathVariable("projectId") int projectId){
        return new ResponseEntity<>(_projectBusiness.removeProject(projectId),HttpStatus.OK);
    }
}
