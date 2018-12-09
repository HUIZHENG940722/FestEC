package com.huizheng.latte.core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * @author HUIZHENG
 * @date 2018/12/9
 * @time 12:47
 * Created by IntelliJ IDEA.
 */
public final class Latte {
    /**
     * 在当前环境下初始化配置
     * @param context
     * @return
     */
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     * 给配置文件增加上下文权限
     * @return
     */
    private static WeakHashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }
}
