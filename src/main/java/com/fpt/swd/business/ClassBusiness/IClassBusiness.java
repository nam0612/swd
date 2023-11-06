package com.fpt.swd.business.ClassBusiness;

import com.fpt.swd.database.dto.Class.AddNewClasDto;
import com.fpt.swd.database.dto.Class.GetClassDto;
import com.fpt.swd.database.dto.Class.GetClassVer2Dto;
import com.fpt.swd.database.dto.Class.UpdateClassDto;
import com.fpt.swd.Response.APIResponse;

public interface IClassBusiness {

    APIResponse<Iterable<GetClassVer2Dto>> GetAllClass(int pageNo, int pageSize);

    APIResponse<Iterable<GetClassDto>> AddNewClass(AddNewClasDto requestClassDto);

     APIResponse<GetClassDto> UpdateClass(UpdateClassDto requestClassDto);

     APIResponse<GetClassVer2Dto> GetClass(int classId);

     APIResponse<Iterable<GetClassDto>> RemoveClass(int classId);
}
