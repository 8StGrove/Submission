package c3477358.ahmed.haseeb.submission.afinal.com.final_submission_2018;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            ListFragment ListFragment = new ListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, ListFragment)
                    .commit();
        }

    }
}
