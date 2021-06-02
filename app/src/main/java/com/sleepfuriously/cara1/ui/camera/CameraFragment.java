package com.sleepfuriously.cara1.ui.camera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.sleepfuriously.cara1.R;
import com.sleepfuriously.cara1.ui.login.LoginFragment;

public class CameraFragment extends Fragment {

    //-------------------------
    //  constants
    //-------------------------

    private static final String TAG = CameraFragment.class.getSimpleName();

    //-------------------------
    //  data
    //-------------------------

    private CameraViewModel cameraViewModel;

    //-------------------------
    //  overridden methods
    //-------------------------

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cameraViewModel =
                ViewModelProviders.of(this).get(CameraViewModel.class);
        View root = inflater.inflate(R.layout.camera_frag_layout, container, false);
        final TextView textView = root.findViewById(R.id.title_tv);
        cameraViewModel.getText().observe(this, new Observer<String>() {
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

        Button clickButt = requireActivity().findViewById(R.id.camera_butt);
        clickButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo:  do camera stuff

                // Now that we have the data, go to the next Fragment
                NavHostFragment.findNavController(CameraFragment.this).navigate(R.id.action_navigation_camera_to_navigation_senddata);
            }
        });
    }

    //-------------------------
    //  public methods
    //-------------------------

    //-------------------------
    //  private methods
    //-------------------------

}