package org.csystem.android.app.generator.random.viewmodel.listeners

import org.csystem.android.app.generator.random.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleStartServiceButton() = mWeakReference.get()?.startServiceButtonClicked()

    fun handleCreateNewServiceSwitchButton(isChecked:Boolean) = mWeakReference.get()?.createNewServiceSwitchButtonClicked(isChecked)
}