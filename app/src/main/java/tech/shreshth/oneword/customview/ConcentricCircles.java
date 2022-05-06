package tech.shreshth.oneword.customview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tech.shreshth.oneword.R;

public class ConcentricCircles extends View {

    private Paint paint;
    private int offset;
    private int color;
    private int spacing;
    private DisplayMetrics metrics;
    private int height;
    private int width;
    private int total_circles;
    private int stroke_width;
    private Context context;
    public ConcentricCircles(Context context) {
        super(context);
        this.context=context;
        init(null);
    }

    public ConcentricCircles(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init(attrs);
    }

    public ConcentricCircles(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init(attrs);
    }

    public ConcentricCircles(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context=context;
        init(attrs);
    }

    private void init(@Nullable AttributeSet set)
    {
        paint=new Paint();
        metrics = new DisplayMetrics();
        WindowManager windowManager=(WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels/2;
        width = metrics.widthPixels/2;
        if(set==null)
            return;

        TypedArray ta= getContext().obtainStyledAttributes(set, R.styleable.ConcentricCircles);
        offset=ta.getInt(R.styleable.ConcentricCircles_offset,100);
        color=ta.getColor(R.styleable.ConcentricCircles_circle_color,Color.GREEN);
        spacing=ta.getInt(R.styleable.ConcentricCircles_spacing,10);
        stroke_width=ta.getInt(R.styleable.ConcentricCircles_stroke_width,10);
        total_circles=ta.getInt(R.styleable.ConcentricCircles_total_circles,10);
        Log.d("HANDW",height+" "+width);
        ta.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(color);
        paint.setStrokeWidth(stroke_width);
        paint.setStyle(Paint.Style.STROKE);
       for(int x=1;x<=total_circles;x++) {
           createCircle(0, 0, offset + x * spacing, canvas);
       }
    }


    private void createCircle(int height,int width,int offset,Canvas canvas)
    {
        canvas.drawCircle(height,width,offset,paint);
    }


}
