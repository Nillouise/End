package nillouise.end;

/**
 * Created by win7x64 on 2016/12/10.
 */

public class PasMsg {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    private String Content;

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }

    private boolean hasDone;


    public PasMsg(String title,String Content,boolean hasDone){
        this.title=title;
        this.Content=Content;
        this.hasDone=hasDone;
    }




}
