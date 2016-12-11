package nillouise.end;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Button btnSend = (Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                udpSend udp = new udpSend();
//                udp.start();
//                UDPClient client = new UDPClient("fdsf");
//                client.start();

//                Socket_Audio socket_audio = new Socket_Audio();
//       //         new Thread(socket_audio).start();
////        String buf = "hello,i am jdh1";
////        socket_audio.send(buf.getBytes(),buf.length());
//                for (int i = 0;i < 20;i++)
//                {
//                    String buf1 = "UDP:I AM JDH" + i;
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    socket_audio.send(buf1.getBytes(),buf1.length());
//                }

                EditText title = (EditText)findViewById(R.id.edtTitle);
                String rexp = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d{1,5})";

                Pattern pat = Pattern.compile(rexp);

                Matcher mat = pat.matcher(title.getText());

                boolean ipAddress = mat.matches();
                if(!ipAddress)
                {
                    Toast.makeText(MsgDetail.this,"不是合法的IP地址",Toast.LENGTH_SHORT).show();
                    return;
                }
                String input = title.getText().toString(); // <-- an example input
                int port = 80; // <-- a default port.
                String host = null;
                if (input.indexOf(':') > -1) { // <-- does it contain ":"?
                    String[] arr = input.split(":");
                    host = arr[0];
                    try {
                        port = Integer.parseInt(arr[1]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    host = input;
                }
                EditText contentTosend =  (EditText)findViewById(R.id.edtContent);

                Thread_Send thread_send = new Thread_Send(contentTosend.getText().toString().getBytes(), contentTosend.getText().toString().getBytes().length,host,port);

                new Thread(thread_send).start();

            }
        });

    }


    private static class Thread_Send implements Runnable {
        //发送数据缓存
        private byte[] Buffer_Send = new byte[MAX_DATA_PACKET_LENGTH];
        //发送数据包
        private DatagramPacket Packet_Send;
        private static final int MAX_DATA_PACKET_LENGTH = 1024;
        DatagramSocket Udp_Socket;
        private String SERVER_IP = "127.0.0.1";
        //语音端口
        private int SERVER_PORT_AUDIO = 12801;
        //本地端口
        private final int LOCAL_PORT_AUDIO = 12801;

        public Thread_Send(byte[] data, int len,String ip,int port) {
            //发送包
            Packet_Send = new DatagramPacket(Buffer_Send, MAX_DATA_PACKET_LENGTH);
            Packet_Send.setData(data);
            Packet_Send.setLength(len);
            SERVER_IP=ip;
            SERVER_PORT_AUDIO = port;
            try{
                Udp_Socket = new DatagramSocket();
            }catch (Exception e){

            }

        }

        @Override
        public void run() {
            try {
                final int port = SERVER_PORT_AUDIO;
                Packet_Send.setPort(port);
                Packet_Send.setAddress(InetAddress.getByName(SERVER_IP));
                Udp_Socket.send(Packet_Send);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(Udp_Socket!=null)
                Udp_Socket.close();
        }
    }

}
