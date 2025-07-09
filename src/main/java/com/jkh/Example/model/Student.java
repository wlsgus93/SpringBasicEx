package com.jkh.Example.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Integer id;
    private String name;
    private Integer score;
    private Integer teacherId;
    private String teacherName;
}