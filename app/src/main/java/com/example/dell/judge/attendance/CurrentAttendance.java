package com.example.dell.judge.attendance;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.judge.MyApplication;
import com.example.dell.judge.R;
import com.example.dell.judge.schedule.Schedule_Menu_Adapter;
import com.example.dell.judge.schedule.Schedule_Menu_Details;
import com.example.dell.judge.schedule.Schedule_Menu_Model;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentAttendance.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentAttendance#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentAttendance extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView name1,name2;
    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    ArrayList<AttendanceManagerModel> list= new ArrayList<>();
    AttendanceManagerAdapter sma;

    public CurrentAttendance() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentAttendance.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentAttendance newInstance(String param1, String param2) {
        CurrentAttendance fragment = new CurrentAttendance();
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
        View view = inflater.inflate(R.layout.fragment_current_attendance, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.attendanceRecycle);
        list = AttendanceManagerDetails.getList();
        sma = new AttendanceManagerAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sma);
//        name1=(TextView)view.findViewById(R.id.name1);
//        name2=(TextView)view.findViewById(R.id.name2);
//        AttendenceManager ad= new AttendenceManager(MyApplication.getContext());
//        ad.open();
//        Cursor cr=ad.fetchStudents();
//        if(cr.moveToFirst())
//        {
//            do{
//                name1.setText(cr.getString(1));
//            }while(cr.moveToNext());
//        }
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
