package com.example.bluerewards.ui.stores;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bluerewards.backend.DbHelper;
import com.example.bluerewards.backend.StoreUtil;
import com.example.bluerewards.databinding.FragmentStoresBinding;

import java.util.ArrayList;

public class StoresFragment extends Fragment {

    private FragmentStoresBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StoresViewModel storesViewModel =
                new ViewModelProvider(this).get(StoresViewModel.class);

        binding = FragmentStoresBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textStores;
        storesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        populateScreen();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void populateScreen() {
        DbHelper dbHelper = new DbHelper(getActivity());
        StoreUtil util = new StoreUtil(dbHelper.getDbInstance());
        ArrayList<Store> stores = util.getStores();
        LinearLayout container = binding.storesContainer;

        if (container != null) {

//            text.setText("Stores using Blue Rewards:");
//            container.addView(text);

            for (Store store : stores) {
                TextView text = new TextView(requireActivity());
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                text.setText(store.getName() + " - " + store.getLocation());
                container.addView(text);
            }
        }
    }
}