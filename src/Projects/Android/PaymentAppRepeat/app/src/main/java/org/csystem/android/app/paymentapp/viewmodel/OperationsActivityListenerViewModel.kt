package org.csystem.android.app.paymentapp.viewmodel

import org.csystem.android.app.paymentapp.OperationsActivity
import java.lang.ref.WeakReference

class OperationsActivityListenerViewModel(activity: OperationsActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handlePaymentButton() = mWeakReference.get()?.paymentButtonClicked()

    fun handleLoginInformationButton() = mWeakReference.get()?.loginInformationButtonClicked()

    fun handleCloseButton() = mWeakReference.get()?.closeButtonClicked()
}