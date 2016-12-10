package nillouise.end;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by win7x64 on 2016/12/10.
 */

public class PasMsgAdapter extends ArrayAdapter<PasMsg> {
    private int resourceId;
    public PasMsgAdapter(Context context,int textViewResourceId,List<PasMsg> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        PasMsg pasmsg = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView title = (TextView)view.findViewById(R.id.tvTitle);
        title.setText(pasmsg.getTitle());
        CheckBox hasdone = (CheckBox)view.findViewById(R.id.chbDone);
        hasdone.setChecked(pasmsg.isHasDone());

        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.chbDone);
        final int  index = position;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final boolean isChecked = checkBox.isChecked();

                MsgLab.get().getMsgs().get(index).setHasDone(isChecked);


            }
        });





        return view;
    }


}
