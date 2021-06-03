package com.sleepfuriously.cara1.ui.camera;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.sleepfuriously.cara1.R;

/**
 * This Fragment displays what the camera sees and lets the user take
 * a picture.
 */
public class CameraFragment extends Fragment {

    //-------------------------
    //  constants
    //-------------------------

    private static final String TAG = CameraFragment.class.getSimpleName();

    private static final int CAMERA_REQUEST_CODE = 2238;

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
//        final TextView textView = root.findViewById(R.id.title_tv);
        cameraViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
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
                if (checkCameraPermissions()) {
                    takePicture();
                }
            }
        });

        // hide soft keyboard (in case it somehow was left open after logging in)
        View v = requireActivity().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }

        checkCameraPermissions();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_REQUEST_CODE) {
            // Yes, this is in response to our request for camera permission
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // Warn them that we NEED a camera and exit.  Hopefully they'll change their mind later.
                Toast.makeText(requireContext(), "This app requires the use of a camera!", Toast.LENGTH_LONG).show();
            }
        }
    }

    //-------------------------
    //  public methods
    //-------------------------

    //-------------------------
    //  private methods
    //-------------------------

    /**
     * Checks to see if we have camera permissions.  If not, then a dialog asking for permission is initiated.
     * The results of that dialog are handled in {@link #onRequestPermissionsResult(int, String[], int[])}.
     *
     * @return  TRUE if we already have permission.
     *          FALSE if we don't have permission.  A dialog will be initiated.
     */
    private boolean checkCameraPermissions() {
        if (requireContext().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            // start the dialog to get permission.  see onRequestPermissionResult() for user's response.
            requestPermissions(new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            return false;
        }
    }

    /**
     * Takes a picture and goes to the next fragment.
     */
    private void takePicture() {
        // todo:  do camera stuff

        // Now that we have the data, go to the next Fragment
        NavHostFragment.findNavController(CameraFragment.this).navigate(R.id.action_navigation_camera_to_navigation_senddata);

    }
}