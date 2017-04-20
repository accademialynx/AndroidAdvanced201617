package com.lynxspa.androidadvanced201617.Beacon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lynxspa.androidadvanced201617.R;

import java.util.List;

public class BeaconAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<BeaconList> listStorage;

    public BeaconAdapter(Context context, List<BeaconList> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.activity_adapter_for_beacon_list, parent, false);

            listViewHolder.nameBeacon = (TextView)convertView.findViewById(R.id.nameBeacon);
            listViewHolder.distanceBeacon= (TextView)convertView.findViewById(R.id.distanceBeacon);
            listViewHolder.addressBeacon= (TextView)convertView.findViewById(R.id.addressBeacon);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.nameBeacon.setText(listStorage.get(position).getNameBeacon());
        listViewHolder.distanceBeacon.setText(listStorage.get(position).getDistanceBeacon()+"m");
        listViewHolder.addressBeacon.setText(listStorage.get(position).getAddressBeacon());

        return convertView;
    }

    static class ViewHolder{
        TextView nameBeacon;
        TextView distanceBeacon;
        TextView addressBeacon;
    }
}
