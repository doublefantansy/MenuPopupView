package hzkj.cc.menupopupview;

import android.app.Application;

import com.xuexiang.xui.XUI;

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
    }
}
