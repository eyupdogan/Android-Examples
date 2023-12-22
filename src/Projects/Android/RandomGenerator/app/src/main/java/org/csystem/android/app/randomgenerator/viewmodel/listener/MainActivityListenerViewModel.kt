package org.csystem.android.app.randomgenerator.viewmodel.listener

import org.csystem.android.app.randomgenerator.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleGenerateButton() = mWeakReference.get()?.generateButtonClicked()
    fun handleSaveButton() = mWeakReference.get()?.saveButtonClicked()
    fun handleClearButton() = mWeakReference.get()?.clearButtonClicked()

}