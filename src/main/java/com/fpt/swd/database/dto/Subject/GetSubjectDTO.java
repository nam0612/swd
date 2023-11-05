package com.fpt.swd.database.dto.Subject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter

public class GetSubjectDTO implements Serializable {
    private int manager_id;
    private String subject_code;
    private String subject_name;
    private String description;
    private String start_date;
    private String created_by;
    private String updated_by;
}
