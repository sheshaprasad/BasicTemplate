package de.thinksonic.basictemplate.tools

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton

import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView

import com.google.android.material.snackbar.Snackbar

import de.thinksonic.basictemplate.application_ui.SplashActivity
import de.thinksonic.basictemplate.app_initializer.AppInit
import de.thinksonic.basictemplate.R
import pl.droidsonroids.gif.GifImageButton

/**
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 22:45
    For Project : BasicTemplate
*/


class Utils {

    fun isConnectingToInternet(context: Context): Boolean {
        val connectivity = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info.indices)
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }

        }
        return false
    }

    companion object {

        private val ANIMATION_TRANSITION_TIME: Long = 300

        internal lateinit var wifiT: TextView
        internal lateinit var mdT: TextView
        internal lateinit var wifiGif: GifImageButton
        internal lateinit var wifi: ToggleButton
        internal lateinit var md: ToggleButton
        internal var alert: AlertDialog? = null


        fun dpToPx(c: Context, dp: Int): Int {
            val r = c.resources
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
        }

        fun showElements(mHiddenLinearLayout: View) {
            // Precondition
            // Animate the hidden linear layout as visible and set
            // the alpha as 0.0. Otherwise the animation won't be shown
            mHiddenLinearLayout.visibility = View.VISIBLE
            mHiddenLinearLayout.alpha = 0.0f
            mHiddenLinearLayout
                    .animate()
                    .setDuration(ANIMATION_TRANSITION_TIME)
                    .alpha(1.0f)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)

                            mHiddenLinearLayout.animate().setListener(null)
                        }
                    })
        }

        fun hideElements(mHiddenLinearLayout: View) {

            // Animate the hidden linear layout as visible and set
            mHiddenLinearLayout
                    .animate()
                    .setDuration(ANIMATION_TRANSITION_TIME)
                    .alpha(0.0f)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {

                            super.onAnimationEnd(animation)
                            mHiddenLinearLayout.visibility = View.GONE
                            // Hack: Remove the listener. So it won't be executed when
                            // any other animation on this view is executed
                            mHiddenLinearLayout.animate().setListener(null)

                        }
                    })
        }

        fun ShowNetworkAlert(activity: Activity) {


            val alertDialogBuilder = AlertDialog.Builder(activity, R.style.UploadDialogTheme)
            val layoutInflater = activity.layoutInflater
            val view = layoutInflater.inflate(R.layout.internet_check, null)
            alertDialogBuilder.setCustomTitle(view)
            alertDialogBuilder.setCancelable(false)

            try {
                wifi = view.findViewById(R.id.wifiBtn)
                md = view.findViewById(R.id.mdBtn)
                mdT = view.findViewById(R.id.mdText)
                wifiT = view.findViewById(R.id.wifiText)
                wifiGif = view.findViewById(R.id.wifiGif)
                val wifiImage = view.findViewById<ImageView>(R.id.wifiImage)

                wifi.setOnCheckedChangeListener { compoundButton, b ->
                    if (b) {
                        try {

                            wifiImage.visibility = View.GONE
                            wifiGif.visibility = View.VISIBLE
                            val wifiManager = activity.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                            wifiManager.isWifiEnabled = true


                        } catch (e: NullPointerException) {
                            Toast.makeText(activity, "Exception Caught", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        try {
                            wifiImage.visibility = View.VISIBLE
                            wifiGif.visibility = View.GONE
                            val wifiManager = activity.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                            wifiManager.isWifiEnabled = false

                        } catch (e: NullPointerException) {
                            Toast.makeText(activity, "Exception Caught", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
                wifi.setOnClickListener { }

                wifiT.setOnClickListener { wifi.performClick() }

                md.setOnCheckedChangeListener { compoundButton, b ->
                    if (b) {
                        md.isChecked = true
                        val intent = Intent(Intent.ACTION_MAIN)
                        intent.component = ComponentName("com.android.settings",
                                "com.android.settings.Settings\$DataUsageSummaryActivity")
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        activity.startActivity(intent)
                        alert!!.dismiss()
                    } else {
                        md.isChecked = false
                    }
                }
                mdT.setOnClickListener { md.performClick() }
                alert = alertDialogBuilder.create()
                alert!!.show()
                try {
                    alert!!.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
                    val window = alert!!.window
                    window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }

            } catch (e: Exception) {
                Toast.makeText(activity, "ExceptionC Caught", Toast.LENGTH_SHORT).show()
            }

        }

        fun DismissNetworkAlert(activity: Activity) {
            if (alert != null) {
                alert!!.dismiss()
                if (!AppInit.SplashCreated) {
                    AppInit.SplashCreated = true
                    val main = Intent(activity, SplashActivity::class.java)
                    main.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    main.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    activity.startActivity(main)
                    activity.finish()
                }
            }
        }

        fun SnackBar(context: Context, message: String, parentView: View) {

            val duration = 5000
            val mInflater = LayoutInflater.from(context)
            val snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT).setDuration(duration)
            val layout = snackbar.view as Snackbar.SnackbarLayout
            layout.setBackgroundColor(Color.TRANSPARENT)

            val textView = layout.findViewById<View>(R.id.snackbar_text) as TextView
            textView.visibility = View.INVISIBLE


            // Inflate our custom view
            val snackView = mInflater.inflate(R.layout.snackbar_layout, null)
            // Configure the view
            val imageView = snackView.findViewById<View>(R.id.snack_image) as ImageView
            //Snackbar Background
            val snackParentView = snackView.findViewById<CardView>(R.id.snackParentView)

            val snackMessage = snackView.findViewById<View>(R.id.snack_message) as TextView
            snackMessage.text = message

            val snackButton = snackView.findViewById<View>(R.id.snack_button) as TextView
            snackButton.setOnClickListener { snackButton.text = "Clicked" }


            //If the view is not covering the whole snackbar layout, add this line
            layout.setPadding(16, 16, 16, 16)

            // Add the view to the Snackbar's layout
            layout.addView(snackView, 0)
            snackbar.show()
        }
    }

}
