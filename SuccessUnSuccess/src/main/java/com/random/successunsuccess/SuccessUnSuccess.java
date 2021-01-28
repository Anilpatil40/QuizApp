package com.random.successunsuccess;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

public class SuccessUnSuccess {
    private static final int DEFAULT_TIME = 1000;

    // Success Dialog
    public static void showSuccessDialog(Context context){
        showSuccessDialog(context,DEFAULT_TIME,null);
    }

    public static void showSuccessDialog(Context context,int timeInMillis){
        showSuccessDialog(context, timeInMillis,null);
    }

    public static void showSuccessDialog(Context context,Listener listener){
        showSuccessDialog(context, DEFAULT_TIME, listener);
    }

    public static void showSuccessDialog(Context context,int timeInMillis,Listener listener){
        showDialog(context,timeInMillis,listener,true);
    }

    private static void showDialog(Context context,int timeInMillis,Listener listener,boolean isSuccess){
        View view = null;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (isSuccess)
            view = LayoutInflater.from(context).inflate(R.layout.success_layout,null);
        else
            view = LayoutInflater.from(context).inflate(R.layout.error_layout,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        CardView cardView = view.findViewById(R.id.cardView);
        cardView.setScaleX(0);
        cardView.setScaleY(0);
        cardView.setAlpha(0);
        cardView.animate().scaleX(1).scaleY(1).alpha(1).setDuration(300);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                if (listener!=null)
                    listener.onDismiss();
            }
        },timeInMillis);
    }

    // Error dialog
    public static void showErrorDialog(Context context){
        showErrorDialog(context,DEFAULT_TIME,null);
    }

    public static void showErrorDialog(Context context,int timeInMillis){
        showErrorDialog(context, timeInMillis,null);
    }

    public static void showErrorDialog(Context context,Listener listener){
        showErrorDialog(context, DEFAULT_TIME, listener);
    }

    public static void showErrorDialog(Context context,int timeInMillis,Listener listener){
        showDialog(context,timeInMillis,listener,false);
    }

    public interface Listener{
        void onDismiss();
    }
}
