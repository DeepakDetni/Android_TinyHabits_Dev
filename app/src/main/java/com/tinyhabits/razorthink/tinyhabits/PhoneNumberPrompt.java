package com.tinyhabits.razorthink.tinyhabits;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak Detni on 01-02-2017.
 */

public class PhoneNumberPrompt {

    public static void showPhoneNumberPrompt(Activity activity, final Activity callback) {
        showPhoneNumberPrompt(activity, callback);
    }

    public static void showPhoneNumberPrompt(final Activity activity, final AlertDialogCallback<String> callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View promptView = layoutInflater.inflate(R.layout.alert_dialog, null);
        builder.setView(promptView);
        builder.setCancelable(false);

        final EditText mobileNumber = (EditText)promptView.findViewById(R.id.input_mobile_number);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.alertDialogCallback(mobileNumber.getText().toString());
                dialog.dismiss();
            }
        });

        builder.show();
    }

}
