package com.fpt.swd.api;

import com.fpt.swd.business.ClassBusiness.IClassBusiness;
import com.fpt.swd.database.dto.Class.AddNewClasDto;
import com.fpt.swd.database.dto.Class.GetClassDto;
import com.fpt.swd.database.dto.Class.GetClassVer2Dto;
import com.fpt.swd.database.dto.Class.UpdateClassDto;
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
@RequestMapping(path = "/api/classes")
public class ClassController {
    private final IClassBusiness _classBusiness;


    @Autowired
    public ClassController(IClassBusiness classBusiness) {
        this._classBusiness = classBusiness;
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetClassVer2Dto>>> GetAllClasses(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                                @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        return new ResponseEntity<>(_classBusiness.GetAllClass(pageNo, pageSize), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping(path = "GetClass/{classId}")
    public ResponseEntity<APIResponse<GetClassVer2Dto>> GetClass(@PathVariable("classId") int classId) {
        return new ResponseEntity<>(_classBusiness.GetClass(classId), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetClassDto>>> AddNewClass(@Valid @RequestBody AddNewClasDto newClass) {
        return new ResponseEntity<>(_classBusiness.AddNewClass(newClass), HttpStatus.CREATED);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetClassDto>> UpdateClass(@Valid @RequestBody UpdateClassDto newClass) {
        return new ResponseEntity<>(_classBusiness.UpdateClass(newClass), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping(path = "Delete/{classId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetClassDto>>> DeleteClass(@PathVariable("classId") int classId) {
        return new ResponseEntity<>(_classBusiness.RemoveClass(classId), HttpStatus.OK);
    }
}
