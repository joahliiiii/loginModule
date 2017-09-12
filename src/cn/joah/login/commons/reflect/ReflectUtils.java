package cn.joah.login.commons.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectUtils {
    /**
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     */
    public static  <T> T toBean(Map<String, String[]> map, Class<T> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IntrospectionException {
        // 创建对象
        T o = clazz.newInstance();
//        System.out.println("user 对象: "+o);
        // 得到所有的属性的值
        Field[] declaredFields = clazz.getDeclaredFields();
        // 遍历所有的属性
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            // 得到属性描述对象
            PropertyDescriptor propertyDescriptor=new PropertyDescriptor(name,clazz);
            // 得到写入方法对象
            Method writeMethod = propertyDescriptor.getWriteMethod();
            // 得到 map 的键的集合
            Set<String> set = map.keySet();
            // 便利 map 的键
            for(String key : set){
                // 比较 map 里面的键的值 和 对应属性的名字
                if(key.equals(name)){
                    // 执行 写方法
                    // 由于 request.getParameterMap()返回的是一个Map<string,string[]>
                    //                  值其实只有一个所以直接接 string[0]
                    writeMethod.invoke( o, map.get(key)[0]);
                }
            }
        }
//        System.out.println("user 对象: "+o);
        return o;
    }

    public static <T> T toBean2(Map<String,String> map,Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException, IntrospectionException {
        // 得到对象
        T o = clazz.newInstance();

        Field[] declaredFields = clazz.getDeclaredFields();
        // 遍历所有属性
        for (Field declaredField : declaredFields) {
            // 得到属性名
            String name = declaredField.getName();
            // 根据 属性名和类名 得到属性描述对象
            PropertyDescriptor propertyDescriptor= new PropertyDescriptor(name,clazz);
            // 得到属性的写入方法对象
            Method writeMethod = propertyDescriptor.getWriteMethod();
            // 得到Map的键的集合
            Set<String> strings = map.keySet();
            // 遍历所有的键
            for(String key : strings){
                // 如果属性名 和 map的键相对应
                if(name.equals(key)){
                    String value = map.get(key);
                    // 传入参数, 执行写入方法
                    writeMethod.invoke(o,value);
                }
            }
        }
        return o;
    }

}
