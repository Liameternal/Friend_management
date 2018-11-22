package com.example.mirliam.friends_management;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FriendListFragment extends Fragment {

    private final static int[] dayArr = new int[]{20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
    private final static String[] constellationArr = new String[]{"摩羯座", "水瓶座", "双鱼座", "白羊座",
            "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};

    private RecyclerView mFriendRecycleview;
    private FriendAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_list, container, false);

        mFriendRecycleview = view.findViewById(R.id.friend_recycle_view);
        mFriendRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));

        upDateUI();

        return view;
    }

    private class FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Friend mFriend;

        private TextView mFriendName;
        private TextView mFriendGender;
        private TextView mFriendAge;
        private TextView mFriendConstellation;
        private TextView[] mFriendHobby = new TextView[3];

        public FriendHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_friend, parent, false));
            itemView.setOnClickListener(this);

            mFriendName = itemView.findViewById(R.id.show_friend_name);
            mFriendGender = itemView.findViewById(R.id.show_friend_gender);
            mFriendAge = itemView.findViewById(R.id.show_friend_age);
            mFriendConstellation = itemView.findViewById(R.id.show_friend_constellattion);
            mFriendHobby[0] = itemView.findViewById(R.id.show_friend_hobby_1);
            mFriendHobby[1] = itemView.findViewById(R.id.show_friend_hobby_2);
            mFriendHobby[2] = itemView.findViewById(R.id.show_friend_hobby_3);

            registerForContextMenu(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bind(Friend friend) {
            int mYear, mAge;
            String Sex, Constellation;

            mFriend = friend;
            mFriendName.setText(mFriend.getName());
            if (mFriend.getGender())
                mFriendGender.setText(R.string.friend_gender_male);
            else
                mFriendGender.setText(R.string.friend_gender_female);

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            mYear = c.get(Calendar.YEAR);
            c.setTime(mFriend.getBirthday());
            mAge = mYear - c.get(Calendar.YEAR);
            Constellation = getConstellation(c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));

            mFriendAge.setText(String.valueOf(mAge));
            mFriendConstellation.setText(Constellation);


            mFriendHobby[0].setText(mFriend.getHobby().toString().split(" ")[0]);
            mFriendHobby[1].setText(mFriend.getHobby().toString().split(" ")[1]);
            mFriendHobby[2].setText(mFriend.getHobby().toString().split(" ")[2]);

        }

        @Override
        public void onClick(View v) {
            Intent intent = FriendActivity.newIntent(getActivity(), mFriend.getId(),0);
            startActivity(intent);
        }
    }


    private class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {
        private List<Friend> mFriends;
        private int mPosition;

        public FriendAdapter(List<Friend> friends) {
            mFriends = friends;
        }

        @NonNull
        @Override
        public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new FriendHolder(layoutInflater, parent);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onBindViewHolder(@NonNull final FriendHolder holder, int position) {
            Friend friend = mFriends.get(position);
            holder.bind(friend);
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mPosition = holder.getAdapterPosition();
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mFriends.size();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,1,0,"修改");
        menu.add(0,2,0,"删除");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 1:
                Intent intent = FriendActivity.newIntent(getActivity(), FriendLab.get(getActivity()).getFriends().get(mAdapter.mPosition).getId(),1);
                startActivity(intent);
                break;
            case 2:
                FriendLab.get(getActivity()).getFriends().remove(mAdapter.mPosition);
                mAdapter.notifyItemRemoved(mAdapter.mPosition);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        upDateUI();
    }

    private void upDateUI() {
        FriendLab friendLab = FriendLab.get(getActivity());
        List<Friend> friends = friendLab.getFriends();

        if (mAdapter == null) {
            mAdapter = new FriendAdapter(friends);
            mFriendRecycleview.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }


    //menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_friend_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_friend:
//                Friend friend = new Friend();
//                FriendLab.get(getActivity()).addFriend(friend);
//                Intent intent = FriendActivity.newIntent(getActivity(),friend.getId());
                Intent intent = new Intent(getActivity(), FriendActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //return constellation
    public String getConstellation(int month, int day) {

        return day < dayArr[month - 1] ? constellationArr[month - 1] : constellationArr[month];
    }

}
