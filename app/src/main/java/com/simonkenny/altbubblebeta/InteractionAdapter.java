package com.simonkenny.altbubblebeta;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by simonkenny on 20/03/15.
 */
public class InteractionAdapter extends RecyclerView.Adapter<InteractionAdapter.ViewHolder> {
    private final ArrayList<InteractionData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public InteractionData data;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("InteractionAdapter", "::ViewHolder - on click");
                    if( data != null ) {
                        if( data.isActive() && !data.isSolved() ) {
                            // put arguments together
                            Bundle args = new Bundle();
                            args.putString("name",data.getName());
                            args.putString("description",data.getDescription());
                            InteractionDetailFragment frag = new InteractionDetailFragment();
                            frag.setArguments(args);
                            // swap in fragment
                            Utils.replaceFragment((Activity)view.getContext(),R.id.container,
                                    frag,"expanded","expand_transition");
                        }
                    }

                    /*
                    ((Activity)view.getContext()).getFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .show(new InteractionDetailFragment())
                            .commit();
                            */
                    /*
                    Utils.replaceFragment((Activity)view.getContext(),R.id.container,
                            new InteractionDetailFragment(),"expanded","expand_transition");
                            */
                    /*
                    Utils.addFragment((Activity)view.getContext(),R.id.container,
                            new InteractionDetailFragment(),"expanded");
                            */
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public InteractionAdapter(ArrayList<InteractionData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public InteractionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.interaction_list_cell, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        InteractionData msg = mDataset.get(position);
        holder.data = msg;
        if( !msg.isActive() ) {
            ((ImageView) holder.mView.findViewById(R.id.action_audio)).setImageResource(R.drawable.ic_action_secure);
            holder.mView.findViewById(R.id.action_audio).setAlpha(0.5f);
            holder.mView.findViewById(R.id.text_title).setAlpha(0.5f);
            holder.mView.findViewById(R.id.text_descript).setAlpha(0.5f);
            holder.mView.findViewById(R.id.text_time).setAlpha(0.5f);
            holder.mView.invalidate();
        } else if( !msg.isSolved() ) {
            ((ImageView) holder.mView.findViewById(R.id.action_audio)).setImageResource(R.drawable.ic_action_edit);
        }
        ((TextView)holder.mView.findViewById(R.id.text_title)).setText(msg.getName());
        ((TextView)holder.mView.findViewById(R.id.text_descript)).setText(msg.getDescription());
        ((TextView)holder.mView.findViewById(R.id.text_time)).setText(msg.isSolved()?"Solved":"Not solved");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
