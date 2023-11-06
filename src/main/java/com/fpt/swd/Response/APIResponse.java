package com.fpt.swd.Response;

import com.fpt.swd.utils.DataUtils;
import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse<T> {
    public T Data;

    public boolean status = true;
    public Pagination pagination = new Pagination();
    public String message = null;
}