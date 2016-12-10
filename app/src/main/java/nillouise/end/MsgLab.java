package nillouise.end;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;

/**
 * Created by win7x64 on 2016/12/11.
 */

public class MsgLab {
    private ArrayList<PasMsg> mMsgs;
    private static MsgLab sMsgLab;

    MsgLab(){
        mMsgs = new ArrayList<PasMsg>();
    }
    public ArrayList<PasMsg> getMsgs(){
        return mMsgs;
    }

    public static MsgLab get(){
        if(sMsgLab==null){
            sMsgLab = new MsgLab();
        }
        return  sMsgLab;
    }




}
