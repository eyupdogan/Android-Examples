package org.csystem.android.app.paymentapp.viewmodel;

import org.csystem.android.app.paymentapp.LoginActivity;

import java.lang.ref.WeakReference;

public class LoginActivityListenerViewModel {
    private final WeakReference<LoginActivity> m_weakReference;

    public LoginActivityListenerViewModel(LoginActivity activity) {
        m_weakReference = new WeakReference<>(activity);
    }

    public void handleLoginButton()
    {
        m_weakReference.get().loginButtonClicked();
    }

    public void handleCloseButton()
    {
        m_weakReference.get().finish();
    }
}
