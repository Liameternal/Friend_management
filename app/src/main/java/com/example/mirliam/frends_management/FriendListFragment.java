package com.example.mirliam.frends_management;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class FriendListFragment extends Fragment {
    private RecyclerView mFriendRecycleview;
    private FriendAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_list, container, false);

        mFriendRecycleview = view.findViewById(R.id.friend_recycle_view);
        mFriendRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));

        upDateUI();

        return view;
    }

    private class FriendHolder extends RecyclerView.ViewHolder {
        public FriendHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_friend, parent, false));
        }
    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {
        private List<Friend> mFriends;

        public FriendAdapter(List<Friend> friends) {
            mFriends = friends;
        }

        @NonNull
        @Override
        public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new FriendHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull FriendHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mFriends.size();
        }
    }

    private void upDateUI(){
        FriendLab friendLab = FriendLab.get(getActivity());
        List<Friend> friends = friendLab.getFriends();

        mAdapter = new FriendAdapter(friends);
        mFriendRecycleview.setAdapter(mAdapter);
    }


}
