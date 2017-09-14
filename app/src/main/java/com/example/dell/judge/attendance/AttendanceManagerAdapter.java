package com.example.dell.judge.attendance;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.dell.judge.R;
import com.example.dell.judge.schedule.ScheduleVariable;
import java.util.ArrayList;

/**
 * Created by dell on 9/14/2017.
 */
public class AttendanceManagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Character> attendanceArray = new ArrayList<>();
public ArrayList<AttendanceManagerModel> attendAdapt;

public AttendanceManagerAdapter(ArrayList<AttendanceManagerModel> attendAdapt) {
        this.attendAdapt = attendAdapt;
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
    final int pos=position;
    final ScheduleHolder sh = (ScheduleHolder) holder;
        sh.studentView.setText(attendAdapt.get(position).getstudentName());
        sh.presentButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
            if(pos>=attendanceArray.size())
            {
                for(int i=attendanceArray.size();i<pos;i++)
                {
                    attendanceArray.add(i,'a');
                }
                attendanceArray.add(pos,'p');
            }else
            {
                attendanceArray.set(pos,'p');
            }

    Log.d("attend",attendanceArray.toString());
        }
        });
    sh.absentButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(pos>=attendanceArray.size())
            {
                for(int i=attendanceArray.size();i<pos;i++)
                {
                    attendanceArray.add(i,'a');
                }
                attendanceArray.add(pos,'a');
            }else
            {
                attendanceArray.set(pos,'a');
            }
            Log.d("attend",attendanceArray.toString());
        }
    });
        }

@Override
public int getItemCount() {
        return attendAdapt.size();
        }

public static class ScheduleHolder extends RecyclerView.ViewHolder{
    private TextView studentView;
    private CardView studentCd;
    private Button presentButton,absentButton;
    public ScheduleHolder(View itemView) {
        super(itemView);
        studentView=(TextView) itemView.findViewById(R.id.student);
        studentCd=(CardView) itemView.findViewById(R.id.studentCard);
        presentButton=(Button) itemView.findViewById(R.id.present);
        absentButton=(Button) itemView.findViewById(R.id.absent);
    }
}
}
