package com.fpt.swd.Response;


import lombok.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
