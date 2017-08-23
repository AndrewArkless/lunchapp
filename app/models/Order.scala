package models

import play.api.libs.json.{Format, Json}

/**
  * Created by andrew on 22/08/17.
  */
case class Order(name:String, address:String, order:String)

object Order {
  implicit val formats: Format[Order] = Json.format[Order]
}
