package nillouise.end;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class MsgDetail extends AppCompatActivity {
    private int dataID;
    ArrayList<PasMsg> msgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detail);

        Intent intent = getIntent();
        dataID = intent.getIntExtra("MsgID",-1);
        msgs =  MsgLab.get().getMsgs();


        if(dataID!=-1)
        {
            EditText title = (EditText)findViewById(R.id.edtTitle);
            title.setText(msgs.get(dataID).getTitle());
            EditText content = (EditText)findViewById(R.id.edtContent);
            content.setText(msgs.get(dataID).getContent());
            CheckBox checkBox = (CheckBox)findViewById(R.id.chbDetail);
            checkBox.setChecked(msgs.get(dataID).isHasDone() );
        }


        Button btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtTitle = (EditText) findViewById(R.id.edtTitle);
                EditText edtContent = (EditText)findViewById(R.id.edtContent);
                CheckBox checkBox = (CheckBox)findViewById(R.id.chbDetail);
                if(dataID==-1)
                {
                    msgs.add(new PasMsg(edtTitle.getText().toString(),edtContent.getText().toString(),false) );
                }else{
                    msgs.get(dataID).setTitle(edtTitle.getText().toString());
                    msgs.get(dataID).setContent(edtContent.getText().toString());
                    msgs.get(dataID).setHasDone(checkBox.isChecked());
                }
                finish();
            }
        });


    }
}
