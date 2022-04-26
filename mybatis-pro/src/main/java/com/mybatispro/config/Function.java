package com.mybatispro.config;

import lombok.Data;

/**
 * @author cxx
 * @date 2021/12/13 1:08
 **/
@Data
public class Function {
    private String sqltype;
    private String funcName;
    private String sql;
    private Object resultType;
    private String parameterType;
}
