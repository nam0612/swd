package com.fpt.swd.api;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.business.IssueBusiness.IIssueBusiness;
import com.fpt.swd.database.dto.Issue.AddNewIssueDto;
import com.fpt.swd.database.dto.Issue.GetIssueDto;
import com.fpt.swd.database.dto.Issue.GetIssueVer2Dto;
import com.fpt.swd.database.dto.Issue.UpdateIssueDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fpt.swd.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@RequestMapping(path = "/api/issue")
public class IssueController {
    private final IIssueBusiness _issueBusiness;
    @Autowired
    public IssueController(IIssueBusiness issueBusiness){
        _issueBusiness = issueBusiness;
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetIssueVer2Dto>>> getAllIssue
            (@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo, @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize){
        return new ResponseEntity<>(_issueBusiness.GetAllIssue(pageNo, pageSize), HttpStatus.OK);
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping(path="GetIssue/{issueId}")
    public ResponseEntity<APIResponse<GetIssueDto>> GetIssue(@PathVariable("issueId") int issueId){
        return new ResponseEntity<>(_issueBusiness.GetIssue(issueId), HttpStatus.OK);
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetIssueDto>>> AddNewIssue(@RequestBody AddNewIssueDto newIssue){
        return new ResponseEntity<>(_issueBusiness.AddNewIssue(newIssue), HttpStatus.CREATED);
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetIssueDto>> UpdateIssue(@RequestBody UpdateIssueDto newIssue){
        return new ResponseEntity<>(_issueBusiness.UpdateIssue(newIssue), HttpStatus.OK);
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping(path = "Delete/{issueId}")
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetIssueDto>>> DeleteIssue(@PathVariable("issueId") int issueId){
        return new ResponseEntity<>(_issueBusiness.RemoveIssue(issueId), HttpStatus.OK);
    }
}
