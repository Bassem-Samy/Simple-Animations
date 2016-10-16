package dummy.bassem.com.animationsdummy.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.provider.CalendarContract;
import android.util.AttributeSet;
import android.view.View;

import dummy.bassem.com.animationsdummy.R;

/**
 * Created by Bassem.Wissa on 10/16/2016.
 */

public class MyArcView extends View {
    private float arc_width;
    private float arc_height;

    Path path = new Path();
    Paint paint = new Paint();

    public MyArcView(Context context) {
        super(context);
    }

    public MyArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MyArcView,
                0, 0);
        arc_width = a.getFloat(R.styleable.MyArcView_arc_total_width, 0);
        arc_height = a.getFloat(R.styleable.MyArcView_arc_height, 0);

    }

    public MyArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (arc_width != 0 && arc_height != 0)
            drawMyArc(canvas);
    }

    private void drawMyArc(Canvas canvas) {

        path.reset();
        path.moveTo(0, 0);
        float fraction = (arc_height/ arc_width );
        for (float x = 0; x < arc_width / 2; x += fraction) {
            if (x < arc_width)
                path.lineTo(x, x);
            else
                path.lineTo(x, arc_height);

        }

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        canvas.drawCircle(20, 20, 15, paint);
        canvas.drawRect(60, 20, 15,20, paint);
    }
}
