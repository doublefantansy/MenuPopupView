package hzkj.cc.menuview;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.util.List;

import static hzkj.cc.menuview.Content.ARROW_CENTER;
import static hzkj.cc.menuview.Content.ARROW_LEFT;
import static hzkj.cc.menuview.Content.ARROW_RIGHT;

public class MenuPopupView extends PopupWindow {
    Context context;
    Content content;
    int arrowLocation;

    public MenuPopupView(Context context, int status, List<ListItem> listItems) {
        super(context);
        this.context = context;
        arrowLocation = ARROW_CENTER;
        init(status, listItems);
    }

    public void setArrowLocation(int arrowLocation) {
        this.arrowLocation = arrowLocation;
        content.setArrowStatus(arrowLocation);
    }

    private void init(int status, List<ListItem> listItems) {
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        content = new Content(context, status, listItems, new CallBack() {
            @Override
            public void callBack() {
                dismiss();
            }
        }, arrowLocation);
//        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(null);
        setContentView(content);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                        .getAttributes();
                lp.alpha = 1f; //0.0-1.0
                ((Activity) context).getWindow()
                        .setAttributes(lp);
            }
        });
    }

    public void setSelectItemListenner(Content.OnSelectItemListenner listenner) {
        content.setOnSelectItemListenner(listenner);
    }

    public void showDown(View view) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                .getAttributes();
        lp.alpha = 0.5f; //0.0-1.0
        ((Activity) context).getWindow()
                .setAttributes(lp);
        if (isShowing()) {
            dismiss();
        } else {
            int[] location = new int[2];
            view.getLocationOnScreen(location);
            switch (arrowLocation) {
                case ARROW_CENTER: {
                    showAtLocation(view, Gravity.NO_GRAVITY, location[0] - content.getContentWidth() / 2 + view.getWidth() / 2, location[1] + view.getMeasuredHeight());
                    break;
                }
                case ARROW_RIGHT: {
                    showAtLocation(view, Gravity.NO_GRAVITY, location[0] + view.getWidth() / 2 - (content.getContentWidth() - content.getRadius() - content.getArrowMarginRight()), location[1] + view.getMeasuredHeight());
                    break;
                }
                case ARROW_LEFT: {
                    showAtLocation(view, Gravity.NO_GRAVITY, location[0] - (content.arrowMargin + content.radius - view.getWidth() / 2), location[1] + view.getMeasuredHeight());
                    break;
                }
            }
        }
    }

    interface CallBack {
        void callBack();
    }
}
