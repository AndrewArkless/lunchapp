package forms

import models.{myOrder}
import play.api.data._
import play.api.data.Forms._

/**
  * Created by andrew on 22/08/17.
  */
object OrderForms {
 val orderForm=Form(
   mapping(
     "name" -> text,
     "address" -> text,
     "order" -> text
   )(myOrder.apply)(myOrder.unapply)
 )
}
