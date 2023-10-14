package com.fpt.swd.api;

import com.fpt.swd.business.ClassBusiness.IClassBusiness;
import com.fpt.swd.database.dto.Class.AddNewClasDto;
import com.fpt.swd.database.dto.Class.GetClassDto;
import com.fpt.swd.database.dto.Class.UpdateClassDto;
import com.fpt.swd.database.entity.APIResponse;
import com.fpt.swd.database.entity.Class;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/classes")
public class ClassController extends BaseController {
    private final IClassBusiness _classBusiness;


    @Autowired
    public ClassController(IClassBusiness classBusiness) {
        this._classBusiness = classBusiness;
    }

    @GetMapping("GetAll")
    public ResponseEntity<APIResponse<Iterable<GetClassDto>>> GetAllClasses() {
        return new ResponseEntity<>(_classBusiness.GetAllClass(), HttpStatus.OK);
    }

    @GetMapping(path = "GetClass/{classId}")
    public ResponseEntity<APIResponse<GetClassDto>> GetClass(@PathVariable("classId") int classId) {
        return new ResponseEntity<>(_classBusiness.GetClass(classId), HttpStatus.OK);
    }

    @PostMapping("AddNew")
    public ResponseEntity<APIResponse<Iterable<GetClassDto>>> AddNewClass(@RequestBody AddNewClasDto newClass) {
        return new ResponseEntity<>(_classBusiness.AddNewClass(newClass), HttpStatus.CREATED);
    }

    @PutMapping("Update")
    public ResponseEntity<APIResponse<GetClassDto>> UpdateClass(@RequestBody UpdateClassDto newClass) {
        return new ResponseEntity<>(_classBusiness.UpdateClass(newClass), HttpStatus.OK);
    }

    @DeleteMapping(path = "Delete/{classId}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<Iterable<GetClassDto>>> DeleteClass(@PathVariable("classId") int classId) {
        return new ResponseEntity<>(_classBusiness.RemoveClass(classId), HttpStatus.OK);
    }
}
