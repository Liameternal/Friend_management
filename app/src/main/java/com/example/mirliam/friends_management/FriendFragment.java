package com.example.mirliam.friends_management;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class FriendFragment extends Fragment {

    private static final String ARG_Friend_ID = "friend_id";
    private static final String ARG_Friend_IS_ADD = "friend_is_add";
    private String hobbys[] = {"电影", "编程", "篮球", "足球", "游泳", "羽毛球", "绘画", "写作", "美食", "游戏", "购物", "阅读", "登山", "旅行"};

    private Friend mFriend;
    private EditText mFriendName;
    private RadioButton mGenderbuttonMale;
    private RadioButton mGenderbuttonFemale;
    private EditText mBirthday;
    private List<CheckBox> mHobbys;

    int isAdd;

    private void SaveHobby() {
        StringBuilder sHobby = new StringBuilder();
        for (CheckBox c : mHobbys) {
            if (c.isChecked()) {
                sHobby = sHobby.append(c.getText()).append(" ");
            }
        }
        mFriend.setHobby(sHobby);
    }

    public static FriendFragment newInstance(UUID frienid, int isAdd) {
        Bundle args = new Bundle(0);
        args.putSerializable(ARG_Friend_ID, frienid);
        args.putInt(ARG_Friend_IS_ADD, isAdd);

        FriendFragment fragment = new FriendFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        UUID friendId = (UUID) getArguments().getSerializable(ARG_Friend_ID);
        isAdd = getArguments().getInt(ARG_Friend_IS_ADD);
        mFriend = FriendLab.get(getActivity()).getFriend(friendId);
        mHobbys = new ArrayList<>();
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_friend, container, false);

        if(isAdd == 0){
            getActivity().setTitle(R.string.name_info);
        }else if(isAdd == 1){
            getActivity().setTitle(R.string.title_modefy);
        }else{
            getActivity().setTitle(R.string.title_add);
            mFriend = new Friend();
        }


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

        RadioGroup genderRadioButton = v.findViewById(R.id.gender);
        genderRadioButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male_rd) {
                    mFriend.setGender("男");
                } else if (checkedId == R.id.female_rd) {
                    mFriend.setGender("女");
                }
            }
        });

        mGenderbuttonMale = v.findViewById(R.id.male_rd);
        mGenderbuttonFemale = v.findViewById(R.id.female_rd);


        mBirthday = v.findViewById(R.id.birthday);
        mBirthday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    getDate();
                    return true;
                }
                return false;
            }
        });

        TableLayout tableLayout = v.findViewById(R.id.Table_Layout);
        CheckBox checkBox;
        TableRow row = new TableRow(getActivity());
        TableRow.LayoutParams param1 = new TableRow.LayoutParams();
        param1.setMargins(0, 5, 0, 5);
        for (int i = 1; i <= hobbys.length; i++) {
            checkBox = new CheckBox(getActivity());
            checkBox.setText(hobbys[i - 1]);
            checkBox.setPadding(0, 8, 50, 8);
            checkBox.setTextSize(COMPLEX_UNIT_SP, 18);
            checkBox.setLayoutParams(param1);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int mCount = 0;
                    for (CheckBox c : mHobbys) {
                        if (c.isChecked()) {
                            mCount++;
                        }
                    }
                    if (mCount > 3) buttonView.setChecked(false);
                    SaveHobby();
                }
            });
            mHobbys.add(checkBox);
            row.addView(checkBox);
            if (i % 3 == 0) {
                tableLayout.addView(row);
                row = new TableRow(getActivity());
            }
        }
        tableLayout.addView(row);


        Button cancelButton = v.findViewById(R.id.cancel_bt);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).finish();
            }
        });

        Button ensureButton = v.findViewById(R.id.ensure_bt);
        ensureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendLab.get(getActivity()).addFriend(mFriend);

                Objects.requireNonNull(getActivity()).finish();
            }
        });

        LoadView();

        return v;
    }

    private void LoadView() {
        mFriendName.setText(mFriend.getName());
        if (mFriend.getGender()) {
            mGenderbuttonMale.setChecked(true);
        } else {
            mGenderbuttonFemale.setChecked(true);
        }
        String date = (String) DateFormat.format("yyyy年MM月dd日", mFriend.getBirthday());
        mBirthday.setText(date);
        LoadHobby();
    }

    private void LoadHobby() {
        StringBuilder Hobby = mFriend.getHobby();
        if (Hobby == null) return;
        String sHobby = Hobby.toString();
        for (CheckBox c : mHobbys) {
            if (sHobby.contains(c.getText().toString())) {
                c.setChecked(true);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getDate() {
        final Calendar c = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                mBirthday.setText(DateFormat.format("yyy-MM-dd", c));
                mFriend.setBirthday(c.getTime());
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

}

