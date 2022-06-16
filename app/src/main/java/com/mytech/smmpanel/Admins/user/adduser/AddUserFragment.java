package com.mytech.smmpanel.Admins.user.adduser;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mytech.smmpanel.R;


import java.util.HashMap;

public class AddUserFragment extends Fragment {

    Context context;
    TextInputEditText til_fn, til_ln, til_un, til_em, til_skype, til_mn, til_p, til_cp;
    Button btn_AU;
    String fn, ln, un, em, skype, mn, p, cp;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        context = getContext();
        
        til_fn = view.findViewById(R.id.til_fn);
        til_ln = view.findViewById(R.id.til_ln);
        til_un = view.findViewById(R.id.til_un);
        til_em = view.findViewById(R.id.til_em);
        til_skype = view.findViewById(R.id.til_skype);
        til_mn = view.findViewById(R.id.til_mn);
        til_p = view.findViewById(R.id.til_p);
        til_cp = view.findViewById(R.id.til_cp);
        btn_AU = view.findViewById(R.id.btn_AU);

        reference = FirebaseDatabase.getInstance().getReference().child("SocialServiceProvider").child("Users");
        auth = FirebaseAuth.getInstance();

        btn_AU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fn = til_fn.getText().toString().trim();
                ln = til_ln.getText().toString().trim();
                un = til_un.getText().toString().trim();
                em = til_em.getText().toString().trim();
                skype = til_skype.getText().toString().trim();
                mn = til_mn.getText().toString().trim();
                p = til_p.getText().toString().trim();
                cp = til_cp.getText().toString().trim();

                if(p.equals(cp)){
                    createUser(un, em, p);
                }else{
                    Toast.makeText(context,"Please Confirm your Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void createUser(String userName, String email, String password) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                            .setDisplayName(userName)
                            .build();
                    assert user != null;
                    user.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("First Name", fn);
                                map.put("Last Name", ln);
                                map.put("User Name", un);
                                map.put("E-Mail", em);
                                map.put("Skype", skype);
                                map.put("Mobile", mn);
                                map.put("Password", p);
                                reference.child(un).setValue(map);

                                Toast.makeText(context, "Account Created for: "+userName, Toast.LENGTH_SHORT).show();
                                auth.signOut();
                            }
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}