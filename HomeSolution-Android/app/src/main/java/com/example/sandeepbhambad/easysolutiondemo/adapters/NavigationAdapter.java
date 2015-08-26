package com.example.sandeepbhambad.easysolutiondemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandeepbhambad.easysolutiondemo.MainActivity;
import com.example.sandeepbhambad.easysolutiondemo.R;
import com.example.sandeepbhambad.easysolutiondemo.activities.Feedback;
import com.example.sandeepbhambad.easysolutiondemo.models.NavigationListData;


import java.util.Collections;
import java.util.List;

/**
 * Created by pratikgadekar on 8/25/15.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<NavigationListData> data = Collections.emptyList();
    private Context context;
    private ClickListener clickListner;

    public NavigationAdapter(Context context, List<NavigationListData> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.navigation_custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavigationListData current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());

    }

    public void setClickListner(ClickListener clickListner) {
        this.clickListner = clickListner;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(context,"Item clicked at " + getAdapterPosition(),Toast.LENGTH_SHORT).show();
//
//            if(getAdapterPosition() == 0){
//                Intent i = new Intent(context, Feedback.class);
//                context.startActivity(i);
//            } else if(getAdapterPosition() == 1){
//                Intent i = new Intent(context, Feedback.class);
//                context.startActivity(i);
//            } else if(getAdapterPosition() == 2){
//                Intent i = new Intent(context, Feedback.class);
//                context.startActivity(i);
//            } else if(getAdapterPosition() == 3){
//                Intent i = new Intent(context, Feedback.class);
//                context.startActivity(i);
//            }

            if(clickListner != null){
                clickListner.itemClicked(v, getAdapterPosition());
            }

        }
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}
