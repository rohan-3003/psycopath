package com.example.dell.judge.attendance;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.dell.judge.R;
import com.example.dell.judge.schedule.ScheduleVariable;
import java.util.ArrayList;

/**
 * Created by dell on 9/14/2017.
 */
public class AttendanceManagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
public ArrayList<AttendanceManagerModel> sm;
        ScheduleVariable setV = new ScheduleVariable();

public AttendanceManagerAdapter(ArrayList<AttendanceManagerModel> sm) {
        this.sm = sm;
        }

@Override
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh =null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_manager_list,null);
        vh = new ScheduleHolder(view);
        return vh;
        }

@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
final ScheduleHolder sh = (ScheduleHolder) holder;
        sh.studentView.setText(sm.get(position).getstudentName());
//        sh.cd.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        setV.setVar(sh.dayy.getText().toString());
//        ChooseFragment.setF("DaySchedule");
//        Intent i=new Intent(MyApplication.getContext(), MainActivity.class);
//        MyApplication.getContext().startActivity(i);
//        }
//        });
        }

@Override
public int getItemCount() {
        return sm.size();
        }
public static class ScheduleHolder extends RecyclerView.ViewHolder{
    private TextView studentView;
    private CardView studentCd;
    public ScheduleHolder(View itemView) {
        super(itemView);
        studentView=(TextView) itemView.findViewById(R.id.student);
        studentCd=(CardView) itemView.findViewById(R.id.studentCard);
    }
}
}
