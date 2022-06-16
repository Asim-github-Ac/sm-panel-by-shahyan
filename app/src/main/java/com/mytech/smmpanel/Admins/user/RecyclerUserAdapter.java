package com.mytech.smmpanel.Admins.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mytech.smmpanel.R;

import java.util.List;

public class RecyclerUserAdapter extends RecyclerView.Adapter<RecyclerUserAdapter.ViewHolder>{

    Context context;
    List<UserModel> list;

    public RecyclerUserAdapter(Context context, List<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_itemview_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel model = list.get(position);
        holder.r_ID.setText(model.getId());
        holder.r_UserName.setText(model.getUser());
        holder.r_EMail.setText(model.getEmail());
        holder.r_Skype.setText(model.getSkype());
        holder.r_Balance.setText(model.getBalance());
        holder.r_Spent.setText(model.getSpent());
        holder.r_Status.setText(model.getStatus());
        holder.r_Created.setText(model.getCreated());
        holder.r_LastAuth.setText(model.getLastAuth());
        holder.r_Rates.setText(model.getRates());
        holder.r_Actions.setText(model.getActions());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView r_ID, r_UserName, r_EMail, r_Skype, r_Balance, r_Spent, r_Status, r_Created, r_LastAuth, r_Rates, r_Actions;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            r_ID = itemView.findViewById(R.id.r_ID);
            r_UserName = itemView.findViewById(R.id.r_UserName);
            r_EMail = itemView.findViewById(R.id.r_EMail);
            r_Skype = itemView.findViewById(R.id.r_Skype);
            r_Balance = itemView.findViewById(R.id.r_Balance);
            r_Spent = itemView.findViewById(R.id.r_Spent);
            r_Status = itemView.findViewById(R.id.r_Status);
            r_Created = itemView.findViewById(R.id.r_Created);
            r_LastAuth = itemView.findViewById(R.id.r_LastAuth);
            r_Rates = itemView.findViewById(R.id.r_Rates);
            r_Actions = itemView.findViewById(R.id.r_Actions);
        }
    }
}
