package org.wit.placemark.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.placemark.R
import org.wit.placemark.models.PlacemarkModel


class PlacemarkActivity : AppCompatActivity(), AnkoLogger {


  val placemarks = ArrayList<PlacemarkModel>()
  var placemark = PlacemarkModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark)
    info("Placemark Activity started..")

    btnAdd.setOnClickListener() {


      placemark.title = placemarkTitle.text.toString()
      placemark.description = placemarkDescription.text.toString()
      println(placemarks)

      if (placemark.title.isNotEmpty() && placemark.description.isNotEmpty()) {
        placemarks.add(placemark.copy())
        info("add Button Pressed: $placemark.Title")
            placemarks.forEach { info("add Button Pressed: ${it.title}")}
        info( "array: $placemarks")
      }
      else {
        toast ("Please Enter a title")
      }
    }
  }
}
