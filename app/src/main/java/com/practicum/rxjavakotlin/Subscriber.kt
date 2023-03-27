package com.practicum.rxjavakotlin

import android.util.Log
import io.reactivex.rxjava3.core.FlowableSubscriber
import org.reactivestreams.Subscription

class Subscriber: FlowableSubscriber<List<String>>{
    val TAG = "LOG_TAG"

    override fun onSubscribe(s: Subscription) {
        Log.e(TAG,"Flowable onSubscribe")
    }

    override fun onError(t: Throwable?) {

    }

    override fun onComplete() {

    }

    override fun onNext(t: List<String>?) {
        t?.forEach {
            Log.e(TAG,"Flowable is working, next data String is: $it")
        }
    }

}