package com.deanmurray.fetchrewardsproject.repository

import com.deanmurray.fetchrewardsproject.model.FetchReward
import com.deanmurray.fetchrewardsproject.network.FetchRewardsService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// Provides an abstraction over the location of the data
class FetchRewardsRepo @Inject constructor(private val fetchRewardsService: FetchRewardsService) {
    fun getFetchRewardsResource(): Single<List<FetchReward>> {
        return fetchRewardsService.getFetchRewardsResource()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}