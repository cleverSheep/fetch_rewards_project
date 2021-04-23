package com.deanmurray.fetchrewardsproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deanmurray.fetchrewardsproject.model.FetchReward
import com.deanmurray.fetchrewardsproject.repository.FetchRewardsRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainFragmentViewModel constructor(private val fetchRewardsRepo: FetchRewardsRepo) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val mapListIdToList = MutableLiveData<HashMap<Int, ArrayList<FetchReward>>>()

    init {
        getFetchRewards()
    }

    private fun getFetchRewards() {
        val map: HashMap<Int, ArrayList<FetchReward>> = HashMap()
        disposable.add(
            fetchRewardsRepo
                .getFetchRewardsResource()
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<ArrayList<FetchReward>>() {
                    override fun onSuccess(t: ArrayList<FetchReward>) {
                        val listRewards = t.filter { it.name != null && it.name.isNotEmpty() }
                        Log.d("MainFragmentViewModel", listRewards.toString())
                        for (reward in listRewards) {
                            if (map[reward.listId] == null) {
                                val listOfRewards = ArrayList<FetchReward>()
                                listOfRewards.add(reward)
                                map[reward.listId] = listOfRewards
                            } else {
                                val listOfRewards: ArrayList<FetchReward> = map[reward.listId]!!
                                listOfRewards.add(reward)
                                map[reward.listId] = listOfRewards
                            }
                        }
                        mapListIdToList.value = map
                    }

                    override fun onError(e: Throwable) {
                        Log.e("MainFragmentViewModel", e.localizedMessage)
                    }

                })
        )
    }

    fun getListIdToList(): LiveData<HashMap<Int, ArrayList<FetchReward>>> = mapListIdToList

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}