package edu.hcmus.minhdat.citytravel.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

//import edu.hcmus.minhdat.citytravel.R;

public class ViewSavedLocations extends AppCompatActivity {
    TextView txtMsg;
    String[] items = { "Demo" };
    Integer[] thumbnails = { R.drawable.admin };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_locations);
        CustomIconLabelAdapter adapter = new CustomIconLabelAdapter(ViewSavedLocations.this, R.layout.custom_item_listview_save_location, items, thumbnails);
        ListView listviewLocation=(ListView)findViewById(R.id.lsvLocations);
        listviewLocation.setAdapter(adapter);
    }
    class CustomIconLabelAdapter extends ArrayAdapter<String> {
        Context context;
        Integer[] thumbnails;
        String[] items;
        public CustomIconLabelAdapter( Context context, int layoutToBeInflated,
                                       String[] items, Integer[] thumbnails) {
            super(context, R.layout.custom_item_listview_save_location, items);
            this.context = context;
            this.thumbnails = thumbnails;
            this.items = items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View row = inflater.inflate(R.layout.custom_item_listview_save_location, null);
            TextView label = (TextView) row.findViewById(R.id.label);
            ImageView icon = (ImageView) row.findViewById(R.id.icon);
            label.setText(items[position]);
            icon.setImageResource(thumbnails[position]);
            return (row);
        }
    }// CustomAdapter
}
