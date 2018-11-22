package com.example.mirliam.friends_management;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class FriendActivity extends SingleFragmentActivity {

    private static final String EXTRA_FRIEND_ID = "com.example.mirliam.friends_management.friend_id";
    private static final String EXTRA_FRIEND_IS_ADD = "com.example.mirliam.friends_management.friend_is_add";  //0:好友信息  1:修改  2:添加好友

    public static Intent newIntent(Context packageContext, UUID friendId, int isAdd) {
        Intent intent = new Intent(packageContext, FriendActivity.class);
        intent.putExtra(EXTRA_FRIEND_ID, friendId);
        intent.putExtra(EXTRA_FRIEND_IS_ADD, isAdd);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID friendId = (UUID) getIntent().getSerializableExtra(EXTRA_FRIEND_ID);
        int isAdd = getIntent().getIntExtra(EXTRA_FRIEND_IS_ADD,-1);

        return FriendFragment.newInstance(friendId,isAdd);
    }
}
