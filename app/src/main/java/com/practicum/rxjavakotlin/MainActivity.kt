package com.practicum.rxjavakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscription

class MainActivity : AppCompatActivity() {
    val TAG = "LOG_TAG"
    val list = listOf("one", "two", "three")
    val observer = Subscriber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Flowable.fromArray(list)
            .onBackpressureBuffer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)


        Single.just("Another Text")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<String>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

                override fun onSuccess(t: String) {
                    Log.e(TAG,"Single is working, data is: $t")
                }

            })

        getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object: Observer<String>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

                override fun onNext(t: String) {
                    Log.e(TAG,"Observable is working, next data text is: $t")
                }
            }
            )
    }

    fun getData(): Observable<String>{
        return Observable.create{subscriber->
            list.forEach {
                subscriber.onNext(it)
            }
        }
    }
}