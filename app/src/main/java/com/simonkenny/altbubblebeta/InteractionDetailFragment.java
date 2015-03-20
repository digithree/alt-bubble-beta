package com.simonkenny.altbubblebeta;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by simonkenny on 20/03/15.
 */
public class InteractionDetailFragment extends Fragment {

    private InteractionData data;

    public InteractionDetailFragment() {
        this.data = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.interaction_detail_fragment, container, false);

        final Context mContext = this.getActivity();

        Bundle b = getArguments();
        String name = b.getString("name");
        //String description = b.getString("description");
        ((TextView)rootView.findViewById(R.id.text_status)).setText(name);

        ((Button)rootView.findViewById(R.id.button_verify))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Activity)mContext).getFragmentManager().popBackStack();
                    }
                });

        return rootView;
    }
}
