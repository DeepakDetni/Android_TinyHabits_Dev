package com.tinyhabits.razorthink.tinyhabits;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomePageActivity extends AppCompatActivity implements
        View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    public static final int CREATE_GROUP_REQUEST_CODE = 1;

    private Button clickHereButton, createGroupButton;
    private TextView userName, emailId, userPhno;
    private ImageView profilePic;

    FireBaseInstances mFireBaseInstances = FireBaseInstances.getInstance();

    private String name, userPhnoTxt;
    private Uri photoUrl;
    private String emailAddress;

    private void findViews() {
        clickHereButton = (Button)findViewById(R.id.click_button);
        clickHereButton.setOnClickListener(this);
        createGroupButton = (Button)findViewById(R.id.create_group_button);
        createGroupButton.setOnClickListener(this);
        userName = (TextView)findViewById(R.id.user_name);
        emailId = (TextView)findViewById(R.id.user_email);
        userPhno = (TextView)findViewById(R.id.user_phno);
        profilePic = (ImageView)findViewById(R.id.profile_pic);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        findViews();

        if(mFireBaseInstances.getFireBaseUser() == null){
            startActivity(new Intent(this, SignUpActivity.class));
            finish();
            return;
        }else{
            name = mFireBaseInstances.getFireBaseUser().getDisplayName();
            photoUrl = mFireBaseInstances.getFireBaseUser().getPhotoUrl();
            emailAddress = mFireBaseInstances.getFireBaseUser().getEmail();
        }

        userName.setText(name);
        emailId.setText(emailAddress);
        Picasso.with(this).load(photoUrl).resize(480, 480).placeholder(R.drawable.rzt_globe).into(profilePic);

        mFireBaseInstances.getUsersPathByUid(mFireBaseInstances.getFireBaseUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final UserLogin userLogin = dataSnapshot.getValue(UserLogin.class);
                if(userLogin != null) {
                    if (userLogin.mobileNumber == null) {
                        Log.e("deepak", "userlogin " + userLogin.name);
                        PhoneNumberPrompt.showPhoneNumberPrompt(HomePageActivity.this, new AlertDialogCallback<String>() {
                            @Override
                            public void alertDialogCallback(final String allType) {
                                mFireBaseInstances.updateChildOfUsers("mobileNumber", allType).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        userPhno.setText(allType);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Please try again later", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    } else
                        userPhno.setText(userLogin.mobileNumber);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.click_button:

                break;

            case R.id.create_group_button:
                    startActivity(new Intent(HomePageActivity.this, SelectGroupMembersActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_GROUP_REQUEST_CODE){

        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
