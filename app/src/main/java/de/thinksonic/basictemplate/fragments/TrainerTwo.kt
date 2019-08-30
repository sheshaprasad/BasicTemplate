package de.thinksonic.basictemplate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.thinksonic.basictemplate.R

/**
    Created By : Shesha Vasukhi Prasad
    Date : 16-Jul-2019
    Time : 01:44
    For Project : BasicTemplate
*/

class TrainerTwo : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_trainer_screens1, container, false)
    }

}
