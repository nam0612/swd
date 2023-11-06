package com.fpt.swd.database.dto.Subject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSubjectDTO implements Serializable {
    private int id;
    private int manager_id;
    private String subject_code;
    private String subject_name;
    private String description;
    private String start_date;
    private String updated_by;
    private Date updated_date;
}
