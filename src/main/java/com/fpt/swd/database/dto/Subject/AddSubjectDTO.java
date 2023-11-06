package com.fpt.swd.database.dto.Subject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSubjectDTO implements Serializable {
    private int manager_id;
    private String subject_code;
    private String subject_name;
    private String description;
    private String start_date;
    private String created_by;
    private Date created_date;
}
