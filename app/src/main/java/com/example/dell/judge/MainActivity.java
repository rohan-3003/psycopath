package com.example.dell.judge;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.judge.attendance.AttendenceManager;
import com.example.dell.judge.attendance.CurrentAttendance;
import com.example.dell.judge.database.DatabaseHandler;
import com.example.dell.judge.schedule.Current_Schedule;
import com.example.dell.judge.schedule.DaySchedule;
import com.example.dell.judge.schedule.ScheduleManager;
import com.example.dell.judge.schedule.Schedule_menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Schedule_menu.OnFragmentInteractionListener,
        Current_Schedule.OnFragmentInteractionListener,DaySchedule.OnFragmentInteractionListener,CurrentAttendance.OnFragmentInteractionListener{

    DatabaseHandler myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.INTERNET)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.INTERNET}, 1);

            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS}, 1);

            }
        } else {
        }
        if(ChooseFragment.getF().equals("DaySchedule")){
            Fragment fragment = new DaySchedule();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.homeFragment,fragment);
            ft.commit();
        }
        else {
            Fragment fragment = new Current_Schedule();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.homeFragment, fragment);
            ft.commit();
        }

        myDB=new DatabaseHandler(MyApplication.getContext());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                Fragment fragment = new Schedule_menu();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.homeFragment,fragment);
                ft.commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        createStudents("vktamta");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {

        } else if (id == R.id.nav_attendance) {
            Fragment fragment = new CurrentAttendance();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.homeFragment,fragment);
            ft.commit();

        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public void createStudents(final String database){
        RequestQueue queue = Volley.newRequestQueue(MyApplication.getContext());
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URLs.GET_STUDENTS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                ParseCheckedcreate len = new ParseCheckedcreate(response);
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


class ParseCheckedcreate{

    public static String[] students;

    public static final String JSON_ARRAY = "students";
    public static final String KEY_STUDENT= "students";
    private JSONArray users = null;
    private String json;
    public ParseCheckedcreate(String json){
        this.json = json;
    }

    protected void parseeJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);
            Log.d("json",users.toString());
            students = new String[users.length()];
            AttendenceManager ad = new AttendenceManager(MyApplication.getContext());
            ad.open();
            ad.deleteStudents();

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                students[i] = jo.getString(KEY_STUDENT);
                ad.insertStudents(students[i]);
            }
            ad.close();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}