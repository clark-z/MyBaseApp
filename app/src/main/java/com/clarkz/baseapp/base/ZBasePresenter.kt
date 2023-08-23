package com.clarkz.baseapp.base

import android.content.Intent
import android.os.Bundle
import java.lang.ref.SoftReference

abstract class ZBasePresenter <T : IZBaseView>(v: T) {
    open var mView: SoftReference<T> = SoftReference(v)

    open fun onCreate(intent: Intent?) {}

    open fun onCreateView(arguments: Bundle?) {}

    open fun onStart() {}

    open fun onResume() {}

    open fun onPause() {}

    open fun onStop() {}

    open fun onDestroy() {
        mView.get()?.unSubscribe()
    }
}