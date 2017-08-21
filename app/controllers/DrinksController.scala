package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import services.DrinkService

/**
  * Created by andrew on 21/08/17.
  */
class DrinksController @Inject()(ds:DrinkService) extends Controller {
  def drinks()=Action{
    val drinks: List[String] =ds.returnDrinks
    Ok(views.html.drinks(drinks))
  }
}
