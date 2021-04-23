package com.deanmurray.fetchrewardsproject.view.list

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.deanmurray.fetchrewardsproject.model.FetchReward
import com.deanmurray.fetchrewardsproject.view.custom.CarouselHeaderViewModel_
import com.deanmurray.fetchrewardsproject.view.custom.CarouselItemView_
import com.deanmurray.fetchrewardsproject.view.custom.CarouselNoSnapModel_
import java.util.ArrayList

class ListController : TypedEpoxyController<HashMap<Int, ArrayList<FetchReward>>>() {
    override fun buildModels(listAtId: HashMap<Int, ArrayList<FetchReward>>) {
        // Create list 1
        listAtId[1]?.let { createDataCarousel("1", it) }
        // Create list 2
        listAtId[2]?.let { createDataCarousel("2", it) }
        // Create list 3
        listAtId[3]?.let { createDataCarousel("3", it) }
        // Create list 4
        listAtId[4]?.let { createDataCarousel("4", it) }
    }

    private fun createDataCarousel(listId: String, listRewards: ArrayList<FetchReward>) {
        val models = ArrayList<CarouselItemView_>()
        for (item in listRewards) {
            Log.d("ListController", "${item.name}")
            models.add(
                CarouselItemView_()
                    .id("item:" + item.id)
                    .item_id(item.id.toString())
                    .item_name(item.name)
            )
        }
        CarouselHeaderViewModel_()
            .id("header:${listId}")
            .title("List: ${listId}")
            .addTo(this)
        CarouselNoSnapModel_()
            .id("header:${listId}")
            .models(models)
            .addTo(this)
    }

}