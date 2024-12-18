package com.yugi.filemanager.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yugi.filemanager.MainActivity;
import com.yugi.filemanager.R;
import com.yugi.filemanager.adapter.HomeAppAdapter;
import com.yugi.filemanager.databinding.FragmentMoreBinding;
import com.yugi.filemanager.interfaces.HomeAppClickListener;
import com.yugi.filemanager.utils.Utils;


public class MoreFragment extends Fragment implements HomeAppClickListener {

    private FragmentMoreBinding binding;
    private String[] apps = {"Images", "Audio", "Videos", "Zips", "Apps", "Document", "Download", "Favorite"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Utils.setStatusBarColor(R.color.white,getActivity(),true);
        binding = FragmentMoreBinding.inflate(inflater);

        binding.rv.setLayoutManager(new GridLayoutManager(getContext(),4));
        HomeAppAdapter homeAppAdapter = new HomeAppAdapter(getActivity(),apps,this);
        binding.rv.setAdapter(homeAppAdapter);
        binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());

        binding.icFavourite.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(MainActivity.MAIN_CONTAINER, new FavouriteFragment()).addToBackStack(null).commit());

        binding.icLargeFiles.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(MainActivity.MAIN_CONTAINER,new LargeFileFragment()).addToBackStack(null).commit());

        return binding.getRoot();
    }

    @Override
    public void onHomeAppClick(String name) {
        if (apps[0].equals(name)||apps[1].equals(name)||apps[2].equals(name)){
            Bundle bundle = new Bundle();
            bundle.putString("FILE", name);
            ImageFragment imageFragment = new ImageFragment();
            imageFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(MainActivity.MAIN_CONTAINER, imageFragment).addToBackStack(null).commit();
        }else if (apps[3].equals(name)){
            getActivity().getSupportFragmentManager().beginTransaction().replace(MainActivity.MAIN_CONTAINER,new ZipFragment()).addToBackStack(null).commit();
        } else if (apps[4].equals(name)){
            getActivity().getSupportFragmentManager().beginTransaction().replace(MainActivity.MAIN_CONTAINER,new AppsFragment()).addToBackStack(null).commit();
        }else if (apps[5].equals(name)){
            getActivity().getSupportFragmentManager().beginTransaction().replace(MainActivity.MAIN_CONTAINER,new DocumentFragment()).addToBackStack(null).commit();
        }else if (apps[6].equals(name)){
            getActivity().getSupportFragmentManager().beginTransaction().replace(MainActivity.MAIN_CONTAINER,new DownloadFragment()).addToBackStack(null).commit();
        }
    }

    
}