package com.example.mirliam.frends_management;

import android.support.v4.app.Fragment;

public class FriendListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new FriendListFragment();
    }
}
