package nillouise.end;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public void setClickedCount(int clickedCount) {
        ClickedCount = clickedCount;
    }
    public void load(){
        msgList.clear();
        SharedPreferences pref = MainActivity.this.getSharedPreferences("pasmsg",Context.MODE_PRIVATE);
        int msgSize = pref.getInt("size",0);
        for(int i=0;i<msgSize;i++)
        {
            msgList.add(new  PasMsg(pref.getString("title"+i,""),pref.getString("content"+i,""),pref.getBoolean("checkout"+i,false)));
        }

    }
    public void dump(){
        SharedPreferences pref = MainActivity.this.getSharedPreferences("pasmsg",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("size",msgList.size());
        for(int i=0;i<msgList.size();i++)
        {
            PasMsg msg = msgList.get(i);
            editor.putString("title"+i,msg.getTitle());
            editor.putString("content"+i,msg.getContent());
            editor.putBoolean("checkout"+i,msg.isHasDone());
        }
        editor.commit();
    }


    public int getClickedCount() {
        return ClickedCount;
    }

    public int ClickedCount;

    private List<PasMsg> msgList = MsgLab.get().getMsgs();


    PasMsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();
        adapter = new PasMsgAdapter(MainActivity.this,R.layout.pasmsg_brief_item,msgList);
        ListView listView =(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PasMsg clickMsg = msgList.get(i);
//                CheckBox checkBox = (CheckBox)findViewById(R.id.chbDone);
//                clickMsg.setHasDone(checkBox.isChecked() );
                Toast.makeText(MainActivity.this,clickMsg.getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MsgDetail.class);
                intent.putExtra("MsgID",i);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int delIndex = i;
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("警告");
                dialog.setMessage("确定删除？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        msgList.remove(delIndex);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                    @Override
                    public  void onClick(DialogInterface dialog,int which){


                    }
                });
                dialog.show();
                return true;
            }
        });

        ImageButton imgbutton = (ImageButton)findViewById(R.id.imbPlus);
        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MsgDetail.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop(){
        super.onStop();
        dump();
    }

    private void initMsg(){
        PasMsg msg1 = new PasMsg("title1","content1",false);
        msgList.add(msg1);
        PasMsg msg2 = new PasMsg("title2","content2",true);
        msgList.add(msg2);
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