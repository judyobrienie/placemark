package org.wit.placemark.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_placemark.*
import kotlinx.android.synthetic.main.activity_placemark_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.placemark.R


import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.models.getId

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

  var placemark = PlacemarkModel()
  lateinit var app : MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark)
    //set new toobar
    toolbarAdd.title = title
    setSupportActionBar(toolbarAdd)
    app = application as MainApp

    if (intent.hasExtra("placemark_edit")) {
      placemark = intent.extras.getParcelable<PlacemarkModel>("placemark_edit")
      placemarkTitle.setText(placemark.title)
      placemarkDescription.setText(placemark.description)
    }


    btnAdd.setOnClickListener() {

      placemark.title = placemarkTitle.text.toString()
      placemark.description = placemarkDescription.text.toString()

      if (placemark.title.isNotEmpty()) {

        //app.placemarks.add(placemark.copy())
        app.placemarks.create(placemark.copy())

        info("add Button Pressed: $placemarkTitle")
       // app.placemarks.forEach { info("add Button Pressed: ${it}")}
        ////////removing logging as it was moved to PlarkmarkMemStore
       // app.placemarks.findAll().forEach{ info("add Button Pressed: ${it}") }

        //set result of activity
        setResult(AppCompatActivity.RESULT_OK)
        //finish
        finish()
      }

      else {
         toast( this.getString(R.string.hint_enterATitle));
      }
    }

  }

  //inflate the menu
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_placemarkactivity, menu)
    return super.onCreateOptionsMenu(menu)
  }

  //when the item on the menu is pressed - handling the event
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      //when cancel is pressed finish
      R.id.item_cancel ->
        finish()
    }
    return super.onOptionsItemSelected(item)
  }



}