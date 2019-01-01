package com.rplsukses.ezprint.ui.custom;

import android.content.Context;
import androidx.cardview.widget.CardView;
import android.util.AttributeSet;

public class SquareCardView extends CardView {
    public SquareCardView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
