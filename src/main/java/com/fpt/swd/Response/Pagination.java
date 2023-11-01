package com.fpt.swd.Response;

import com.fpt.swd.database.entity.Assignment;
import com.fpt.swd.database.entity.Class;
import lombok.*;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {
    public int pageNo;
    public int pageSize;
    public long totalElements;
    public int totalPages;
}
