package com.lynxspa.androidadvanced201617;

/**
 * Created by Samuele on 17/01/2017.
 */
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import com.lynxspa.androidadvanced201617.Utils.SurveyType;
import com.lynxspa.androidadvanced201617.data.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samuele on 10/03/2016.
 */
public class ListAdapter extends ArrayAdapter<Object> {

    List list = new ArrayList();


    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.raw_layout, parent, false);

        Profile profile = (Profile) getItem(position);

        TextView t1 = (TextView) view.findViewById(R.id.editTextRowLayout);

        t1.setText(profile.getProfileName());


        return view;

    }



}
