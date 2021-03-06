package org.wit.placemark.activities

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.support.v7.app.AppCompatActivity
import android.text.Layout
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_placemark.*
import kotlinx.android.synthetic.main.activity_placemark_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.placemark.R
import org.wit.placemark.helpers.readImage
import org.wit.placemark.helpers.readImageFromPath
import org.wit.placemark.helpers.showImagePicker
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel


class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

  var placemark = PlacemarkModel()
  lateinit var app : MainApp
  val IMAGE_REQUEST = 1



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark)
    //set new toobar
    toolbarAdd.title = title
    setSupportActionBar(toolbarAdd)
    app = application as MainApp

    var edit = false;
    if (intent.hasExtra("placemark_edit")) {
      placemark = intent.extras.getParcelable<PlacemarkModel>("placemark_edit")
      placemarkTitle.setText(placemark.title)
      placemarkDescription.setText(placemark.description)
      btnAdd.setText(R.string.button_savePlacemark)
      chooseImage.setText(R.string.button_saveImage)
      //adding image to edit mode
      placemarkImage.setImageBitmap(readImageFromPath(this, placemark.image))
      edit = true;
    }

    chooseImage.setOnClickListener {
      showImagePicker(this, IMAGE_REQUEST)
    }

    btnAdd.setOnClickListener() {
      placemark.title = placemarkTitle.text.toString()
      placemark.description = placemarkDescription.text.toString()

      if (placemark.title.isNotEmpty()) {
        if (edit){
          btnAdd.setText(R.string.button_savePlacemark)
          app.placemarks.update(placemark.copy())
        }
        else{
          app.placemarks.create(placemark.copy())
        }
        //set result of activity
        setResult(AppCompatActivity.RESULT_OK)
      //if button pressed again then finish
        finish()
      }
      else {
        toast (R.string.hint_enterATitle)
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

  //recover the image name when the activity picker activity finishes
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    when (requestCode) {
      IMAGE_REQUEST -> {
        if (data != null) {
          placemark.image = data.getData().toString()
          placemarkImage.setImageBitmap(readImage(this, resultCode, data))
        }
      }
    }
  }


}