package com.example.dell.judge.schedule;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.judge.ChooseFragment;
import com.example.dell.judge.MainActivity;
import com.example.dell.judge.MyApplication;
import com.example.dell.judge.R;

import java.util.ArrayList;

/**
 * Created by dell on 9/6/2017.
 */
public class Schedule_Menu_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public ArrayList<Schedule_Menu_Model> sm;
    ScheduleVariable setV = new ScheduleVariable();

    public Schedule_Menu_Adapter(ArrayList<Schedule_Menu_Model> sm) {
        this.sm = sm;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh =null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_menu_list,null);
        vh = new ScheduleHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ScheduleHolder sh = (ScheduleHolder) holder;
        sh.dayy.setText(sm.get(position).getDay());
        sh.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setV.setVar(sh.dayy.getText().toString());
                ChooseFragment.setF("DaySchedule");
                Intent i=new Intent(MyApplication.getContext(), MainActivity.class);
                MyApplication.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sm.size();
    }
    public static class ScheduleHolder extends RecyclerView.ViewHolder{
        private TextView dayy;
        private CardView cd;
        public ScheduleHolder(View itemView) {
            super(itemView);
            dayy=(TextView) itemView.findViewById(R.id.day);
            cd=(CardView) itemView.findViewById(R.id.dayCard);
        }
    }
}
