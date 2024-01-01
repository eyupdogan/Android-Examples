package org.csystem.android.app.fakestore.viewmodel.listeners

import org.csystem.android.app.fakestore.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleProductListItemClicked(pos:Int)
    {

    }

    fun handleGetAllProductsButton()
    {
        mWeakReference.get()?.getAllProductsButtonClicked()
    }
}