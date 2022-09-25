package com.sosa.dummyapp.ui.controller;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sosa.dummyapp.R;
import com.sosa.dummyapp.ui.settings.SettingsViewModel;

public class ControllerFragment extends Fragment {

    private static final String TAG = "ControllerFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "Created new instance");
        View view = inflater.inflate(R.layout.fragment_controller, container, false);
        Button button1 = (Button) view.findViewById(R.id.SendPowerSignal1);
        button1.setOnClickListener(v -> {
            // Do Something
            Toast.makeText(super.getContext(), "Button 1 Pressed", Toast.LENGTH_SHORT).show();
        });

        Button button2 = (Button) view.findViewById(R.id.SendPowerSignal2);
        button2.setOnClickListener(v -> {
            // Do Something
            Toast.makeText(super.getContext(), "Button 2 Pressed", Toast.LENGTH_SHORT).show();
        });

        Button button3 = (Button) view.findViewById(R.id.SendPowerSignal3);
        button3.setOnClickListener(v -> {
            // Do Something
            Toast.makeText(super.getContext(), "Button 3 Pressed", Toast.LENGTH_SHORT).show();
        });
        return view;
    }


}