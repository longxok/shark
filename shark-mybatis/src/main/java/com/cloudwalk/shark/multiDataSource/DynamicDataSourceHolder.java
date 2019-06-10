package com.cloudwalk.shark.multiDataSource;

/**
 * 数据源操作类
 */
public class DynamicDataSourceHolder {
    private static ThreadLocal<String> routeKey = new ThreadLocal<String>();

    /**
     * 获取当前线程的数据源路由的key
     */
    public static String getRouteKey()
    {
        String key = routeKey.get();
        return key;
    }

    /**
     * 绑定当前线程数据源路由的key
     * 使用完成后必须调用removeRouteKey()方法删除
     */
    public static void  setRouteKey(String key)
    {
        routeKey.set(key);
    }

    /**
     * 删除与当前线程绑定的数据源路由的key
     */
    public static void removeRouteKey()
    {
        routeKey.remove();
    }
}