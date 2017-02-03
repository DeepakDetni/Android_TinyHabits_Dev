package com.tinyhabits.razorthink.tinyhabits;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignUpActivity extends AppCompatActivity implements
        View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 9001;

    private Button mSignUpButton;

    //Firebase instance variable
    private FirebaseAuth mFirebaseAuth;
    private GoogleApiClient mGoogleApiClient;

    FireBaseInstances mFireBaseInstances = FireBaseInstances.getInstance();

    private void findViews(){
        mSignUpButton = (Button)findViewById(R.id.signup_button);
        mSignUpButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViews();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup_button:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.e("deepak", result.toString());
            if (result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                Log.e("deepak", account.getEmail());
//                if(account.getEmail().contains("razorthink")){
                    firebaseAuthWithGoogle(account);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Please sign up using razorthink email id", Toast.LENGTH_SHORT).show();
//                    signIn();
//                }
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        Log.d("deepak", "firebaseAuthWithGoogle: "+account.getId()+"email ID: "+account.getEmail()+"image: "+account.getPhotoUrl());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("deepak", "signInWithCredential:onComplete:"+ task.isSuccessful());

                if (!task.isSuccessful()){
                    Log.d("deepak", "signInWithCredential", task.getException());
                }
                else{
                    mFireBaseInstances.getUsersPath();
                    UserLogin userLogin = new UserLogin(account.getDisplayName(), account.getPhotoUrl().toString(), account.getEmail(), null);
                    mFireBaseInstances.insertChildToUsers(userLogin).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            startActivity(new Intent(SignUpActivity.this, HomePageActivity.class));
                            finish();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
