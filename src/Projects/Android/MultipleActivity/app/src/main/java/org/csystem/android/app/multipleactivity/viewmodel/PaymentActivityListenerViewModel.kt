package org.csystem.android.app.multipleactivity.viewmodel

import org.csystem.android.app.multipleactivity.PaymentActivity
import java.lang.ref.WeakReference

class PaymentActivityListenerViewModel(activity: PaymentActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handlePayButtonClicked() = mWeakReference.get()?.payButtonClicked()
    fun handleClearButtonClicked() = mWeakReference.get()?.clearButtonClicked()
    fun handleCloseButtonClicked() = mWeakReference.get()?.closeButtonClicked()
    fun handleExitButtonClicked() = mWeakReference.get()?.exitButtonClicked()
}