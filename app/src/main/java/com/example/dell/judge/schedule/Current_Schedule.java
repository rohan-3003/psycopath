package com.example.dell.judge.schedule;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.judge.MyApplication;
import com.example.dell.judge.R;
import com.example.dell.judge.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Current_Schedule.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Current_Schedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Current_Schedule extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //TextView day,nine,ten,eleven,twelve,one,two,three,four;
    TextView day,list[];
    String currentDay;

    public Current_Schedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Current_Schedule.
     */
    // TODO: Rename and change types and number of parameters
    public static Current_Schedule newInstance(String param1, String param2) {
        Current_Schedule fragment = new Current_Schedule();
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
        checkSchedule("vktamta");
        View view = inflater.inflate(R.layout.fragment_current__schedule, container, false);
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
        String dayNames[]=new DateFormatSymbols().getWeekdays();
        Calendar calendar = Calendar.getInstance();
        currentDay = dayNames[calendar.get(Calendar.DAY_OF_WEEK)];
        day.setText(currentDay);
        ScheduleManager sc = new ScheduleManager(MyApplication.getContext()).open();
        Cursor cr = sc.fetchSchedule();
        if (cr.moveToFirst()){
            do {
                list[i].setText(cr.getString(cr.getColumnIndex(currentDay)));
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


    public void checkSchedule(final String database){
        RequestQueue queue = Volley.newRequestQueue(MyApplication.getContext());
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URLs.GET_SCHEDULE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                 ParseCheckedUpdate len = new ParseCheckedUpdate(response);
                 len.parseeJSON();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("number", database);
                return params;
            }

        };
        queue.add(strReq);

        //  MyApplication.getInstance().addToRequestQueue(strReq);
    }
}

class ParseCheckedUpdate{

    public static String[] time;
    public static String[] monday;
    public static String[] tuesday;
    public static String[] wednesday;
    public static String[] thursday;
    public static String[] friday;

    public static final String JSON_ARRAY = "schedule";
    public static final String KEY_ID= "id";
    public static final String KEY_TIME= "time";
    public static final String KEY_MONDAY = "Monday";
    public static final String KEY_TUESDAY= "Tuesday";
    public static final String KEY_WEDNESDAY="Wednesday";
    public static final String KEY_THURSDAY="Thursday";
    public static final String KEY_FRIDAY="Friday";
    static final String TABLE_NAME="schedule";
    private JSONArray users = null;
    private String json;
    public ParseCheckedUpdate(String json){
        this.json = json;
    }

    protected void parseeJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);
            Log.d("json",users.toString());
            time = new String[users.length()];
            monday = new String[users.length()];
            tuesday=new String[users.length()];
            wednesday=new String[users.length()];
            thursday=new String[users.length()];
            friday=new String[users.length()];
            ScheduleManager sc = new ScheduleManager(MyApplication.getContext());
            sc.open();
            sc.deleteSchedule();

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                time[i] = jo.getString(KEY_TIME);
                monday[i] = jo.getString(KEY_MONDAY);
                tuesday[i]=jo.getString(KEY_TUESDAY);
                wednesday[i]=jo.getString(KEY_WEDNESDAY);
                thursday[i]=jo.getString(KEY_THURSDAY);
                friday[i]=jo.getString(KEY_FRIDAY);
                sc.insertSchedule(time[i],monday[i],tuesday[i],wednesday[i],thursday[i],friday[i]);
            }
            sc.close();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}