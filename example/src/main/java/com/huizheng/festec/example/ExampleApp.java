package com.huizheng.festec.example;

import android.app.Application;
import com.huizheng.latte.core.app.Latte;


/**
 * @author HUIZHENG
 * @date 2018/12/9
 * @time 13:06
 * Created by IntelliJ IDEA.
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
