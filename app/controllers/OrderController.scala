package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import services.OrderService

import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by andrew on 22/08/17.
  */
class OrderController @Inject()(os:OrderService) extends Controller {
def getOrder=Action.async{
  val order=os.getOrder
  order.map { o =>
    Ok(views.html.order(o))
    }
  }
}
