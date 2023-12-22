// LoginActivityListenerViewModel
package org.csystem.android.activity.viewmodel

import org.csystem.android.activity.LoginActivity
import java.lang.ref.WeakReference

internal class LoginActivityListenerViewModel(activity:LoginActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleLoginButton() = mWeakReference.get()?.loginButtonClicked()
    fun handleCloseButton() = mWeakReference.get()?.loginButtonClicked()
}