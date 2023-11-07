package com.fpt.swd.database.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MilestoneDTO implements Serializable {
    private Integer  id;
    private String name;
    private String description;

    @NotBlank
    private String projectId;

    @NotBlank
    private String classId;
    private Integer status;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private String projectName;
    private String className;

    public MilestoneDTO(Integer id, String name, String description, Integer status, String projectName, String className) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.projectName = projectName;
        this.className = className;
    }

}
