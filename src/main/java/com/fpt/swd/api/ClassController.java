package com.fpt.swd.api;

import com.fpt.swd.business.ClassBusiness.IClassBusiness;
import com.fpt.swd.database.dto.Class.AddNewClasDto;
import com.fpt.swd.database.dto.Class.GetClassDto;
import com.fpt.swd.database.dto.Class.UpdateClassDto;
import com.fpt.swd.Response.APIResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/classes")
public class ClassController {
    private final IClassBusiness _classBusiness;


    @Autowired
    public ClassController(IClassBusiness classBusiness) {
        this._classBusiness = classBusiness;
    }

    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetClassDto>>> GetAllClasses(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        return new ResponseEntity<>(_classBusiness.GetAllClass(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping(path = "GetClass/{classId}")
    public ResponseEntity<APIResponse<GetClassDto>> GetClass(@PathVariable("classId") int classId) {
        return new ResponseEntity<>(_classBusiness.GetClass(classId), HttpStatus.OK);
    }

    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetClassDto>>> AddNewClass(@Valid @RequestBody AddNewClasDto newClass) {
        return new ResponseEntity<>(_classBusiness.AddNewClass(newClass), HttpStatus.CREATED);
    }

    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetClassDto>> UpdateClass(@Valid @RequestBody UpdateClassDto newClass) {
        return new ResponseEntity<>(_classBusiness.UpdateClass(newClass), HttpStatus.OK);
    }

    @DeleteMapping(path = "Delete/{classId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetClassDto>>> DeleteClass(@PathVariable("classId") int classId) {
        return new ResponseEntity<>(_classBusiness.RemoveClass(classId), HttpStatus.OK);
    }
}
