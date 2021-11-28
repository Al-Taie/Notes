package com.altaie.notes.utils

import android.annotation.SuppressLint
import com.altaie.notes.ui.base.BaseAdapter
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.altaie.notes.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView?, items: List<T>?) {
    (view?.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:tags"])
fun setTags(view: ChipGroup, tags: List<String>?) {
    tags?.forEach { tag ->
        Chip(view.context).apply {
            text = tag
            setPadding(8, 8, 8, 8)
//            setTextAppearanceResource(R.style.ChipTextStyle_Selected)
            setChipStrokeColorResource(R.color.purple_200)
            chipStrokeWidth = 1f
//            setChipBackgroundColorResource(R.color.background_color)
            view.addView(this)
        }
    }
}

@BindingAdapter(value = ["app:cardColor"])
fun setCardColor(view: MaterialCardView, color: Int?) {
    color?.let { view.setBackgroundColor(it) }
}


@SuppressLint("SimpleDateFormat")
@BindingAdapter(value = ["app:date"])
fun setDate(view: TextView, date: Date?) {
    date?.let { view.text = SimpleDateFormat("yyyy-MM-dd").format(date) }
}