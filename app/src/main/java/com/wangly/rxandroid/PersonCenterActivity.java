package com.wangly.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wangly.rxandroid.constant.Constant;
import com.wangly.rxandroid.view.PersonCenterView;

import de.hdodenhof.circleimageview.CircleImageView;


public class PersonCenterActivity extends AppCompatActivity implements PersonCenterView {

    private CircleImageView iv_userPic;
    private TextView tv_city;
    private TextView tv_nickName;
    private TextView tv_sex;
    private TextView tv_height;
    private TextView tv_weight;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        iv_userPic = (CircleImageView) findViewById(R.id.profile_image);
        tv_nickName = (TextView) findViewById(R.id.tv_nickname);

        tv_city = (TextView) findViewById(R.id.tv_city);


        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_height = (TextView) findViewById(R.id.tv_height);
        tv_weight = (TextView) findViewById(R.id.tv_weight);

        bundle = getIntent().getExtras();

        Picasso.with(this).load(Constant.picUrl).into(iv_userPic);
        tv_nickName.setText(bundle.getString("nickname"));
        tv_city.setText("城市：" + bundle.getString("city"));
        tv_sex.setText("性别：男");
        tv_height.setText("身高：" + bundle.getString("height"));
        tv_weight.setText("体重：" + bundle.getString("weight"));
    }


    @Override
    public void setNichname(String nickname) {

    }

    @Override
    public void setCity(String city) {

    }

    @Override
    public void setSex(String sex) {

    }

    @Override
    public void setHeight(String height) {

    }

    @Override
    public void setWeigth(String weigth) {

    }
}
