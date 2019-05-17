package hzkj.cc.menuview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Content extends LinearLayout {
    public static final int LIST = 11111;
    public static final int ARROW_RIGHT = 22222;
    public static final int ARROW_CENTER = 22223;
    public static final int ARROW_LEFT = 22224;
    Paint paint;
    int width;
    int radius = 20;
    int arrowMargin = 30;
    int status;
    int id = 0;
    View view;
    int arrowStatus;
    List<ListItem> listItems;
    OnSelectItemListenner listenner;
    MenuPopupView.CallBack callBack;

    public void setOnSelectItemListenner(OnSelectItemListenner listenner) {
        this.listenner = listenner;
    }

    public void setArrowStatus(int arrowStatus) {
        this.arrowStatus = arrowStatus;
    }

    public int getContentWidth() {
        return width;
    }

    public int getRadius() {
        return radius;
    }

    public int getArrowMarginRight() {
        return arrowMargin;
    }

    public Content(Context context, int status, List<ListItem> listItems, MenuPopupView.CallBack callBack, int arrowStatus) {
        super(context);
        Log.d("ccnb", width + "");
        this.status = status;
        this.listItems = listItems;
        this.callBack = callBack;
        this.arrowStatus = arrowStatus;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
//        setBackgroundColor(Color.GREEN);
        switch (status) {
            case LIST: {
                LinearLayout superLayout = new LinearLayout(getContext());
                superLayout.setOrientation(VERTICAL);
                for (final ListItem listItem : listItems) {
                    View view = LayoutInflater.from(getContext())
                            .inflate(R.layout.content, null);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
                    layoutParams.weight = 1;
                    view.setLayoutParams(layoutParams);
                    ImageView imageView = view.findViewById(R.id.image);
                    TextView textView = view.findViewById(R.id.text);
                    textView.setText(listItem.getText());
                    imageView.setImageDrawable(getResources().getDrawable(listItem.getDrawableId()));
                    superLayout.addView(view);
                    view.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listenner != null) {
                                listenner.onSelectItem(listItems.indexOf(listItem));
                                callBack.callBack();
                            }
                        }
                    });
//
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.topMargin = radius;
                superLayout.setLayoutParams(layoutParams);
                this.addView(superLayout);
                superLayout.setPadding(dip2px(getContext(), 13), dip2px(getContext(), 7), dip2px(getContext(), 13), dip2px(getContext(), 7));
                superLayout.setBackground(getResources().getDrawable(R.drawable.style));
                measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                width = getMeasuredWidth();
                break;
            }
        }
    }

    public Content(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
//        arrowStatus = ARROW_LEFT;
        Path path = new Path();
        switch (arrowStatus) {
            case ARROW_RIGHT: {
                path.moveTo(width - radius - arrowMargin, 0);// 此点为多边形的起点
                path.lineTo(width - radius * 2 - arrowMargin, radius);
                path.lineTo(width - arrowMargin, radius);
                break;
            }
            case ARROW_CENTER: {
                path.moveTo(width / 2, 0);// 此点为多边形的起点
                path.lineTo(width / 2 - radius, radius);
                path.lineTo(width / 2 + radius, radius);
                break;
            }
            case ARROW_LEFT: {
                path.moveTo(arrowMargin + radius, 0);// 此点为多边形的起点
                path.lineTo(arrowMargin, radius);
                path.lineTo(arrowMargin + radius * 2, radius);
            }
        }
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, paint);
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public interface OnSelectItemListenner {
        void onSelectItem(int position);
    }
}
