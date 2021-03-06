package org.wit.placemark.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_placemark.*
import kotlinx.android.synthetic.main.activity_placemark_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.placemark.R
import org.wit.placemark.R.id.placemarkImage
import org.wit.placemark.adapters.PlacemarkAdapter
import org.wit.placemark.adapters.PlacemarkListener
import org.wit.placemark.helpers.readImage
import org.wit.placemark.helpers.readImageFromPath
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel


class PlacemarkListActivity : AppCompatActivity(), PlacemarkListener {

  lateinit var app: MainApp




  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark_list)
    //set up menu bar
    toolbarMain.title = title
    setSupportActionBar(toolbarMain)
    app = application as MainApp

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager




    /*
    recyclerView.adapter = PlacemarkAdapter(app.placemarks)
    using encapsulated from models - PlacemarkMemStore          /// the 'this' add Placemarkistener
    */
    recyclerView.adapter = PlacemarkAdapter(app.placemarks.findAll(),this)

  }
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> startActivityForResult<PlacemarkActivity>(0)
    }
    return super.onOptionsItemSelected(item)

  }

  /*implementing the new placemarkListener interface
  override fun onPlacemarkClick(placemark: PlacemarkModel) {
    startActivityForResult(intentFor<PlacemarkActivity>(), 0)
  }*/
  override fun onPlacemarkClick(placemark: PlacemarkModel) {
    startActivityForResult(intentFor<PlacemarkActivity>().putExtra("placemark_edit", placemark), 0)
  }

  //refreshing the view after an update
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    recyclerView.adapter?.notifyDataSetChanged()
    super.onActivityResult(requestCode, resultCode, data)
  }




}

