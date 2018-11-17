package com.example.mirliam.frends_management;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class FriendFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private Friend mFriend;
    private EditText mFriendName;
    private RadioGroup mGenderRadioButton;
    private TextView mBirthday;
    private Button mChoseBirthday;
    private CheckBox mHobbyCheckBox[] = new CheckBox[14];
    private int mNumbersOfHobby = 0;  //不超过三个
    private Button mCancelButton;
    private Button mEnsureButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFriend = new Friend();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_friend, container, false);

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
                } else if (checkedId == R.id.female_rd) {
                    Toast.makeText(getActivity(), "female!", Toast.LENGTH_SHORT).show();
                    mFriend.setGender("女");
                }
            }
        });

        mBirthday = v.findViewById(R.id.birthday);
        mChoseBirthday = v.findViewById(R.id.chose_birthday);
        mChoseBirthday.setOnClickListener(new View.OnClickListener() {
            final Calendar c = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        mBirthday.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        mHobbyCheckBox[0] = v.findViewById(R.id.checkBox_hobby_of_1);
        mHobbyCheckBox[0].setOnCheckedChangeListener(this);

        mHobbyCheckBox[1] = v.findViewById(R.id.checkBox_hobby_of_2);
        mHobbyCheckBox[1].setOnCheckedChangeListener(this);

        mHobbyCheckBox[2] = v.findViewById(R.id.checkBox_hobby_of_3);
        mHobbyCheckBox[2].setOnCheckedChangeListener(this);

        mHobbyCheckBox[3] = v.findViewById(R.id.checkBox_hobby_of_4);
        mHobbyCheckBox[3].setOnCheckedChangeListener(this);

        mHobbyCheckBox[4] = v.findViewById(R.id.checkBox_hobby_of_5);
        mHobbyCheckBox[4].setOnCheckedChangeListener(this);

        mHobbyCheckBox[5] = v.findViewById(R.id.checkBox_hobby_of_6);
        mHobbyCheckBox[5].setOnCheckedChangeListener(this);

        mHobbyCheckBox[6] = v.findViewById(R.id.checkBox_hobby_of_7);
        mHobbyCheckBox[6].setOnCheckedChangeListener(this);

        mHobbyCheckBox[7] = v.findViewById(R.id.checkBox_hobby_of_8);
        mHobbyCheckBox[7].setOnCheckedChangeListener(this);

        mHobbyCheckBox[8] = v.findViewById(R.id.checkBox_hobby_of_9);
        mHobbyCheckBox[8].setOnCheckedChangeListener(this);

        mHobbyCheckBox[9] = v.findViewById(R.id.checkBox_hobby_of_10);
        mHobbyCheckBox[9].setOnCheckedChangeListener(this);

        mHobbyCheckBox[10] = v.findViewById(R.id.checkBox_hobby_of_11);
        mHobbyCheckBox[10].setOnCheckedChangeListener(this);

        mHobbyCheckBox[11] = v.findViewById(R.id.checkBox_hobby_of_12);
        mHobbyCheckBox[11].setOnCheckedChangeListener(this);

        mHobbyCheckBox[12] = v.findViewById(R.id.checkBox_hobby_of_13);
        mHobbyCheckBox[12].setOnCheckedChangeListener(this);

        mHobbyCheckBox[13] = v.findViewById(R.id.checkBox_hobby_of_14);
        mHobbyCheckBox[13].setOnCheckedChangeListener(this);


        mCancelButton = v.findViewById(R.id.cancel_bt);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.friend_add_cancel, Toast.LENGTH_SHORT).show();
            }
        });

        mEnsureButton = v.findViewById(R.id.ensure_bt);
        mEnsureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "mNumberOfHobby:  " + mNumbersOfHobby, Toast.LENGTH_SHORT).show();
//                for (String hobby : mFriend.getHobby()) {
//                    Toast.makeText(getActivity(), hobby, Toast.LENGTH_SHORT).show();
//                }
                if (mNumbersOfHobby > 3) {
                    Toast.makeText(getActivity(), R.string.error_tip_1, Toast.LENGTH_SHORT).show();
                } else if(mNumbersOfHobby < 1){
                    Toast.makeText(getActivity(), R.string.error_tip_2, Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getActivity(), R.string.friend_add_done, Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkBox_hobby_of_1:
                if (isChecked) {
                    mFriend.getHobby().add("电影");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("电影");
                }
                break;
            case R.id.checkBox_hobby_of_2:
                if (isChecked) {
                    mFriend.getHobby().add("编程");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("编程");
                }
                break;
            case R.id.checkBox_hobby_of_3:
                if (isChecked) {
                    mFriend.getHobby().add("篮球");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("篮球");
                }
                break;
            case R.id.checkBox_hobby_of_4:
                if (isChecked) {
                    mFriend.getHobby().add("足球");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("足球");
                }
                break;
            case R.id.checkBox_hobby_of_5:
                if (isChecked) {
                    mFriend.getHobby().add("游泳");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("游泳");
                }
                break;
            case R.id.checkBox_hobby_of_6:
                if (isChecked) {
                    mFriend.getHobby().add("羽毛球");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("羽毛球");
                }
                break;
            case R.id.checkBox_hobby_of_7:
                if (isChecked) {
                    mFriend.getHobby().add("绘画");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("绘画");
                }
                break;
            case R.id.checkBox_hobby_of_8:
                if (isChecked) {
                    mFriend.getHobby().add("写作");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("写作");
                }
                break;
            case R.id.checkBox_hobby_of_9:
                if (isChecked) {
                    mFriend.getHobby().add("美食");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("美食");
                }
                break;
            case R.id.checkBox_hobby_of_10:
                if (isChecked) {
                    mFriend.getHobby().add("游戏");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("游戏");
                }
                break;
            case R.id.checkBox_hobby_of_11:
                if (isChecked) {
                    mFriend.getHobby().add("购物");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("购物");
                }
                break;
            case R.id.checkBox_hobby_of_12:
                if (isChecked) {
                    mFriend.getHobby().add("阅读");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("阅读");
                }
                break;
            case R.id.checkBox_hobby_of_13:
                if (isChecked) {
                    mFriend.getHobby().add("登山");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("登山");
                }
                break;
            case R.id.checkBox_hobby_of_14:
                if (isChecked) {
                    mFriend.getHobby().add("旅行");
                    mNumbersOfHobby++;
                } else {
                    mNumbersOfHobby--;
                    mFriend.getHobby().remove("旅行");
                }
                break;
        }
    }

}
