package com.fpt.swd.database.dto.Subject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class GetSubjectDTO implements Serializable {
    private int id;
    private int manager_id;
    private String subject_code;
    private String subject_name;
    private String description;
    private String start_date;
    private String created_by;
    private String updated_by;
}
