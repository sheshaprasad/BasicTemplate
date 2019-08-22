package de.thinksonic.basictemplate.application_ui;

import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import de.thinksonic.basictemplate.Tools.Utils;
import de.thinksonic.basictemplate.app_initializer.BaseActivity;
import de.thinksonic.basictemplate.R;
import de.thinksonic.basictemplate.Tools.RequestTypes;

/*
    Created By : Shesha Vasukhi Prasad
    Date : 16-Jul-2019
    Time : 01:52
    For Project : BasicTemplate
*/

public class ChooseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    @Override
    public void initViews() {

        parentView = findViewById(R.id.parentView);
    }

    @Override
    public void onSuccess(RequestTypes requestTypes, JSONObject jsonObject) {

    }

    @Override
    public void onError(RequestTypes requestTypes, VolleyError volleyError) {

    }

    public void SubmitClicked(View v){
        Utils.SnackBar(this,"Button Click! This is a Custom Snackbar",parentView);
    }
}
