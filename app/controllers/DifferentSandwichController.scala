package controllers

import javax.inject.Inject

import models.{DifferentSandwich, Sandwich}
import play.api.mvc.{Action, Controller}
import services.DifferentSandwichService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by andrew on 21/08/17.
  */
class DifferentSandwichController @Inject()(ds:DifferentSandwichService) extends Controller {

  def differentSarnies()=Action.async{request=>
    val s: Future[List[DifferentSandwich]] =ds.sandwiches()
    s.map { sarnies =>
      Ok(views.html.differntSarnies(sarnies))
    }.recover {
      case _ => Ok(views.html.errorPage())
    }
  }
}
