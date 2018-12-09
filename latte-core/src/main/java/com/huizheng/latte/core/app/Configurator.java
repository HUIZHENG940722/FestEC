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
     */
    private static final WeakHashMap<String,Object> LATTE_CONFIGS=new WeakHashMap<>();
    private Configurator(){
        //.name()是以字符串的形式输出出来
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }
    private static class Holder{
        /**
         * 把单例放进来了
         */
        private static final Configurator INSTANCE=new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    /**
     * 告诉系统配置文件已好
     */
    public final void configure(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String apiHost){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),apiHost);
        return this;
    }

    /**
     * 检查配置是否完成
     */
    private void checkConfiguration(){
        final boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
