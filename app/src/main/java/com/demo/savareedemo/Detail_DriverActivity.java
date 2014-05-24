package com.demo.savareedemo;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.ArrayList;

public class Detail_DriverActivity extends TabActivity implements TabHost.OnTabChangeListener {
    TabHost tabHost;
    ListView list;
    ReviewCustomAdapter adapter;
    public  Detail_DriverActivity CustomListView = null;
    public ArrayList<ReviewListModel> CustomListViewValuesArr = new ArrayList<ReviewListModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_driver);



        tabHost = getTabHost();

        // Set TabChangeListener called when tab changed
        tabHost.setOnTabChangedListener(this);

        TabHost.TabSpec spec;
        Intent intent;

        /************* TAB1 ************/
        // Create  Intents to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, Tab1.class);
        spec = tabHost.newTabSpec("First").setIndicator("")
                .setContent(intent);

        //Add intent to tab
        tabHost.addTab(spec);

        /************* TAB2 ************/
        intent = new Intent().setClass(this, Tab2.class);
        spec = tabHost.newTabSpec("Second").setIndicator("")
                .setContent(intent);
        tabHost.addTab(spec);

        /************* TAB3 ************/
        intent = new Intent().setClass(this, Tab3.class);
        spec = tabHost.newTabSpec("Third").setIndicator("")
                .setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, Tab4.class);
        spec = tabHost.newTabSpec("Four").setIndicator("")
                .setContent(intent);
        tabHost.addTab(spec);

        // Set drawable images to tab
        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.badge);
        tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.history);
        tabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.message);

        // Set Tab1 as Default tab and change image

        // Set Tab1 as Default tab and change image
        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.reviews);

    }

    @Override
    public void onTabChanged(String tabId) {


        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            if(i==0)
                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.reviews);
            else if(i==1)
                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.badge);
            else if(i==2)
                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.history);
            else if(i==3)
                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.message);
        }

        Log.i("tabs", "CurrentTab: " + tabHost.getCurrentTab());

        if(tabHost.getCurrentTab()==0)
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.reviews);
        else if(tabHost.getCurrentTab()==1)
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.badge);
        else if(tabHost.getCurrentTab()==2)
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.history);
        else if(tabHost.getCurrentTab()==3)
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.message);

        CustomListView = this;

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res =getResources();
        list = ( ListView )findViewById( R.id.Reviewlist );  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter=new ReviewCustomAdapter( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter( adapter );
    }
    public void setListData()
    {

        for (int i = 0; i < 4; i++) {

            final ReviewListModel sched = new ReviewListModel();

            /******* Firstly take data in model object ******/
            sched.setName("Hamad Hassan");
            sched.setComments("Lorem ipsum dolor sit amet, consecteture adipiscing elit, sed diam nonummy nibh euismod tincidun");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }

    }
    public void onItemClick(int mPosition)
    {
        ReviewListModel tempValues = ( ReviewListModel ) CustomListViewValuesArr.get(mPosition);


        // SHOW ALERT

        Toast.makeText(CustomListView,
                "" + tempValues.getName(),
                Toast.LENGTH_LONG)
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail__driver, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.notification:
                return true;
            case R.id.message:

                return true;
            case R.id.logout:
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                Intent intent = new Intent(Detail_DriverActivity.this,SigninActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
