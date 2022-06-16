package com.mytech.smmpanel.fragments.signup;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mytech.smmpanel.R;
import com.mytech.smmpanel.fragments.login.LoginFragment;
import com.mytech.smmpanel.model.UserData;

public class SignUpFragment extends Fragment {

    Button btn_Login;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    EditText edname,edemail,edpas,edcpass;
    Button btn_signup;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);


        btn_Login = view.findViewById(R.id.btn_Login_s);
        edname=view.findViewById(R.id.uname);
        edemail=view.findViewById(R.id.uemail);
        edpas=view.findViewById(R.id.upass);
        edcpass=view.findViewById(R.id.ucpass);
        btn_signup=view.findViewById(R.id.usignup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebAseAuth(edemail.getText().toString().trim(),edpas.getText().toString().trim(),edname.getText().toString());
            }
        });
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
            }
        });
        return view;
    }
    public void FirebAseAuth(String email,String pass,String name){
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("SignUp");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("RegisterUserDetails");
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                UserData userData=new UserData(name,email,authResult.getUser().getUid());
                databaseReference.push().setValue(userData);
                progressDialog.dismiss();
                getParentFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}