package com.tinyhabits.razorthink.tinyhabits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectGroupMembersActivity extends AppCompatActivity {

    ListView employeesListView;
    ArrayList<EmployeesDetails> employeesDetailsList = new ArrayList<>();
    EmployeeAdapter adapter;

    FireBaseInstances mFireBaseInstances = FireBaseInstances.getInstance();

    void findViews(){
        employeesListView = (ListView)findViewById(R.id.employees_listview);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group_members);

        findViews();

        getSupportActionBar().setTitle("Select group members");

        adapter = new EmployeeAdapter(this, employeesDetailsList, mFireBaseInstances.getFireBaseUser().getUid());
        employeesListView.setAdapter(adapter);

        mFireBaseInstances.getUsersPath().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot singleDataSnapshot : dataSnapshot.getChildren()) {
                    EmployeesDetails employeesDetails = singleDataSnapshot.getValue(EmployeesDetails.class);

//                    EmployeesDetails singleEmployeeDetails = new EmployeesDetails();
//
//                    singleEmployeeDetails.name = employeesDetails.getName();
//                    singleEmployeeDetails.photoUrl = employeesDetails.getPhotoUrl();
//                    singleEmployeeDetails.emailAddress = employeesDetails.getMobileNumber();
//                    singleEmployeeDetails.Uid = employeesDetails.getUid();
//
//                    Log.e("deepak", " "+singleEmployeeDetails.name+" "+singleEmployeeDetails.photoUrl+" "+singleEmployeeDetails.emailAddress+
//                            " "+singleEmployeeDetails.Uid);

                    Log.e("deepak", "key "+singleDataSnapshot.getKey().toString());
                    if(!employeesDetails.emailAddress.equalsIgnoreCase(mFireBaseInstances.getFireBaseUser().getEmail()))
                        employeesDetailsList.add(employeesDetails);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
