package com.mytech.smmpanel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mytech.smmpanel.R;
import com.mytech.smmpanel.model.UserCampaign;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.myHolder> {
    Context context;
    List<UserCampaign> userCampaigns;

    public ServiceAdapter(Context context, List<UserCampaign> userCampaigns) {
        this.context = context;
        this.userCampaigns = userCampaigns;
    }

    @NonNull
    @Override
    public ServiceAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item,parent,false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.myHolder holder, int position) {

        UserCampaign user=userCampaigns.get(position);
        holder.serviceid.setText(user.getUserid());
        holder.link.setText(user.getChannellink());
        holder.chargecount.setText(user.getCharge());
        holder.quantity.setText(user.getQuantity());
        holder.starttime.setText(user.getStarttime());
    }

    @Override
    public int getItemCount() {
        return userCampaigns.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView serviceid,link,chargecount,quantity,starttime,status;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            serviceid=itemView.findViewById(R.id.serviceid);
            link=itemView.findViewById(R.id.link);
            chargecount=itemView.findViewById(R.id.charge);
            quantity=itemView.findViewById(R.id.quantity);
            starttime=itemView.findViewById(R.id.startcount);
            status=itemView.findViewById(R.id.remainstatus);
        }
    }
}
