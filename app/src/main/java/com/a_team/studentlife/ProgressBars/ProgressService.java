package com.a_team.studentlife.ProgressBars;


import android.app.ProgressDialog;
import android.content.Context;

public class ProgressService {
    public static void showDialogMessage(Context context, String title, String message,
                                         int progressStyle, int max, boolean ideterminate) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(progressStyle);
        progressDialog.setMax(max);
        progressDialog.setIndeterminate(ideterminate);
        progressDialog.show();
    }
}
