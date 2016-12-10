package nillouise.end;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    public void setClickedCount(int clickedCount) {
        ClickedCount = clickedCount;
    }

    public int getClickedCount() {
        return ClickedCount;
    }

    public int ClickedCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bn = (Button)findViewById(R.id.btnClickedCount);
        ClickedCount=0;
        bn.setText(ClickedCount+"");
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickedCount++;
                final Button bn2 = (Button)findViewById(R.id.btnClickedCount);
                bn2.setText(ClickedCount+"");

            }
        });
        RelativeLayout root = (RelativeLayout)findViewById(R.id.activity_main);
        final DrawView draw = new DrawView(this);
        draw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                draw.currentX=motionEvent.getX();
                draw.currentY=motionEvent.getY();
                draw.invalidate();
                return false;
            }
        });
        root.addView(draw);
        Button bnActivate = (Button)findViewById(R.id.btnActivity);
        bnActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,test2.class );
                startActivity(i);
            }
        });

    }
}
class DrawView extends View
{
    public float currentX=40;
    public float currentY = 50;
    public DrawView(Context context)
    {
        super(context);
    }
    @Override
    public  void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint p =new Paint();
        p.setColor(Color.RED);
        canvas.drawCircle(currentX,currentY,15,p);


    }

}