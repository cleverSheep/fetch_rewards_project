package com.deanmurray.fetchrewardsproject.network

import com.deanmurray.fetchrewardsproject.model.FetchReward
import io.reactivex.Single
import retrofit2.http.GET

interface FetchRewardsService {
    @GET("hiring.json")
    fun getFetchRewardsResource(): Single<ArrayList<FetchReward>>
}

