package cn.joah.login.test;


import cn.joah.login.commons.reflect.ReflectUtils;
import cn.joah.login.entity.User;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ReflectUtilsTest {
    @Test
    public void toBeanTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IntrospectionException, InvocationTargetException {
        Map<String,String[]> map=new HashMap<>();
        String[] uName={"Joah"};
        String[] sx={"male"};
        String[] eml={"joah@joah.com"};
        String[] pro={"GGGGGGG"};
        map.put("username",uName);
        map.put("password",uName);
        map.put("sex",sx);
        map.put("email",eml);
        map.put("profile",pro);
        map.put("website",pro);
        User user = ReflectUtils.toBean(map,cn.joah.login.entity.User.class);
        System.out.println("返回的对象: "+user);
    }
}