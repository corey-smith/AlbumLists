package com.albums.ui;

import java.util.List;
import com.albumlists.R;
import com.albums.model.AlbumList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Class to hold list of Album Lists
 */
public class MetaListArrayAdapter extends ArrayAdapter<AlbumList> {
    Context context;
    List<AlbumList> metaList;

    public MetaListArrayAdapter(Context context, int resource, List<AlbumList> metaList) {
        super(context, resource, metaList);
        this.context = context;
        this.metaList = metaList;
    }
    
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.meta_list_item, parent, false);
            AlbumList currentList = this.metaList.get(position);
            TextView listName = (TextView) itemView.findViewById(R.id.meta_list_item_name);
            listName.setText(currentList.getName());
        }
        return itemView;
    }
    
}
