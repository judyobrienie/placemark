package org.wit.placemark.models

interface PlacemarkStore {
  fun findAll(): List<PlacemarkModel>
  fun create(placemark: PlacemarkModel)

  // to allow us to edid placemark
  fun update(placemark: PlacemarkModel)
}

