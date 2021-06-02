package com.sleepfuriously.cara1.ui.senddata;

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

import com.sleepfuriously.cara1.R;

public class SendDataFragment extends Fragment {

    //-------------------------
    //  constants
    //-------------------------

    private static final String TAG = SendDataFragment.class.getSimpleName();

    //-------------------------
    //  data
    //-------------------------

    private EditText mDescriptionEt;
    private SendDataViewModel sendDataViewModel;

    //-------------------------
    //  overridden methods
    //-------------------------

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendDataViewModel =
                ViewModelProviders.of(this).get(SendDataViewModel.class);
        View root = inflater.inflate(R.layout.senddata_frag_layout, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        sendDataViewModel.getText().observe(this, new Observer<String>() {
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

        mDescriptionEt = requireActivity().findViewById(R.id.description_et);

        Button sendButt = requireActivity().findViewById(R.id.send_butt);
        sendButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mDescriptionEt.getText().toString().isEmpty()) {
                    // todo: collect data

                    // go back to the camera
                    NavHostFragment.findNavController(SendDataFragment.this).navigateUp();
                }
                else {
                    Toast.makeText(requireContext(), "Please supply a description", Toast.LENGTH_LONG).show();
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

}