package org.wit.placemark.adapters;

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_placemark.*
import kotlinx.android.synthetic.main.card_placemark.view.*
import org.jetbrains.anko.image
import org.wit.placemark.R
import org.wit.placemark.R.id.placemarkImage
import org.wit.placemark.helpers.readImageFromPath
import org.wit.placemark.models.PlacemarkModel

//represent click events on the placemark Card, and allow us to abstract the response to this event.
interface PlacemarkListener {
    fun onPlacemarkClick(placemark: PlacemarkModel)
}


class PlacemarkAdapter constructor(private var placemarks: List<PlacemarkModel>,
                                   //using above interface
                                   private val listener: PlacemarkListener) : RecyclerView.Adapter<PlacemarkAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_placemark, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val placemark = placemarks[holder.adapterPosition]
        holder.bind(placemark, listener)
    }

    override fun getItemCount(): Int = placemarks.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(placemark: PlacemarkModel,  listener : PlacemarkListener) {
            itemView.placemarkTitle.text = placemark.title
            itemView.description.text = placemark.description
            //adding an image to view
            itemView.placemarkImage.setImageBitmap(readImageFromPath(itemView.context, placemark.image))
            //using the about interface
            itemView.setOnClickListener { listener.onPlacemarkClick(placemark) }
        }
    }
}