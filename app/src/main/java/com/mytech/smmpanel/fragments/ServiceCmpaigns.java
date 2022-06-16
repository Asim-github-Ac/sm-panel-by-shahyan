package com.mytech.smmpanel.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mytech.smmpanel.Adapters.ServiceAdapter;
import com.mytech.smmpanel.R;
import com.mytech.smmpanel.model.UserCampaign;

import java.util.ArrayList;
import java.util.List;

public class ServiceCmpaigns extends Fragment {

    RecyclerView recyclerView;
    ServiceAdapter serviceAdapter;
    List<UserCampaign> userCampaignList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_service_cmpaigns, container, false);

        recyclerView=view.findViewById(R.id.adminrecy);
        userCampaignList=new ArrayList<>();


        GetData();



        return  view;
    }
    public void GetData(){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("UserCampaigns");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserCampaign userCampaign=dataSnapshot.getValue(UserCampaign.class);
                    userCampaignList.add(userCampaign);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(new ServiceAdapter(getContext(),userCampaignList));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}