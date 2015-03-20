package com.simonkenny.altbubblebeta;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;


/**
 * Created by simonkenny on 22/01/15.
 */
public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment(),"expanded")
                    .commit();
            getFragmentManager().beginTransaction()
                    .add(R.id.drawer_container, new DrawerFragment())
                    .commit();
            getFragmentManager().beginTransaction()
                    .add(R.id.playing_container, new PlayingFragment())
                    .commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        setSupportActionBar(toolbar);

        drawerView = (View)findViewById(R.id.drawer_container);

        // Now retrieve the DrawerLayout so that we can set the status bar color.
        // This only takes effect on Lollipop, or when using translucentStatusBar
        // on KitKat.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackgroundColor(
                getResources().getColor(R.color.primarydark));
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.app_name, R.string.app_name) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle("Keen Time");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle("Drawer");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //getWindow().setStatusBarColor(getResources().getColor(R.color.primary));  //DOES NOTHING
            getWindow().setNavigationBarColor(getResources().getColor(R.color.primary));
        }
        */

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        GlobalSettings.getInstance().setSharedPreferences(sharedPreferences);

        // add debug puzzble data
        // Add test data
        AppSupport.getInstance().getDataset().add(new InteractionData("Audio 1","Intro",true,true));
        AppSupport.getInstance().getDataset().add(new InteractionData("Enter Code","Journey",false,true));
        AppSupport.getInstance().getDataset().add(new InteractionData("?????","???",false,false));
        AppSupport.getInstance().getDataset().add(new InteractionData("?????","???",false,false));
        AppSupport.getInstance().getDataset().add(new InteractionData("?????","???",false,false));
        // Force list update
        AppSupport.getInstance().getmAdapter().notifyDataSetChanged();
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(drawerView);
        //menu.findItem(R.id.).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(Gravity.START|Gravity.LEFT)){
            mDrawerLayout.closeDrawers();
            return;
        }
        getFragmentManager().popBackStack();
        //super.onBackPressed();
    }

    /**
     * Fragment in main panel of app
     */
    public static class MainFragment extends Fragment {

        private RecyclerView mRecyclerView;
        private RecyclerView.LayoutManager mLayoutManager;

        public MainFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.main_fragment, container, false);

            final Context mContext = this.getActivity();

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.event_recycler_view);
            //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(mContext);
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mRecyclerView.setAdapter(AppSupport.getInstance().getmAdapter());

            return rootView;
        }
    }

    /**
     * Fragment in main panel of app
     */
    public static class DrawerFragment extends Fragment {

        public DrawerFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.drawer_fragment, container, false);

            final Context mContext = this.getActivity();

            // TODO : build view

            return rootView;
        }
    }

    public static class PlayingFragment extends Fragment {

        private boolean timerActive = false;
        private com.gc.materialdesign.views.ProgressBarDeterminate audioProgress;

        public PlayingFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.playing_layout_fragment, container, false);

            final Context mContext = this.getActivity();

            audioProgress = ((com.gc.materialdesign.views.ProgressBarDeterminate)rootView.findViewById(R.id.audio_progress));

            return rootView;
        }

        public void onResume() {
            super.onResume();
            startAlarm();
        }

        public void onPause() {
            super.onPause();
            cancelAlarm();
        }

        public void startAlarm() {
            timerActive  = true;
            // set up alarm
            new CountDownTimer(60000, 60) { //make tick small, might crash it though?
                private int count = 0;
                public void onTick(long millisUntilFinished) {
                    if( audioProgress != null ) {
                        audioProgress.setProgress(count++);
                    }
                }

                public void onFinish() {
                    if( timerActive ) {
                        startAlarm();
                    } else {
                        Log.d("MainActivity", "Timer is disabled");
                    }
                }
            }.start();
        }

        public void cancelAlarm() {
            timerActive = false;
        }
    }
}
