package com.bdjobs.recyclerviewswipe;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.util.Attributes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Student> mDataSet;


    private TextView tvEmptyView;
    private RecyclerView mRecyclerView;
    LinearLayout linearLayout;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Job List");


            linearLayout = (LinearLayout) findViewById(R.id.info);
        tvEmptyView = (TextView) findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // Layout Managers:
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Item Decorator:
       mRecyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
         //mRecyclerView.setItemAnimator(new FadeInLeftAnimator());
        mRecyclerView.addOnScrollListener(new CustomScrollListener());

        mDataSet = new ArrayList<Student>();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Android Students");

        }

        loadData();

        if (mDataSet.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            tvEmptyView.setVisibility(View.VISIBLE);

        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            tvEmptyView.setVisibility(View.GONE);
        }


        // Creating Adapter object
        SwipeRecyclerViewAdapter mAdapter = new SwipeRecyclerViewAdapter(this, mDataSet);


        // Setting Mode to Single to reveal bottom View for one item in List
        // Setting Mode to Mutliple to reveal bottom Views for multile items in List
        ((SwipeRecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);

        mRecyclerView.setAdapter(mAdapter);

        /* Scroll Listeners */
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("RecyclerView", "onScrollStateChanged");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    class CustomScrollListener extends RecyclerView.OnScrollListener {
        CustomScrollListener(){
        }
        public void onScrollStateChanged(RecyclerView recyclerView, int newState){
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:
                    System.out.println("RP= The RecyclerView is not scrolling");



                    break;
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    System.out.println("RP= Scrolling now");

                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    System.out.println("RP= Scroll Settling");
                    break;

            }

        }
        public void onScrolled(RecyclerView recyclerView, int dx, int dy){
            if(dx>0)
            {
                System.out.println("Scrolled Right");

            }
            else if(dx < 0)
            {
                System.out.println("Scrolled Left");

            }
            else {

                System.out.println("No Horizontal Scrolled");
            }

            if(dy>0)
            {
                Animation out = AnimationUtils.makeOutAnimation(MainActivity.this, true);
                linearLayout.startAnimation(out);
                linearLayout.setVisibility(View.GONE);
                System.out.println("Scrolled Downwards");
            }
            else if(dy < 0)
            {
                System.out.println("Scrolled Upwards");
                Animation in = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left);
                linearLayout.startAnimation(in);
                linearLayout.setVisibility(View.VISIBLE);

            }
            else {

                System.out.println("No Vertical Scrolled");
            }
        }



    }


    // load initial data
    public void loadData() {

        for (int i = 0; i <= 20; i++) {
            mDataSet.add(new Student("Student " + i, "androidstudent" + i + "@gmail.com"));

        }


    }

}

