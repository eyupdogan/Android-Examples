package org.csystem.android.app.paymentapp.viewmodel

import org.csystem.android.app.paymentapp.MakePaymentActivity
import java.lang.ref.WeakReference

class MakePaymentActivityListenerViewModel(activity: MakePaymentActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handlePayButton() = mWeakReference.get()?.payButtonClicked()

    fun handleClearButton() = mWeakReference.get()?.clearButtonClicked()

    fun handleCloseButton() = mWeakReference.get()?.closeButtonClicked()
}