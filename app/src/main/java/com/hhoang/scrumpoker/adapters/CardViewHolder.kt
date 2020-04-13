package com.hhoang.scrumpoker.adapters

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hhoang.scrumpoker.R

class CardViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var imageView: ImageView = view.findViewById(R.id.itemImageView)
}