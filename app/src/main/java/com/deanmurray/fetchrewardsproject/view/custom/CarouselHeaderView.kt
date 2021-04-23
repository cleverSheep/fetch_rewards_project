package com.deanmurray.fetchrewardsproject.view.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.deanmurray.fetchrewardsproject.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CarouselHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val textView: TextView

    init {
        inflate(context, R.layout.carousel_header, this)
        orientation = VERTICAL
        textView = (findViewById(R.id.carousel_header))
    }

    @TextProp
    lateinit var title: CharSequence

    @AfterPropsSet
    fun useProps() {
        textView.text = title
    }
}