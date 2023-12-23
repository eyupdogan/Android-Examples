package org.csystem.android.app.paymentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.karandev.util.data.service.DataServiceException;

import org.csystem.android.app.paymentapp.data.service.PaymentApplicationDataService;
import org.csystem.android.app.paymentapp.data.service.dto.LoginInfoDTO;
import org.csystem.android.app.paymentapp.databinding.ActivityLoginBinding;
import static org.csystem.android.app.paymentapp.global.keys.BundleKeyKt.LOGIN_INFO;
import org.csystem.android.app.paymentapp.viewmodel.LoginActivityListenerViewModel;

import java.util.concurrent.ScheduledExecutorService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding m_binding;
    private Handler m_handler;

    @Inject
    PaymentApplicationDataService dataService;

    @Inject
    ScheduledExecutorService threadPool;

    private void initBinding()
    {
        m_binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        m_binding.setViewModel(new LoginActivityListenerViewModel(this));
        m_binding.setLoginInfo(new LoginInfoDTO());
    }
    private void init()
    {
        initBinding();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void saveLoginInfoUIThreadCallback(LoginInfoDTO loginInfo) {
        Toast.makeText(this, "Access granted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, OperationsActivity.class).putExtra(LOGIN_INFO, loginInfo));
    }
    public void loginButtonClickedCallback()
    {
        try {
            var loginInfo = m_binding.getLoginInfo();
            if (dataService.checkAndSaveLoginInfo(loginInfo))
                runOnUiThread(()->saveLoginInfoUIThreadCallback(loginInfo));
            else
                runOnUiThread(()-> Toast.makeText(this, "Access denied!...", Toast.LENGTH_SHORT).show());
        }catch (DataServiceException ex){
            runOnUiThread(()->Toast.makeText(this, "Data problem" + ex.getMessage(), Toast.LENGTH_SHORT).show());
        }catch (Throwable ignore){
            runOnUiThread(()->Toast.makeText(this, "Problem occurred Try again later", Toast.LENGTH_SHORT).show());
        }
    }



    public void loginButtonClicked()
    {
        threadPool.execute(this::loginButtonClickedCallback);
    }
}