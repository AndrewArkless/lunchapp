package models

import play.api.libs.json.{Format, Json}

/**
  * Created by andrew on 18/08/17.
  */
case class Sandwich(name: String, price: BigDecimal, description: String)

object Sandwich {
  implicit val formats: Format[Sandwich] = Json.format[Sandwich]
}