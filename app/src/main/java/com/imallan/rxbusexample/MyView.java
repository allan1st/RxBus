package com.imallan.rxbusexample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.imallan.rxbus.annotation.Subscribe;

public class MyView extends TextView {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Subscribe
    public void updateText(ViewEvent event) {
        setText(event.tag);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MyView$BindUtils.bind(MyApplication.getBus(), this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MyView$BindUtils.unbind(MyApplication.getBus(), this);
    }

    static class ViewEvent {

        public final String tag;

        ViewEvent(String tag) {
            this.tag = tag;
        }
    }
}
