package com.mybatispro.excutor;

/**
 * @author cxx
 * @date 2021/12/13 1:10
 **/
public interface Excutor {
    public <T> T query(String statement, Object parameter);
}
