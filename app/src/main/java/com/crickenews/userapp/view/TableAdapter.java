package com.crickenews.userapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crickenews.userapp.R;
import com.crickenews.userapp.model.Table;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private Context context;
    private List<Table> tableList;

    public TableAdapter(Context context, List<Table> tableList) {
        this.context = context;
        this.tableList = tableList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_recy_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.country.setText(tableList.get(position).getTeam());

        String wingImageUrl = tableList.get(position).getTeamflag();
        Picasso.get().load(wingImageUrl).into(holder.flag);


        holder.points.setText(tableList.get(position).getPts());
        holder.played.setText(tableList.get(position).getMatches());

        holder.won.setText(tableList.get(position).getWon());
        holder.lost.setText(tableList.get(position).getLost());
        holder.netrr.setText(tableList.get(position).getNrr());




    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView country,played,won,lost,points,netrr;
       ImageView flag;
        // Define other views here

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            country= itemView.findViewById(R.id.tablecountryname);
            flag= itemView.findViewById(R.id.tablecountryflag);

            played= itemView.findViewById(R.id.tablematchplayed);
            won= itemView.findViewById(R.id.tablewin);
            lost= itemView.findViewById(R.id.tablelost);
            points= itemView.findViewById(R.id.tablepts);
            netrr= itemView.findViewById(R.id.tablenrr);


        }
    }
}