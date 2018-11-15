package com.example.mirliam.frends_management;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class FriendFragment extends Fragment {
    private Friend mFriend;
    private ImageButton mLastImageButton;
    private EditText mFriendName;
    private RadioGroup mGenderRadioButton;
    private TextView mBirthday;
    private Button mChoseBirthday;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFriend = new Friend();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_friends, container, false);

        mLastImageButton = v.findViewById(R.id.last_page);
        mLastImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(getActivity(), "It is doesn't work now!", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.BOTTOM, 0, 0);
                t.show();
            }
        });

        mFriendName = v.findViewById(R.id.friend_name);
        mFriendName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFriend.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mGenderRadioButton = v.findViewById(R.id.gender);
        mGenderRadioButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male_rd) {
                    Toast.makeText(getActivity(), "male!", Toast.LENGTH_SHORT).show();
                    mFriend.setGender("男");
                } else if(checkedId == R.id.female_rd) {
                    Toast.makeText(getActivity(), "female!", Toast.LENGTH_SHORT).show();
                    mFriend.setGender("女");
                }
            }
        });

        mBirthday = v.findViewById(R.id.birthday);
        mChoseBirthday = v.findViewById(R.id.chose_birthday);
        mChoseBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return v;
    }
}
