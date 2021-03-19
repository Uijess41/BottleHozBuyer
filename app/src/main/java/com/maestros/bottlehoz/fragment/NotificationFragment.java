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
import com.maestros.bottlehoz.adapter.NotificationAdapter;
import com.maestros.bottlehoz.databinding.FragmentFeedBinding;
import com.maestros.bottlehoz.databinding.FragmentNotificationBinding;
import com.maestros.bottlehoz.model.Feed;

import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends Fragment {
    private NotificationAdapter adapter;
    private List<Feed> feedList;
    private FragmentNotificationBinding binding;
    private View view;
    private Context context;
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentNotificationBinding.inflate(getLayoutInflater(), container, false);
        view=binding.getRoot();
        context=getActivity();

        feedList = new ArrayList<>();
        adapter = new NotificationAdapter(context, feedList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        binding.rvFeed.setLayoutManager(mLayoutManager);
        binding.rvFeed.setAdapter(adapter);

        getData();
        return view;
    }

    private void getData() {

        Feed a = new Feed("Orkan", "Liked your product");
        feedList.add(a);
        a = new Feed("Meera", "Sent you message");
        feedList.add(a); a = new Feed("Kylie", "Followed you");
        feedList.add(a); a = new Feed("New Order", "Sam ordered");
        feedList.add(a);

        adapter.notifyDataSetChanged();
    }
}