package de.thinksonic.basictemplate.app_initializer


/*
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 23:34
    For Project : BasicTemplate
*/

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import de.thinksonic.basictemplate.interfaces.ActivityCreated
import de.thinksonic.basictemplate.interfaces.VolleyHandler

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(), VolleyHandler, ActivityCreated {
    var initialized = false
    var parentView: View? = null
}
