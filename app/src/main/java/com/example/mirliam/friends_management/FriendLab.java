package com.example.mirliam.friends_management;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FriendLab {
    private static FriendLab sFriendLab;
    private List<Friend> mFriends;

    public static FriendLab get(Context context) {
        if (sFriendLab == null) {
            sFriendLab = new FriendLab(context);
        }
        return sFriendLab;
    }

    private FriendLab(Context context) {
        mFriends = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            Friend friend = new Friend();
//            friend.setName("老王 #" + i);
//            friend.setGender("男");
//            friend.getHobby().add("电影");
//            friend.getHobby().add("编程");
//            friend.getHobby().add("篮球");
//            mFriends.add(friend);
//        }
    }

    public void addFriend(Friend friend){
        mFriends.add(friend);
    }

    public List<Friend> getFriends() {
        return mFriends;
    }

    public Friend getFriend(UUID id) {
        for (Friend friend : mFriends) {
            if (friend.getId().equals(id)) {
                return friend;
            }
        }

        return null;
    }

}
