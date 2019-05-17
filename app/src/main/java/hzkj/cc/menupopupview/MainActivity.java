package hzkj.cc.menupopupview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;

import hzkj.cc.menuview.Content;
import hzkj.cc.menuview.ListItem;
import hzkj.cc.menuview.MenuPopupView;

public class MainActivity extends AppCompatActivity {
    TitleBar.Action action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.sd);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuPopupView menuPopupView = new MenuPopupView(MainActivity.this, Content.LIST, new ArrayList<ListItem>() {{
                    add(new ListItem(R.drawable.ic_danhaochaxun, "日期查询"));
                    add(new ListItem(R.drawable.ic_danhaochaxun, "单号查询"));
                    add(new ListItem(R.drawable.ic_danhaochaxun, "日期查询"));
                    add(new ListItem(R.drawable.ic_danhaochaxun, "单号查询"));
                    add(new ListItem(R.drawable.ic_danhaochaxun, "日期查询"));
                    add(new ListItem(R.drawable.ic_danhaochaxun, "单号查询"));
                }});
                menuPopupView.setArrowLocation(Content.ARROW_LEFT);
                menuPopupView.showDown(v);
                menuPopupView.setSelectItemListenner(new Content.OnSelectItemListenner() {
                    @Override
                    public void onSelectItem(int position) {
                        Log.d("ccnb", position + "");
                    }
                });
            }
        });
        final TitleBar bar = findViewById(R.id.bar);
        action = new TitleBar.Action() {
            @Override
            public String getText() {
                return null;
            }

            @Override
            public int getDrawable() {
                return R.drawable.ic_danhaochaxun;
            }

            @Override
            public void performAction(View view) {
                MenuPopupView menuPopupView = new MenuPopupView(MainActivity.this, Content.LIST, new ArrayList<ListItem>() {{
                    add(new ListItem(R.drawable.ic_danhaochaxun, "日期查询"));
                    add(new ListItem(R.drawable.ic_danhaochaxun, "单号查询"));
                    add(new ListItem(R.drawable.ic_danhaochaxun, "日期查询"));
                    add(new ListItem(R.drawable.ic_danhaochaxun, "单号查询"));
                    add(new ListItem(R.drawable.ic_danhaochaxun, "日期查询"));
                    add(new ListItem(R.drawable.ic_danhaochaxun, "单号查询"));
                }});
                menuPopupView.showDown(view);
                menuPopupView.setSelectItemListenner(new Content.OnSelectItemListenner() {
                    @Override
                    public void onSelectItem(int position) {
                        Log.d("ccnb", position + "");
                    }
                });
            }

            @Override
            public int leftPadding() {
                return 0;
            }

            @Override
            public int rightPadding() {
                return 0;
            }
        };
        bar.addAction(action);
    }
}
