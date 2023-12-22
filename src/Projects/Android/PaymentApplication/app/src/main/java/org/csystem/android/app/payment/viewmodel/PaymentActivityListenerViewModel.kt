package org.csystem.android.app.payment.viewmodel

import org.csystem.android.app.payment.PaymentActivity
import java.lang.ref.WeakReference

class PaymentActivityListenerViewModel(activity: PaymentActivity)
{
    private val mWeakreference = WeakReference(activity)

    fun handleMakePaymentButton()
    {
        mWeakreference.get()?.makePaymentButtonClicked()
    }

    fun handlePaymentsButton()
    {
        mWeakreference.get()?.paymentsButtonClicked()
    }

    fun handleCloseButton()
    {
        mWeakreference.get()?.closeButtonClicked()
    }
}