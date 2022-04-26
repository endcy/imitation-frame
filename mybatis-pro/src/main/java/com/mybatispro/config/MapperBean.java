package com.mybatispro.config;

import lombok.Data;

import java.util.List;

/**
 * @author cxx
 * @date 2021/12/13 1:05
 **/
@Data
public class MapperBean {
    private String interfaceName; //接口名
    private List<Function> list; //接口下所有方法
}
