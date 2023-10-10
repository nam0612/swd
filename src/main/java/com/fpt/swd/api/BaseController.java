package com.fpt.swd.api;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.fpt.swd.utils.DataUtils;
import com.google.gson.Gson;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    private Gson gson = new Gson();

    public BaseController() {
    }

    protected List<Sort.Order> getSortInformation(String sorts) {
        if (DataUtils.isNullObject(sorts)) {
            return null;
        } else {
            Sort.Order[] orders = (Sort.Order[])this.gson.fromJson(sorts, Sort.Order[].class);
            return Arrays.asList(orders);
        }
    }

    protected <V> ResponseEntity<Object> processPageResponse(Page<V> data) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-totalElements", String.valueOf(data.getTotalElements()));
        headers.add("X-totalPages", String.valueOf(data.getTotalPages()));
        headers.add("X-numberOfElements", String.valueOf(data.getNumberOfElements()));
        headers.add("X-size", String.valueOf(data.getSize()));
        headers.add("X-number", String.valueOf(data.getNumber()));
        return new ResponseEntity(data.getContent(), headers, HttpStatus.OK);
    }

    protected Map<String, String> getExtendParams(Map<String, String> params, Object param) throws Exception {
        List<String> lstIgnoreKey = new ArrayList();
        lstIgnoreKey.add("page");
        lstIgnoreKey.add("size");
        lstIgnoreKey.add("sort");
        Field[] fields = param.getClass().getDeclaredFields();
        Field[] var5 = fields;
        int var6 = fields.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Field field = var5[var7];
            field.setAccessible(true);
            NotNull fieldType = (NotNull)field.getDeclaredAnnotation(NotNull.class);
            if (!DataUtils.isNullObject(fieldType)) {
                try {
                    if (field.get(param) == null || "".equals(field.get(param))) {
                        throw new Exception("err.input.invalid");
                    }
                } catch (Exception var11) {
                    throw new Exception("err.input.invalid");
                }
            }

            lstIgnoreKey.add(field.getName());
        }

        Map<String, String> result = new HashMap();
        Iterator var13 = params.entrySet().iterator();

        while(var13.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var13.next();
            if (!lstIgnoreKey.contains(entry.getKey())) {
                result.put((String)entry.getKey(), (String)entry.getValue());
            }
        }

        return result;
    }
}