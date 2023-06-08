package com.pang.smartbill.ui.person;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pang.smartbill.MainActivity;
import com.pang.smartbill.R;
import com.pang.smartbill.adapter.AccountAdapter;
import com.pang.smartbill.databinding.FragmentPersonBinding;

import  com.pang.smartbill.ui.person.PersonMain;

import java.util.ArrayList;

public class PersonFragment extends Fragment {


    private FragmentPersonBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonViewModel personViewModel =
                new ViewModelProvider(this).get(PersonViewModel.class);

        binding = FragmentPersonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPerson;
        personViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

//        Intent intent = new Intent(getActivity(), PersonMain.class);
//        startActivity(intent);


//        PersonMain personMain = new PersonMain();
//
//        personMain.initTime();
////        personMain.initView();
////        personMain.preferences = personMain.getSharedPreferences("budget", Context.MODE_PRIVATE);
////
////        personMain.addLVHeaderView();
////        personMain.mDatas = new ArrayList<>();
////
////        personMain.todayLv.setAdapter(personMain.adapter);


        return root;


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

