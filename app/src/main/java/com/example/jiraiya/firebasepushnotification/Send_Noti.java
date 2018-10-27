package com.example.jiraiya.firebasepushnotification;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Send_Noti extends Fragment {

    Button send;
    FirebaseUser user;


    public Send_Noti() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vh= inflater.inflate(R.layout.send_noti, container, false);


        send =(Button)vh.findViewById(R.id.send);

        return vh;
    }

    @Override
    public void onStart() {
        super.onStart();

        user =  FirebaseAuth.getInstance().getCurrentUser();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token=FirebaseInstanceId.getInstance().getToken();
                 Map<String, String> users = new HashMap<>();
                 users.put("Token",token);
                 Log.d("Token",token);
                 FirebaseDatabase.getInstance().getReference().child("Test").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(users);


            }
        });
    }
}
