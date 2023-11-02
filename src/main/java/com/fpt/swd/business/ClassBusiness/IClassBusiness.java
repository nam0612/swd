package com.fpt.swd.business.ClassBusiness;

import com.fpt.swd.database.dto.Class.AddNewClasDto;
import com.fpt.swd.database.dto.Class.GetClassDto;
import com.fpt.swd.database.dto.Class.UpdateClassDto;
import com.fpt.swd.Response.APIResponse;

public interface IClassBusiness {

    APIResponse<Iterable<GetClassDto>> GetAllClass(int pageNo, int pageSize);

    APIResponse<Iterable<GetClassDto>> AddNewClass(AddNewClasDto requestClassDto);

     APIResponse<GetClassDto> UpdateClass(UpdateClassDto requestClassDto);

     APIResponse<GetClassDto> GetClass(int classId);

     APIResponse<Iterable<GetClassDto>> RemoveClass(int classId);
}
