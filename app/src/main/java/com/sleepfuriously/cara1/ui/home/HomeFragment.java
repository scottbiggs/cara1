package com.sleepfuriously.cara1.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.sleepfuriously.cara1.MainActivity;
import com.sleepfuriously.cara1.R;

import java.util.Objects;

/**
 * The first Fragment the user user sees.
 */
public class HomeFragment extends Fragment {

    //-------------------------
    //  constants
    //-------------------------

    private static final String TAG = HomeFragment.class.getSimpleName();

    //-------------------------
    //  data
    //-------------------------

    private HomeViewModel homeViewModel;

    private EditText mUserEt;
    private EditText mPassEt;

    //-------------------------
    //  overridden methods
    //-------------------------

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.title_tv);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity parentActivity = Objects.requireNonNull(getActivity());

        mUserEt = parentActivity.findViewById(R.id.name_et);
        mPassEt = parentActivity.findViewById(R.id.pass_et);

        Button loginButt = parentActivity.findViewById(R.id.login_butt);
        loginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodInput()) {
                    NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_navigation_home_to_navigation_dashboard);
                }
                else {
                    Toast.makeText(requireContext(), "Please supply a user name and a password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //-------------------------
    //  public methods
    //-------------------------

    //-------------------------
    //  private methods
    //-------------------------

    /**
     * @return  Returns FALSE if either user input or password EditTexts are empty.
     */
    private boolean goodInput() {
        if (mUserEt.getText().toString().isEmpty() ||
            mPassEt.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

}