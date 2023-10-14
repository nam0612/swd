package com.fpt.swd.business.ClassBusiness;

import com.fpt.swd.database.dto.Class.AddNewClasDto;
import com.fpt.swd.database.dto.Class.GetClassDto;
import com.fpt.swd.database.dto.Class.UpdateClassDto;
import com.fpt.swd.database.entity.APIResponse;

public interface IClassBusiness {

    APIResponse<Iterable<GetClassDto>> GetAllClass();

    APIResponse<Iterable<GetClassDto>> AddNewClass(AddNewClasDto requestClassDto);

     APIResponse<GetClassDto> UpdateClass(UpdateClassDto requestClassDto);

     APIResponse<GetClassDto> GetClass(int classId);

     APIResponse<Iterable<GetClassDto>> RemoveClass(int classId);
}
