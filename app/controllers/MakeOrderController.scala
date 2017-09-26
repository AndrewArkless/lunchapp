package controllers

import javax.inject.Inject

import play.api.mvc._
import services.OrderService
import forms.OrderForms._
import models.{Order, myOrder}
import play.api.data.Form
import play.api.libs.json.JsValue
import uk.gov.hmrc.play.http.HttpResponse

import scala.concurrent.{ExecutionContext, Future}
/**
  * Created by andrew on 22/08/17.
  */
 class MakeOrderController @Inject()(os:OrderService)(components: MessagesControllerComponents)(implicit executionContext:ExecutionContext)
    extends MessagesAbstractController(components) {

  def makeOrder = Action { implicit request =>
    val o = orderForm
    Ok(views.html.makeOrders(o))
  }

  def submitOrder() = Action.async { implicit request =>
    orderForm.bindFromRequest.fold(
      formWithErrors => {
      Future.successful(BadRequest(views.html.makeOrders(formWithErrors)))
      },
      userData => {
        val newOrder: myOrder =myOrder(userData.name, userData.address, "Stuff")
        val response: Future[HttpResponse] =os.submitOrder(newOrder)
        response.flatMap { x =>
          x.status match {
            case OK => {
              println("OK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
              Future.successful(Redirect(routes.WelcomeController.welcome()))
            }
            case _ => {
              println("ERROR")
              Future.successful(Redirect(routes.WelcomeController.welcome()))
            }
          }
        }.recoverWith {
          case e => println("TIMEOUT!!!!!")
            Future.successful(Redirect(routes.WelcomeController.welcome()))
        }
      }
    )
  }
}
