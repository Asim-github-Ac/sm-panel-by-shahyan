package com.mytech.smmpanel.fragments.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mytech.smmpanel.R;
import com.mytech.smmpanel.SharedPrefrence.PrefManager;
import com.mytech.smmpanel.model.CategoryModel;
import com.mytech.smmpanel.model.UserCampaign;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    EditText etxt_Category,description,link,quantity;
    TextView tvcharge;
    PrefManager prefManager;
    RadioButton radio_yes, radio_ys, radio_ycmp, radio_yws, radio_fpl, radio_yv, radio_ifg, radio_tktkf, radio_tf;
    Spinner spinner,spinservice;
    ProgressDialog progressDialog;
    DatabaseReference reference;
    List<String> categoryList;
    List<String> servicelist;

    Button btn_Submit;
    final String[] catList ={"Select One"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

//        etxt_Category = view.findViewById(R.id.etxt_Category);

        prefManager=new PrefManager(getContext());
        spinner = view.findViewById(R.id.spinner);
        btn_Submit=view.findViewById(R.id.btn_Submit);
        spinservice=view.findViewById(R.id.servicename);
        description=view.findViewById(R.id.description);
        link=view.findViewById(R.id.link);
        quantity=view.findViewById(R.id.quantity);
        tvcharge=view.findViewById(R.id.charge);
        categoryList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("SocialServiceProvider");


        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                UploadCampaigns(spinner.getSelectedItem().toString(),spinservice.getSelectedItem().toString(),description.getText().toString(),link.getText().toString(),quantity.getText().toString(),tvcharge.getText().toString(),prefManager.getUserID());
            }
        });
        reference.child("Categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoryList.clear();
                if(snapshot.exists()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        String catName = snap.child("CategoryName").getValue(String.class);
                        categoryList.add(catName);
                     //   catName.
                        System.out.println("cat name:_______________"+catName);
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categoryList);
                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(spinnerAdapter);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String txt = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), txt, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        reference.child("SocialServiceProvider").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoryList.clear();
                if(snapshot.exists()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        String catName = snap.child("Services").getValue(String.class);
                        servicelist.add(catName);
                        //   catName.
                        System.out.println("services    name:_______________"+catName);
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, servicelist);
                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinservice.setAdapter(spinnerAdapter);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
@RequiresApi(api = Build.VERSION_CODES.N)
public void UploadCampaigns(String catname, String servicename, String description, String channellink, String quantity, String charge, String userids){
    progressDialog=new ProgressDialog(getContext());
    progressDialog.setTitle("Main Page");
    progressDialog.setMessage("Please Wait");
    progressDialog.setCancelable(false);
    progressDialog.show();

    UserCampaign userCampaign=new UserCampaign(catname,servicename,description,channellink,quantity,charge,userids,getDate().toString());
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("UserCampaigns");
    databaseReference.child(userids).setValue(userCampaign).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            progressDialog.dismiss();
            Toast.makeText(getContext(), "Data Successfully Added", Toast.LENGTH_SHORT).show();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(getContext(), "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    });

}
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getDate(){
        DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
        String date=dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        String time = dfTime.format(Calendar.getInstance().getTime());
        return date + " " + time;
    }
}