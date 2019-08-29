package de.thinksonic.basictemplate.tools


/*
    Created By : Shesha Vasukhi Prasad
    Date : 16-Jul-2019
    Time : 01:11
    For Project : BasicTemplate
*/

import android.content.Context
import android.content.SharedPreferences

import android.content.Context.MODE_PRIVATE

class UserPreference(context: Context) {

    internal var preferences: SharedPreferences

    internal var editor: SharedPreferences.Editor


    init {

        preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        editor = preferences.edit()
    }

}
