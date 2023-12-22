package org.csystem.android.app.payment.viewmodel

import org.csystem.android.app.payment.PaymentsActivity
import java.lang.ref.WeakReference

class PaymentsActivityListenerViewModel(activity: PaymentsActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleMakePaymentButton()
    {
        mWeakReference.get()?.listPaymentsButtonClicked()
    }


}