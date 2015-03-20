package com.simonkenny.altbubblebeta;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by simonkenny on 20/03/15.
 */
public class AppSupport {
    private final static AppSupport INSTANCE = new AppSupport();

    protected AppSupport() {
        mAdapter = new InteractionAdapter(dataset);
    } // Changed from EventAdapter

    public static AppSupport getInstance() {
        return INSTANCE;
    }

    // ---- Data
    private ArrayList<InteractionData> dataset = new ArrayList<InteractionData>();

    public ArrayList<InteractionData> getDataset() {
        return dataset;
    }

    public void setDataset(ArrayList<InteractionData> dataset) {
        this.dataset = dataset;
    }

    private RecyclerView.Adapter mAdapter;

    public RecyclerView.Adapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }

}
