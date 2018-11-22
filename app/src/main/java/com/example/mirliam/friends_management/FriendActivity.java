package com.example.mirliam.friends_management;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class FriendActivity extends SingleFragmentActivity {

    private static final String EXTRA_FRIEND_ID = "com.example.mirliam.friends_management.friend_id";

    public static Intent newIntent(Context packageContext, UUID friendId) {
        Intent intent = new Intent(packageContext, FriendActivity.class);
        intent.putExtra(EXTRA_FRIEND_ID, friendId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID friendId = (UUID) getIntent().getSerializableExtra(EXTRA_FRIEND_ID);

        return FriendFragment.newInstance(friendId);
    }
}
