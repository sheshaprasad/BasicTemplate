package de.thinksonic.basictemplate.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import de.thinksonic.basictemplate.R;

/*
    Created By : Shesha Vasukhi Prasad
    Date : 16-Jul-2019
    Time : 01:44
    For Project : BasicTemplate
*/


public class TrainerOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_trainer_screens, container, false);
    }

}
