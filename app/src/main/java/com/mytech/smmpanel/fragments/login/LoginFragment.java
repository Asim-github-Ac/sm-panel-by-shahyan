package com.mytech.smmpanel.fragments.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mytech.smmpanel.Admins.AdminMain;
import com.mytech.smmpanel.R;
import com.mytech.smmpanel.SharedPrefrence.PrefManager;
import com.mytech.smmpanel.fragments.main.MainFragment;
import com.mytech.smmpanel.fragments.signup.SignUpFragment;

public class LoginFragment extends Fragment {

    EditText etxt_UserName,lpass;
    Button btn_LogIn, btn_SignUp;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        etxt_UserName = view.findViewById(R.id.etxt_UserName_l);
        btn_LogIn = view.findViewById(R.id.btn_LogIn_l);
        btn_SignUp = view.findViewById(R.id.btn_SignUp_l);
        lpass=view.findViewById(R.id.lpass);

        btn_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefManager prefManager=new PrefManager(getContext());

               if (prefManager.getToken_Email().equals("admin")){
                   if (etxt_UserName.getText().toString().equals("admin@gmail.com") && lpass.getText().toString().equals("admin")){
                       startActivity(new Intent(getContext(),AdminMain.class));
                   }
               }else {
                   SigninUser(etxt_UserName.getText().toString().trim(),lpass.getText().toString().trim());
               }
            }
        });
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.frameLayout, new SignUpFragment()).commit();
            }
        });
        return view;
    }
    public void SigninUser(String email,String pass){
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Signin");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String userid=authResult.getUser().getUid();
                PrefManager prefManager=new PrefManager(getContext());
                prefManager.setUserID(userid);
                getParentFragmentManager().beginTransaction().replace(R.id.frameLayout, new MainFragment()).commit();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
//        PrefManager prefManager= new PrefManager(getContext());
//        if (prefManager.getToken_Email().equals("admin")){
//            startActivity(new Intent(getContext(), AdminMain.class));
//        }else if (prefManager.getToken_Email().equals("user")){
//            getParentFragmentManager().beginTransaction().replace(R.id.frameLayout, new MainFragment()).commit();
//        }
    }
}