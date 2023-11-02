package com.fpt.swd.Response;


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
