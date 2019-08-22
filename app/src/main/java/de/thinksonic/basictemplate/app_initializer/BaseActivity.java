package de.thinksonic.basictemplate.app_initializer;


/*
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 23:34
    For Project : BasicTemplate
*/

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.thinksonic.basictemplate.Interface.ActivityCreated;
import de.thinksonic.basictemplate.Interface.VolleyHandler;

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity implements VolleyHandler, ActivityCreated {

    public boolean initialized = false;
    public View parentView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
