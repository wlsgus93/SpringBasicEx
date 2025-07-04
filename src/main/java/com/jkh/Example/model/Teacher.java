package com.jkh.Example.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    private Integer id;
    private String name;
}