package com.deanmurray.fetchrewardsproject.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.deanmurray.fetchrewardsproject.model.FetchReward
import com.deanmurray.fetchrewardsproject.repository.FetchRewardsRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainFragmentViewModel constructor(private val fetchRewardsRepo: FetchRewardsRepo) : ViewModel() {
    private val disposable = CompositeDisposable()

    fun getFetchRewards() {
        disposable.add(
            fetchRewardsRepo.getFetchRewardsResource().subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<List<FetchReward>>() {
                    override fun onSuccess(t: List<FetchReward>) {
                        for (reward in t)
                            Log.d("MainFragmentViewModel", reward.id.toString())
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}