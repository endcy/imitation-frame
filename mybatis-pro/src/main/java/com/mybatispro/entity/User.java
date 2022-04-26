package com.mybatispro.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author cxx
 * @date 2021/11/1 23:04
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2072158639836717203L;

    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private Integer managerId;
    private Byte deleted;
    private Integer version;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
