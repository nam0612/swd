package com.fpt.swd.Response;

import com.fpt.swd.utils.DataUtils;
import lombok.*;

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
