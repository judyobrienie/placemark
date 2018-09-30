package org.wit.placemark.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

//adding id to identify placemark
var lastId = 0L

internal fun getId(): Long {
  return lastId++
}


// when logging you must use Ankologger in the declaration
class PlacemarkMemStore : PlacemarkStore, AnkoLogger {

  // encapsulation of list of placemarks
  val placemarks = ArrayList<PlacemarkModel>()

  override fun findAll(): List<PlacemarkModel> {
    return placemarks
  }


  //log all placemarks. Call it whenever a new placemark is added
  override fun create(placemark: PlacemarkModel) {
    placemark.id = getId()
    placemarks.add(placemark)
    logAll()
  }

  //updating placemark by id
  override fun update(placemark: PlacemarkModel) {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == placemark.id }
    if (foundPlacemark != null) {
      foundPlacemark.title = placemark.title
      foundPlacemark.description = placemark.description
      logAll()
    }
  }

  fun logAll() {
    placemarks.forEach{ info("${it}") }
  }

}