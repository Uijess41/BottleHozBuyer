package com.maestros.bottlehoz.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.FeedAdapter;
import com.maestros.bottlehoz.adapter.RewardAdapter;
import com.maestros.bottlehoz.databinding.FragmentFeedBinding;
import com.maestros.bottlehoz.databinding.FragmentRewardBinding;
import com.maestros.bottlehoz.model.Feed;

import java.util.ArrayList;
import java.util.List;


public class RewardFragment extends Fragment {

    private RewardAdapter adapter;
    private List<Feed> feedList;
    private FragmentRewardBinding binding;
    private View view;
    private Context context;

    public RewardFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentRewardBinding.inflate(getLayoutInflater(), container, false);
        view=binding.getRoot();
        context=getActivity();

        feedList = new ArrayList<>();
        adapter = new RewardAdapter(context, feedList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        binding.rvReward.setLayoutManager(mLayoutManager);
        binding.rvReward.setAdapter(adapter);

        getData();
        return view;
    }

    private void getData() {

        Feed a = new Feed("Signup reward", "22-03-2021");
        feedList.add(a);
        a = new Feed("Refered a friend", "22-03-2021");
        feedList.add(a);a = new Feed("Made a purchase of 450", "22-03-2021");
        feedList.add(a);
        adapter.notifyDataSetChanged();

    }
}