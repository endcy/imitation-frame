package com.mybatispro.session;

import com.mybatispro.excutor.Excutor;
import com.mybatispro.excutor.MyExcutor;

import java.lang.reflect.Proxy;

/**
 * @author cxx
 * @date 2021/12/13 1:10
 **/
public class MySqlsession {

    private Excutor excutor = new MyExcutor();

    private MyConfiguration myConfiguration = new MyConfiguration();

    public <T> T selectOne(String statement, Object parameter) {
        System.out.println("执行sql----" + statement);
        return excutor.query(statement, parameter);
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clas) {
        //动态代理调用
        return (T) Proxy.newProxyInstance(clas.getClassLoader(), new Class[]{clas},
                new MyMapperProxy(myConfiguration, this));
    }

}
