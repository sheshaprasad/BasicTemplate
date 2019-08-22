package de.thinksonic.basictemplate.application_ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.android.volley.VolleyError;
import org.json.JSONObject;
import de.thinksonic.basictemplate.Fragments.TrainerOne;
import de.thinksonic.basictemplate.Fragments.TrainerThree;
import de.thinksonic.basictemplate.Fragments.TrainerTwo;
import de.thinksonic.basictemplate.app_initializer.BaseActivity;
import de.thinksonic.basictemplate.R;
import de.thinksonic.basictemplate.Tools.RequestTypes;

/*
    Created By : Shesha Vasukhi Prasad
    Date : 16-Jul-2019
    Time : 01:36
    For Project : BasicTemplate
*/

public class TrainerActivity extends BaseActivity {

    CardView one,two,three;
    TextView skip, next;
    private ViewPager mViewPager;
    private TrainerPagerAdapter mTrainerPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

    }

    @Override
    public void initViews() {

        if(!initialized) {
            initialized = true;
            one = findViewById(R.id.one);
            two = findViewById(R.id.two);
            three = findViewById(R.id.three);
            skip = findViewById(R.id.skip);
            next = findViewById(R.id.next);
            mTrainerPagerAdapter = new TrainerPagerAdapter(getSupportFragmentManager());
            mViewPager = findViewById(R.id.container);
            mViewPager.setAdapter(mTrainerPagerAdapter);

            setNavigator();
            initListeners();
        }
    }

    private void initListeners() {
        skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent main = new Intent(TrainerActivity.this, ChooseActivity.class);
                startActivity(main);
                finish();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mViewPager.getCurrentItem() != (mTrainerPagerAdapter.getCount() - 1)) {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                } else {
                    Intent main = new Intent(TrainerActivity.this, ChooseActivity.class);
                    startActivity(main);
                    finish();
                }
                setNavigator();
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int position) {
                // TODO Auto-generated method stub
                setNavigator();
            }
        });

    }

    public void setNavigator() {

        if(mViewPager.getCurrentItem()==0){
            one.setCardBackgroundColor(Color.BLUE);
            two.setCardBackgroundColor(Color.GRAY);
            three.setCardBackgroundColor(Color.GRAY);
        }else if(mViewPager.getCurrentItem()==1){
            one.setCardBackgroundColor(Color.GRAY);
            two.setCardBackgroundColor(Color.BLUE);
            three.setCardBackgroundColor(Color.GRAY);
        }else{
            one.setCardBackgroundColor(Color.GRAY);
            two.setCardBackgroundColor(Color.GRAY);
            three.setCardBackgroundColor(Color.BLUE);
        }

    }

    @Override
    public void onSuccess(RequestTypes requestTypes, JSONObject jsonObject) {

    }

    @Override
    public void onError(RequestTypes requestTypes, VolleyError volleyError) {

    }

    public class TrainerPagerAdapter extends FragmentPagerAdapter {

        TrainerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0: return new TrainerOne();

                case 1: return new TrainerTwo();

                case 2: return new TrainerThree();

                default:return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
