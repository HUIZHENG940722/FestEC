package com.huizheng.latte.core.app;

import android.widget.LinearLayout;

import java.util.WeakHashMap;

/**
 * 配置文件的存储
 * @author HUIZHENG
 * @date 2018/12/9
 * @time 13:08
 * Created by IntelliJ IDEA.
 */
public class Configurator {
    /**
     * 有static final命名必须是大写以及下划线
     * 存放配置文件属性以及对应的值信息
     */
    private static final WeakHashMap<String,Object> LATTE_CONFIGS=new WeakHashMap<>();

    /**
     * 构造函数，开始默认配置需要更新
     */
    private Configurator(){
        /**
         * name()是以字符串的形式输出出来
         */
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    /**
     * 可以认为是个中间类，存放默认的配置信息
     */
    private static class Holder{
        private static final Configurator INSTANCE=new Configurator();
    }

    /**
     * 初始化配置信息
     * @return
     */
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    /**
     * 获取配置信息的上下文
     * @return
     */
    final WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    /**
     * 告诉系统配置文件已好
     */
    public final void configure(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    /**
     * 配置网络请求域名
     * @param apiHost
     * @return
     */
    public final Configurator withApiHost(String apiHost){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),apiHost);
        return this;
    }

    /**
     * 判断配置是否完成
     */
    private void checkConfiguration(){
        final boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 获取配置信息属性值
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
