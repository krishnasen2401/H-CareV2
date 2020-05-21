package com.infosys.connected.h_carev2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.infosys.connected.h_carev2.DataFiles.notificationstable;
import com.infosys.connected.h_carev2.R;

import java.util.List;

public class notificationsadapter extends RecyclerView.Adapter<notificationsadapter.ViewHolder> {
    private List<notificationstable> mlists;
    private Context mContext1;
    private RecyclerView mRecyclerV1;
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Tvtitle,tvtext,tvTimeStamp;
        public View layout;
        public ViewHolder(View v) {
            super(v);
            layout=v;
            Tvtitle=v.findViewById(R.id.tvTitle);
            tvtext=v.findViewById(R.id.tvMessage);
            tvTimeStamp=v.findViewById(R.id.tvTimestamp);
        }
    }
    public void add(int position, notificationstable food) {
        mlists.add(position, food);
        notifyItemInserted(position);
    }
    public notificationsadapter(List<notificationstable> myDataset, Context context, RecyclerView recyclerView) {
        mlists = myDataset;
        mContext1 = context;
        mRecyclerV1 = recyclerView;
    }
    public notificationsadapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType){
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =inflater.inflate(R.layout.row_notificitions, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(notificationsadapter.ViewHolder holder, final int position){
        final notificationstable fb=mlists.get(position);
        holder.layout.setBackgroundResource(R.drawable.notificationbuble);
        //if(position%2==0)
          ///  holder.layout.setBackgroundResource(R.drawable.cellbg1);
        //else
           // holder.layout.setBackgroundResource(R.drawable.cellbg2);
        holder.Tvtitle.setText(fb.getTitle1());
        holder.tvtext.setText(fb.getText1());
        holder.tvTimeStamp.setText(fb.getTimestamp());
    }
    public int getItemCount(){
        return  mlists.size();}

}
