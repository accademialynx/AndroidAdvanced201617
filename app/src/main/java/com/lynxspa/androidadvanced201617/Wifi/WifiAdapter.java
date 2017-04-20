package com.lynxspa.androidadvanced201617.Wifi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lynxspa.androidadvanced201617.R;

import java.util.List;

public class WifiAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<WifiList> listStorage;

    public WifiAdapter(Context context, List<WifiList> customizedListView) {
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
            convertView = layoutInflater.inflate(R.layout.activity_adapter_for_wifi_list, parent, false);

            listViewHolder.ssid = (TextView)convertView.findViewById(R.id.ssid);
            listViewHolder.bssid= (TextView)convertView.findViewById(R.id.bssid);
            listViewHolder.signal= (TextView)convertView.findViewById(R.id.signal);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.ssid.setText(listStorage.get(position).getSsid());
        listViewHolder.bssid.setText(listStorage.get(position).getBssid());
        listViewHolder.signal.setText(listStorage.get(position).getSignal());

        return convertView;
    }

    static class ViewHolder{
        TextView ssid;
        TextView bssid;
        TextView signal;
    }
}
