package com.fpt.swd.Response;

import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse<T> {
    public T Data;
    public Pagination pagination = new Pagination();
    public boolean status = true;
    public String message = null;
}
