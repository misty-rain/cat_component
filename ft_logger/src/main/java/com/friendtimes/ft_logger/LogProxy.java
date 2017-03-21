package com.friendtimes.ft_logger;

/**
 * wutao
 * 自定义组件Log 输出类
 * 可自定义是输出log开关
 */
public class LogProxy {

    private static boolean isDebug = true;
    private static String prefixName = "";

    public static void setDebugMode(boolean debug) {
        isDebug = debug;
    }

    public static void setPrefixName(String prefixName) {
        LogProxy.prefixName = prefixName;
    }

    private static String getLogTag(String tag) {
        return String.format("%s[%s]", prefixName, tag);
    }


    public static void d(String tag, String msg) {
        if (isDebug) {
            Logger.d(getLogTag(tag) + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Logger.e(getLogTag(tag) + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Logger.i(getLogTag(tag) + msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Logger.w(getLogTag(tag) + msg);
        }
    }

    /**
     * 格式json 字符串
     *
     * @param msg
     */
    public static void json(String msg) {
        if (isDebug) {
            Logger.json(msg);
        }
    }

    /**
     * 格式xml
     *
     * @param msg
     */
    public static void xml(String msg) {
        if (isDebug) {
            Logger.xml(msg);
        }
    }

    /**
     * 设置全局TAG
     *
     * @param tag
     */
    public static void setGlobalTag(String tag) {
        if (isDebug) {
            Logger.init(tag);
        }
    }
}
