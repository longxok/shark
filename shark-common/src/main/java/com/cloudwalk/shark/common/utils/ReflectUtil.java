package com.cloudwalk.shark.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectUtil {

    /**
     * 使用反射调用方法
     *
     * @param o          被调用对象
     * @param methodName 被调用对象的方法名称
     * @param args       被调用方法所需传入的参数列表
     */
    public static Object callMethod(Object o, String methodName, Object... args) {
        Object result = null;
        try {
            int argslen = args.length;
            Class c = o.getClass();
            Method m = null;

            Class<?> argsTypes[] = new Class<?>[argslen];
            //基本类型在参数传递过程中会自动封住成对象类型，这里还原成基本类型的Class
            for (int i = 0; i < argslen; i++) {
                argsTypes[i] = args[i].getClass();
                if (argsTypes[i] == Integer.class) {
                    argsTypes[i] = int.class;
                }
                if (argsTypes[i] == Float.class) {
                    argsTypes[i] = float.class;
                }
                if (argsTypes[i] == Long.class) {
                    argsTypes[i] = long.class;
                }
                if (argsTypes[i] == Byte.class) {
                    argsTypes[i] = byte.class;
                }
                if (argsTypes[i] == Double.class) {
                    argsTypes[i] = double.class;
                }
                if (argsTypes[i] == Boolean.class) {
                    argsTypes[i] = boolean.class;
                }
            }

            if (argslen == 0) {
                m = c.getDeclaredMethod(methodName);
            } else {
                Method[] methodes = c.getDeclaredMethods();
                for (Method method : methodes) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (method.getName().equals(methodName) && parameterTypes.length == argslen) {
                        int i;
                        for (i = 0; i < argslen; i++) {
                            if (argsTypes[i] != parameterTypes[i]) {
                                break;
                            }
                        }
                        if (i == argslen) {
                            m = method;
                            break;
                        }
                    }
                }
            }
            if (m == null) {
                String argsName = Arrays.toString(argsTypes).replace("[", "(").replace("]", ")");
                throw new NoSuchMethodException(methodName + argsName);
            }
            m.setAccessible(true);
            result = m.invoke(o, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用反射设置变量值
     *
     * @param target    被调用对象
     * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可是常量！
     * @param value     值
     * @param <T>       value类型，泛型
     */
    public static <T> void setValue(Object target, String fieldName, T value) {
        try {
            Class c = target.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(target, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用反射获取变量值
     *
     * @param target    被调用对象
     * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可以是常量
     * @param <T>       返回类型，泛型
     * @return 值
     */
    public static <T> T getValue(Object target, String fieldName) {
        T value = null;
        try {
            Class c = target.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            value = (T) f.get(target);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }
}