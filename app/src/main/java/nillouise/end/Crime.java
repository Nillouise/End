package nillouise.end;

import java.util.UUID;

/**
 * Created by win7x64 on 2016/12/9.
 */

public class Crime {
    public UUID getmId() {
        return mId;
    }

    private UUID mId;
    private  String mTitle;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public  Crime(){
        mId=UUID.randomUUID();
    }

}
