package controllers

import com.google.inject.Inject
import models.Sandwich
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{Action, Controller}
import services.SandwichService

import scala.concurrent.Future

class SandwichController @Inject()(sandwichService: SandwichService) extends Controller{
  def sandwiches() = Action.async {
    val sandwiches: Future[List[Sandwich]] = sandwichService.sandwiches
    sandwiches.map {sandwiches=>
      Ok(views.html.sandwiches(sandwiches))
    }
  }
}