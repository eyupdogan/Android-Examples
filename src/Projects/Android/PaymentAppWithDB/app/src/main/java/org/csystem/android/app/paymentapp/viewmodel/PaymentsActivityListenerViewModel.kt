package org.csystem.android.app.paymentapp.viewmodel

import org.csystem.android.app.paymentapp.PaymentsActivity
import java.lang.ref.WeakReference

class PaymentsActivityListenerViewModel(activity: PaymentsActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleListPaymentsButton() = mWeakReference.get()?.listPaymentsButtonClicked()

    fun handleCloseButton() = mWeakReference.get()?.closeButtonClicked()

}