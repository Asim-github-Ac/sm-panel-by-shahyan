package com.mytech.smmpanel.Admins.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.mytech.smmpanel.Admins.user.adduser.AddUserFragment;
import com.mytech.smmpanel.R;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {

    Button btn_AddUser;
    RecyclerView recycler_User;
    RecyclerUserAdapter adapter;
    List<UserModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user, container, false);

        btn_AddUser = view.findViewById(R.id.btn_AddUser);
        recycler_User = view.findViewById(R.id.recycler_User);
        list = new ArrayList<>();

        btn_AddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.frameLayout, new AddUserFragment()).commit();
            }
        });

        UserModel model = new UserModel("ID","User Name","EMail","Skype","Balance","Spent","Status","Created","Last Auth","Rates","Actions");
        list.add(model);
        adapter = new RecyclerUserAdapter(view.getContext(), list);
        recycler_User.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycler_User.setAdapter(adapter);
        return view;
    }
}