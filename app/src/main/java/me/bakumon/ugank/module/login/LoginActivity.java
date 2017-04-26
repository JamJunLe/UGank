package me.bakumon.ugank.module.login;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import me.bakumon.ugank.R;
import me.bakumon.ugank.ThemeManage;
import me.bakumon.ugank.base.SwipeBackBaseActivity;
import me.bakumon.ugank.entity.UserInfo;
import me.bakumon.ugank.utills.DisplayUtils;

public class LoginActivity extends SwipeBackBaseActivity {
    @BindView(R.id.toolbar_setting)
    Toolbar mToolbarSetting;
    @BindView(R.id.appbar_setting)
    AppBarLayout mAppbarSetting;
    @BindView(R.id.email_sign_in_button)
    Button mBtnLogin;
    @BindView(R.id.tv_login_register)
    TextView mTvLoginRegister;
    @BindView(R.id.username)
    AutoCompleteTextView mTvUserName;
    @BindView(R.id.password)
    EditText mTvPassword;

    private boolean isLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mAppbarSetting.setPadding(
                    mAppbarSetting.getPaddingLeft(),
                    mAppbarSetting.getPaddingTop() + DisplayUtils.getStatusBarHeight(this),
                    mAppbarSetting.getPaddingRight(),
                    mAppbarSetting.getPaddingBottom());
        }
        setSupportActionBar(mToolbarSetting);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbarSetting.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mAppbarSetting.setBackgroundColor(ThemeManage.INSTANCE.getColorPrimary());
        mBtnLogin.setBackgroundColor(ThemeManage.INSTANCE.getColorPrimary());
        mTvLoginRegister.setTextColor(ThemeManage.INSTANCE.getColorPrimary());
    }

    @OnClick(R.id.tv_login_register)
    public void loginRegisterTip() {
        if (isLogin) {
            mToolbarSetting.setTitle("注册");
            mTvLoginRegister.setText("已有账号？直接登录");
            mBtnLogin.setText("注册");
        } else {
            mToolbarSetting.setTitle("登录");
            mTvLoginRegister.setText("新用户注册");
            mBtnLogin.setText("登录");
        }
        isLogin = !isLogin;
    }

    @OnClick(R.id.email_sign_in_button)
    public void loginRegister() {
        if (!checkInput()) {
            return;
        }
        if (isLogin) {
            login();
        } else {
            register();
        }
    }

    // 简单验证一下
    private boolean checkInput() {
        if (TextUtils.isEmpty(mTvUserName.getText().toString().trim())
                || TextUtils.isEmpty(mTvPassword.getText().toString().trim())) {
            Toasty.error(this, "请输入账号和密码").show();
            return false;
        }
        return true;
    }

    private void register() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(mTvUserName.getText().toString().trim());
        userInfo.setUserName(mTvPassword.getText().toString().trim());
        if (userInfo.save()) {
            isLogin = true;
        } else {
            Toasty.error(this, "注册失败，请重试。").show();
        }
    }

    private void login() {
        Toasty.error(this, "login").show();
    }


}
