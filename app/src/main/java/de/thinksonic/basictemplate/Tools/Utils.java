package de.thinksonic.basictemplate.Tools;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;

import de.thinksonic.basictemplate.application_ui.SplashActivity;
import de.thinksonic.basictemplate.app_initializer.AppInit;
import de.thinksonic.basictemplate.R;
import pl.droidsonroids.gif.GifImageButton;


/*
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 22:45
    For Project : BasicTemplate
*/


public class Utils {

    private static final long ANIMATION_TRANSITION_TIME = 300;

    static TextView wifiT, mdT;
    static GifImageButton wifiGif;
    static ToggleButton wifi,md;
    static AlertDialog alert;


    public static int dpToPx(Context c, int dp) {
        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static void showElements(final View mHiddenLinearLayout) {
        // Precondition
        // Animate the hidden linear layout as visible and set
        // the alpha as 0.0. Otherwise the animation won't be shown
        mHiddenLinearLayout.setVisibility(View.VISIBLE);
        mHiddenLinearLayout.setAlpha(0.0f);
        mHiddenLinearLayout
                .animate()
                .setDuration(ANIMATION_TRANSITION_TIME)
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        mHiddenLinearLayout.animate().setListener(null);
                    }
                });
    }

    public static void hideElements(final View mHiddenLinearLayout) {

        // Animate the hidden linear layout as visible and set
        mHiddenLinearLayout
                .animate()
                .setDuration(ANIMATION_TRANSITION_TIME)
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                        super.onAnimationEnd(animation);
                        mHiddenLinearLayout.setVisibility(View.GONE);
                        // Hack: Remove the listener. So it won't be executed when
                        // any other animation on this view is executed
                        mHiddenLinearLayout.animate().setListener(null);

                    }
                });
    }

    public boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public static void ShowNetworkAlert(final Activity activity){


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.UploadDialogTheme);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.internet_check, null);
        alertDialogBuilder.setCustomTitle(view);
        alertDialogBuilder.setCancelable(false);

        try {
            wifi = view.findViewById(R.id.wifiBtn);
            md = view.findViewById(R.id.mdBtn);
            mdT = view.findViewById(R.id.mdText);
            wifiT = view.findViewById(R.id.wifiText);
            wifiGif = view.findViewById(R.id.wifiGif);
            final ImageView wifiImage = view.findViewById(R.id.wifiImage);

            wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        try {

                            wifiImage.setVisibility(View.GONE);
                            wifiGif.setVisibility(View.VISIBLE);
                            WifiManager wifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                            wifiManager.setWifiEnabled(true);


                        } catch (NullPointerException e) {
                            Toast.makeText(activity, "Exception Caught", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        try {
                            wifiImage.setVisibility(View.VISIBLE);
                            wifiGif.setVisibility(View.GONE);
                            WifiManager wifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                            wifiManager.setWifiEnabled(false);

                        } catch (NullPointerException e) {
                            Toast.makeText(activity, "Exception Caught", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            wifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            wifiT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wifi.performClick();
                }
            });

            md.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (b) {
                        md.setChecked(true);
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setComponent(new ComponentName("com.android.settings",
                                "com.android.settings.Settings$DataUsageSummaryActivity"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(intent);
                        alert.dismiss();
                    } else {
                        md.setChecked(false);
                    }
                }
            });
            mdT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    md.performClick();
                }
            });
            alert = alertDialogBuilder.create();
            alert.show();
            try {
                alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                Window window = alert.getWindow();
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            Toast.makeText(activity,"ExceptionC Caught",Toast.LENGTH_SHORT).show();
        }
    }

    public static void DismissNetworkAlert(Activity activity){
        if(alert!=null) {
            alert.dismiss();
            if (!AppInit.SplashCreated) {
                AppInit.SplashCreated = true;
                Intent main = new Intent(activity, SplashActivity.class);
                main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                main.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                activity.startActivity(main);
                activity.finish();
            }
        }
    }

    public static void SnackBar(Context context, String message,View parentView){

        int duration = 5000;
        LayoutInflater mInflater = LayoutInflater.from(context);
        Snackbar snackbar = Snackbar.make(parentView,message,Snackbar.LENGTH_SHORT).setDuration(duration);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.setBackgroundColor(Color.TRANSPARENT);

        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);


        // Inflate our custom view
        View snackView = mInflater.inflate(R.layout.snackbar_layout, null);
        // Configure the view
        ImageView imageView = (ImageView) snackView.findViewById(R.id.snack_image);
        //Snackbar Background
        CardView snackParentView = snackView.findViewById(R.id.snackParentView);

        TextView snackMessage= (TextView) snackView.findViewById(R.id.snack_message);
        snackMessage.setText(message);

        final TextView snackButton = (TextView) snackView.findViewById(R.id.snack_button);
        snackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackButton.setText("Clicked");
            }
        });


        //If the view is not covering the whole snackbar layout, add this line
        layout.setPadding(16,16,16,16);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);
        snackbar.show();
    }

}
