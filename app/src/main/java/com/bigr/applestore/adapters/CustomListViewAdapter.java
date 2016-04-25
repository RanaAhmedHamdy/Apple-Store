package com.bigr.applestore.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.bigr.applestore.R;
import com.bigr.applestore.models.Subcategory;
import com.bigr.applestore.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rana on 4/25/2016.
 */
public class CustomListViewAdapter extends ArrayAdapter<Subcategory> {


    private Context context;

    public CustomListViewAdapter(Context context, int resourceId,
                                 List<Subcategory> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Subcategory rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.subcategories_list_item, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.subcategories_txt);
            holder.imageView = (ImageView) convertView.findViewById(R.id.subcategories_image);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(rowItem.getName());
        Picasso.with(context).load(Constants.IMAGE_URL + rowItem.getImage()).into(holder.imageView);

        return convertView;
    }
}
