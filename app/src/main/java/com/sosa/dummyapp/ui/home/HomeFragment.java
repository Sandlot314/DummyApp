package com.sosa.dummyapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sosa.dummyapp.GetUrlContentTask;
import com.sosa.dummyapp.HomeResource;
import com.sosa.dummyapp.R;
import com.sosa.dummyapp.databinding.FragmentHomeBinding;

import org.w3c.dom.Text;

import java.util.Arrays;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private static final String localhost = "http://10.0.2.2:5000"; //emulator host loopback url     //"http://127.0.0.1:5000";

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Log.i(TAG, "Creating GetUrlContentTask");
        GetUrlContentTask task = new GetUrlContentTask(this);
        task.execute(localhost + "/home");


        return root;
    }

    public void updateHomeViews(HomeResource res){
        View root = binding.getRoot();
        TextView monthTextView = root.findViewById(R.id.monthTextView);
        TextView summaryTextView = root.findViewById(R.id.summaryDescTextView);     //Get relevant textviews
        TextView savingsTextView = root.findViewById(R.id.savingsDescTextView);
        TextView deltaTextView = root.findViewById(R.id.deltaDescTextView);
        TextView plugs = root.findViewById(R.id.plugDescTextView);

        monthTextView.setText(res.getMonth());
        summaryTextView.setText(res.getSummary());
        savingsTextView.setText(res.getSavings());                 //Update textviews with data from API call
        deltaTextView.setText(String.format("%s%%", res.getDelta().toString()));
        plugs.setText(Arrays.toString(res.getPlugs()));
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}