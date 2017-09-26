package models

import play.api.libs.json.{Format, Json}

/**
  * Created by andrew on 22/08/17.
  */
case class myOrder(name:String, address:String, order:String)

object myOrder {
  implicit val formats: Format[myOrder] = Json.format[myOrder]
}
