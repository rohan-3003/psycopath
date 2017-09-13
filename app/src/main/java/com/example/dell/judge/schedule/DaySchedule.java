package com.example.dell.judge.schedule;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.judge.MyApplication;
import com.example.dell.judge.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DaySchedule.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DaySchedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaySchedule extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    TextView day;

    public DaySchedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DaySchedule.
     */
    // TODO: Rename and change types and number of parameters
    public static DaySchedule newInstance(String param1, String param2) {
        DaySchedule fragment = new DaySchedule();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String selectedDay = ScheduleVariable.getVar();
        View view = inflater.inflate(R.layout.fragment_day_schedule, container, false);
        day=(TextView) view.findViewById(R.id.day);
        TextView list[]=new TextView[10];
        list[0]=(TextView) view.findViewById(R.id.nine);
        list[1]=(TextView) view.findViewById(R.id.ten);
        list[2]=(TextView) view.findViewById(R.id.eleven);
        list[3]=(TextView) view.findViewById(R.id.twelve);
        list[4]=(TextView) view.findViewById(R.id.one);
        list[5]=(TextView) view.findViewById(R.id.two);
        list[6]=(TextView) view.findViewById(R.id.three);
        list[7]=(TextView) view.findViewById(R.id.four);
        int i=0;
        day.setText(selectedDay);
        ScheduleManager sc = new ScheduleManager(MyApplication.getContext()).open();
        Cursor cr = sc.fetchSchedule();
        if (cr.moveToFirst()){
            do {
                list[i].setText(cr.getString(cr.getColumnIndex(selectedDay)));
                i++;
            }while (cr.moveToNext());
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
