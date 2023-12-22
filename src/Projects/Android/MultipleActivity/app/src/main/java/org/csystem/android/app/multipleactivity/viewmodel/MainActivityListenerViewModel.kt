package org.csystem.android.app.multipleactivity.viewmodel

import org.csystem.android.app.multipleactivity.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleRegisterButtonClicked() = mWeakReference.get()?.registerButtonClicked()
    fun handleLoginButtonClicked() = mWeakReference.get()?.loginButtonClicked()
    fun handleCloseButtonClicked() = mWeakReference.get()?.closeButtonClicked()
}