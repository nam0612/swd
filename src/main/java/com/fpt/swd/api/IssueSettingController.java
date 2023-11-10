package com.fpt.swd.api;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.business.IssueSettingBusiness.IIssueSettingBusiness;
import com.fpt.swd.database.dto.IssueSetting.AddNewIssueSettingDto;
import com.fpt.swd.database.dto.IssueSetting.GetIssueSettingDto;
import com.fpt.swd.database.dto.IssueSetting.GetIssueSettingVer2Dto;
import com.fpt.swd.database.dto.IssueSetting.UpdateIssueSettingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fpt.swd.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@RequestMapping(path = "/api/issueSetting")
public class IssueSettingController {
    private final IIssueSettingBusiness _issueSettingBusiness;
    @Autowired
    public IssueSettingController(IIssueSettingBusiness issueBusiness){
        _issueSettingBusiness = issueBusiness;
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetIssueSettingVer2Dto>>> getAllIssueSetting
            (@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo, @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize){
        return new ResponseEntity<>(_issueSettingBusiness.GetAllIssueSetting(pageNo, pageSize), HttpStatus.OK);
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping(path="GetIssue/{issueSettingId}")
    public ResponseEntity<APIResponse<GetIssueSettingDto>> GetIssueSetting(@PathVariable("issueSettingId") int issueSettingId){
        return new ResponseEntity<>(_issueSettingBusiness.GetIssueSetting(issueSettingId), HttpStatus.OK);
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetIssueSettingDto>>> AddNewIssueSetting(@RequestBody AddNewIssueSettingDto newIssueSetting){
        return new ResponseEntity<>(_issueSettingBusiness.AddNewIssueSetting(newIssueSetting), HttpStatus.CREATED);
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetIssueSettingDto>> UpdateIssueSetting(@RequestBody UpdateIssueSettingDto newIssueSetting){
        return new ResponseEntity<>(_issueSettingBusiness.UpdateIssueSetting(newIssueSetting), HttpStatus.OK);
    }
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping(path = "Delete/{issueSettingId}")
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetIssueSettingDto>>> DeleteIssueSetting(@PathVariable("issueSettingId") int issueSettingId){
        return new ResponseEntity<>(_issueSettingBusiness.RemoveIssueSetting(issueSettingId), HttpStatus.OK);
    }
}
