package com.awei.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {
    private Long id;
    private String name;
    private String email;

    public Dept(String dname, String email) {
        this.name = name;
        this.email = email;
    }
}
