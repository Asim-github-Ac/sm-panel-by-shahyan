package com.mytech.smmpanel.Admins.payment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.mytech.smmpanel.R;

import java.util.HashMap;

public class PaymentFragment extends Fragment {

    Button btn_AddPayment;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_payment, container, false);

        btn_AddPayment = view.findViewById(R.id.btn_AddPayment);
        reference = FirebaseDatabase.getInstance().getReference().child("SocialServiceProvider");

        btn_AddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View paymentDialogue = LayoutInflater.from(getContext()).inflate(R.layout.layout_add_payment, null);

                EditText etxt_un, etxt_ft, etxt_amount, etxt_td;
                Button btn_ap, btn_apClose;

                etxt_un = paymentDialogue.findViewById(R.id.etxt_un);
                etxt_ft = paymentDialogue.findViewById(R.id.etxt_ft);
                etxt_amount = paymentDialogue.findViewById(R.id.etxt_amount);
                etxt_td = paymentDialogue.findViewById(R.id.etxt_td);
                btn_ap = paymentDialogue.findViewById(R.id.btn_ap);
                btn_apClose = paymentDialogue.findViewById(R.id.btn_apClose);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(paymentDialogue);
                AlertDialog alert = builder.create();
                alert.show();

                btn_ap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, Object> pmap = new HashMap<>();
                        pmap.put("UserName", etxt_un.getText().toString().trim());
                        pmap.put("FundsType", etxt_ft.getText().toString().trim());
                        pmap.put("Amount", etxt_amount.getText().toString().trim());
                        pmap.put("TransactionDetails", etxt_td.getText().toString().trim());

                        reference.child("Payment").push().setValue(pmap);
                        Toast.makeText(getContext(), "Payment Added", Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });
                btn_apClose.setOnClickListener(new View.OnClickListener() {
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