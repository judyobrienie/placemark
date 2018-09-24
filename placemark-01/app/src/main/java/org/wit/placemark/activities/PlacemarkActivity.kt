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

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

  var placemark = PlacemarkModel()
  lateinit var app : MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark)
    //set new menu
    setSupportActionBar(toolbarAdd)
    app = application as MainApp




    btnAdd.setOnClickListener() {

      placemark.title = placemarkTitle.text.toString()
      placemark.description = placemarkDescription.text.toString()

      if (placemark.title.isNotEmpty()) {
        app.placemarks.add(placemark.copy())
        info("add Button Pressed: $placemarkTitle")
        app.placemarks.forEach { info("add Button Pressed: ${it}")}
        setResult(AppCompatActivity.RESULT_OK)
        finish()
      }

      else {
        toast ("Please Enter a title")
      }
    }

  }


  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_placemarkactivity, menu)
    return super.onCreateOptionsMenu(menu)
  }

  //when the item is pressed ie cancel - finish
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> finish()
    }
    return super.onOptionsItemSelected(item)
  }

}