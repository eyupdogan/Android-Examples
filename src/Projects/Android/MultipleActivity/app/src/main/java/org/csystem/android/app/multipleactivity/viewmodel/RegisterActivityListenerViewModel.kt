package org.csystem.android.app.multipleactivity.viewmodel

import org.csystem.android.app.multipleactivity.RegisterActivity
import java.lang.ref.WeakReference

class RegisterActivityListenerViewModel(activity: RegisterActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleRegisterButtonClicked() = mWeakReference.get()?.registerButtonClicked()
    fun handleClearButtonClicked() = mWeakReference.get()?.clearButtonClicked()
    fun handleCloseButtonClicked() = mWeakReference.get()?.closeButtonClicked()
}