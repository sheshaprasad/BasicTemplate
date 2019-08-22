package de.thinksonic.basictemplate.Tools;


/*
    Created By : Shesha Vasukhi Prasad
    Date : 16-Jul-2019
    Time : 01:11
    For Project : BasicTemplate
*/

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class UserPreference {

    SharedPreferences preferences;

    SharedPreferences.Editor editor;




    public UserPreference(Context context){

        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        editor = preferences.edit();
    }

}
