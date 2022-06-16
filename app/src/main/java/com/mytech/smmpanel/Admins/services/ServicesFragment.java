package com.mytech.smmpanel.Admins.services;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mytech.smmpanel.R;


import java.util.HashMap;

public class ServicesFragment extends Fragment {

    Button btn_AddServices, btn_AddCategories;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_services, container, false);

        btn_AddServices = view.findViewById(R.id.btn_AddServices);
        btn_AddCategories = view.findViewById(R.id.btn_AddCategories);

        reference = FirebaseDatabase.getInstance().getReference().child("SocialServiceProvider");

        btn_AddServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View serviceDialogue = LayoutInflater.from(getContext()).inflate(R.layout.layout_add_services, null);

                EditText etxt_sn, etxt_c, etxt_sd, etxt_m, etxt_p, etxt_st, etxt_sct, etxt_dpf, etxt_rp, etxt_pv, etxt_mio, etxt_mao, etxt_of, etxt_df;
                Button btn_as, btn_asClose;
                etxt_sn = serviceDialogue.findViewById(R.id.etxt_sn);
                etxt_c = serviceDialogue.findViewById(R.id.etxt_c);
                etxt_sd = serviceDialogue.findViewById(R.id.etxt_sd);
                etxt_m = serviceDialogue.findViewById(R.id.etxt_m);
                etxt_p = serviceDialogue.findViewById(R.id.etxt_p);
                etxt_st = serviceDialogue.findViewById(R.id.etxt_st);
                etxt_sct = serviceDialogue.findViewById(R.id.etxt_sct);
                etxt_dpf = serviceDialogue.findViewById(R.id.etxt_dpf);
                etxt_rp = serviceDialogue.findViewById(R.id.etxt_rp);
                etxt_pv = serviceDialogue.findViewById(R.id.etxt_pv);
                etxt_mio = serviceDialogue.findViewById(R.id.etxt_mio);
                etxt_mao = serviceDialogue.findViewById(R.id.etxt_mao);
                etxt_of = serviceDialogue.findViewById(R.id.etxt_of);
                etxt_df = serviceDialogue.findViewById(R.id.etxt_df);
                btn_as = serviceDialogue.findViewById(R.id.btn_as);
                btn_asClose = serviceDialogue.findViewById(R.id.btn_asClose);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(serviceDialogue);
                AlertDialog alert = builder.create();
                alert.show();

                btn_as.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, Object> smap = new HashMap<>();
                        smap.put("ServiceName", etxt_sn.getText().toString().trim());
                        smap.put("Category", etxt_c.getText().toString().trim());
                        smap.put("ServiceDetail", etxt_sd.getText().toString().trim());
                        smap.put("Mode", etxt_m.getText().toString().trim());
                        smap.put("Provider", etxt_p.getText().toString().trim());
                        smap.put("ServiceType", etxt_st.getText().toString().trim());
                        smap.put("StartCountType", etxt_sct.getText().toString().trim());
                        smap.put("DripFeed", etxt_dpf.getText().toString().trim());
                        smap.put("RatePer1000", etxt_rp.getText().toString().trim());
                        smap.put("PriceVisibility", etxt_pv.getText().toString().trim());
                        smap.put("MinOrder", etxt_mio.getText().toString().trim());
                        smap.put("MaxOrder", etxt_mao.getText().toString().trim());
                        smap.put("OverFlow", etxt_of.getText().toString().trim());
                        smap.put("DownFlow", etxt_df.getText().toString().trim());

                        reference.child("Services").push().setValue(smap);
                        Toast.makeText(getContext(), "Service Added", Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });
                btn_asClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });
            }
        });
        btn_AddCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View categoryDialogue = LayoutInflater.from(getContext()).inflate(R.layout.layout_add_category, null);

                EditText etxt_cn, etxt_ss;
                Button btn_ec, btn_ecClose;

                etxt_cn = categoryDialogue.findViewById(R.id.etxt_cn);
                etxt_ss = categoryDialogue.findViewById(R.id.etxt_ss);
                btn_ec = categoryDialogue.findViewById(R.id.btn_ec);
                btn_ecClose = categoryDialogue.findViewById(R.id.btn_ecClose);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(categoryDialogue);
                AlertDialog alert = builder.create();
                alert.show();

                btn_ec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, Object> cmap = new HashMap<>();
                        cmap.put("CategoryName", etxt_cn.getText().toString().trim());
                        cmap.put("SelectStatus", etxt_ss.getText().toString().trim());

                        reference.child("Categories").push().setValue(cmap);
                        Toast.makeText(getContext(), "Category Added", Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });
                btn_ecClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });
            }
        });

        return view;
    }
}