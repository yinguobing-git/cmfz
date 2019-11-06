package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    private String id;
    private String name;
    private String password;
    private String phone;
}
