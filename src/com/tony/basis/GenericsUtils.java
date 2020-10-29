package com.tony.basis;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/8 13:31
 * Description:
 */
public class GenericsUtils {
    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
     */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     */
    public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 通过反射，获取类中成员变量泛型的实际类型
     * 如：class A {List<String> data}
     *
     * @param clazz A
     * @param fieldName data
     * @return  String
     * @throws NoSuchFieldException
     */
    public static Class getFieldGenricType(Class clazz,String fieldName) throws NoSuchFieldException {
        Field stringListField = clazz.getDeclaredField(fieldName);
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> cla = (Class<?>) stringListType.getActualTypeArguments()[0];
        return cla;
    }
}
