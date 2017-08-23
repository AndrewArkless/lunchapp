package models
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
  import play.api.libs.json.{Format, Json}

  case class DifferentSandwich(called: String, cost: Int,stuff:Option[String])

  object DifferentSandwich {
    implicit val differentSandwichReads: Reads[DifferentSandwich] =(
      (JsPath \ "name").read[String] and
        (JsPath \ "price").read[BigDecimal].map(x=>1) and
         (JsPath \ "stuff").readNullable[String]
    )(DifferentSandwich.apply _)
  }

