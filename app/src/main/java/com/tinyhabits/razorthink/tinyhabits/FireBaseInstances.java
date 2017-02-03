package com.tinyhabits.razorthink.tinyhabits;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Deepak Detni on 01-02-2017.
 */

public class FireBaseInstances {

    public static FireBaseInstances instance;

    // Firebase instance variables
    public FirebaseAuth mFirebaseAuth;
    public FirebaseUser mFirebaseUser;
    public DatabaseReference mFirebaseDatabaseReference;
    public FirebaseDatabase mFirebaseDatabase;

    public static FireBaseInstances getInstance(){
        if (instance == null){
            return instance = new FireBaseInstances();
        }else
            return instance;
    }

    FireBaseInstances(){
        //Initialize firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mFirebaseDatabase.setPersistenceEnabled(true);
    }

    public DatabaseReference getUsersPath(){
        // get reference to 'users' node
       return mFirebaseDatabaseReference = mFirebaseDatabase.getReference("users");
    }

    public DatabaseReference getUsersPathByUid(String Uid){
        return mFirebaseDatabaseReference = mFirebaseDatabase.getReference("users/"+Uid);
    }

    public FirebaseUser getFireBaseUser(){
        return mFirebaseUser;
    }

    public Task<Void> insertChildToUsers(UserLogin userLogin){
        if(mFirebaseUser == null)
            mFirebaseUser = mFirebaseAuth.getCurrentUser();
         return mFirebaseDatabaseReference.child(mFirebaseUser.getUid()).setValue(userLogin);
    }

    public Task<Void> updateChildOfUsers(String key, String value){
        return mFirebaseDatabaseReference.child(key).setValue(value);
    }
}
