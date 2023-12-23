package org.csystem.android.app.paymentapp.viewmodel

import org.csystem.android.app.paymentapp.PaymentActivity
import java.lang.ref.WeakReference

class PaymentActivityListenerViewModel(activity: PaymentActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleMakePaymentButton()
    {
        mWeakReference.get()?.makePaymentButtonClicked()
    }

    fun handlePaymentsButton()
    {
        mWeakReference.get()?.paymentsButtonClicked()
    }

    fun handleCloseButton()
    {
        mWeakReference.get()?.closeButtonClicked()
    }
}