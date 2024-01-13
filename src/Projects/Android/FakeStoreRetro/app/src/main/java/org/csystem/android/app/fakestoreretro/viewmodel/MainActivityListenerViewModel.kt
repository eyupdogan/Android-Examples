package org.csystem.android.app.fakestoreretro.viewmodel

import org.csystem.android.app.fakestoreretro.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)
    fun handleGetProductsButtonClicked() = mWeakReference.get()?.getProductsButtonClicked()

    fun handleProductListItemClicked(pos:Int)
    {

    }
}