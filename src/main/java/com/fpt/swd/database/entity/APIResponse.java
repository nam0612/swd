package com.fpt.swd.database.entity;

import com.fpt.swd.utils.DataUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
public class APIResponse<T> {
    public T Data;

    public boolean status = true;

    public String message = null;
}
