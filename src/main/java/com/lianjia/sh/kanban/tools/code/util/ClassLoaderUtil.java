package com.lianjia.sh.kanban.tools.code.util;

/**
 * 类加载工具
 *
 * @author ouyang
 * @since 2015-02-09 16:53
 */
@SuppressWarnings("UnusedCatchParameter")
public class ClassLoaderUtil {


    /**
     * 私有构造 工具类
     *
     * @author ouyang
     * @since 2015-02-12 17:35
     */
    private ClassLoaderUtil() {
    }

    /**
     * 加载类
     *
     * @param className    类名
     * @param callingClass 调用的类
     * @return class
     * @throws ClassNotFoundException ClassNotFoundException
     * @author ouyang
     * @since 2015-02-09 16:54
     */
    public static Class loadClass(String className, Class callingClass) throws ClassNotFoundException {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException ignored) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ex) {
                try {
                    return ClassLoaderUtil.class.getClassLoader().loadClass(className);
                } catch (ClassNotFoundException exc) {
                    return callingClass.getClassLoader().loadClass(className);
                }
            }
        }
    }
}
