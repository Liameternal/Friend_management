package com.example.mirliam.frends_management;

import android.support.v4.app.Fragment;

public class FriendActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new FriendFragment();
    }
}
