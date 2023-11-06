package com.fpt.swd.Response;


import lombok.*;
<<<<<<< HEAD
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
=======

>>>>>>> d8b82252d198afa4797adc9de858d6fd08333a8f

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
