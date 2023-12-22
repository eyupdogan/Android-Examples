package org.csystem.android.app.payment.viewmodel

import org.csystem.android.app.payment.MakePaymentActivity
import java.lang.ref.WeakReference

class MakePaymentActivityListenerViewModel(activity: MakePaymentActivity)
{
    private val mWeakreference = WeakReference(activity)

    fun handlePayButton()
    {
        mWeakreference.get()?.payButtonClicked()
    }

    fun handleClearButton()
    {
        mWeakreference.get()?.clearButtonClicked()
    }

    fun handleCloseButton()
    {
        mWeakreference.get()?.closeButtonClicked()
    }
}