package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.FakeRequest
import play.api.test.Helpers.{contentAsString, status, _}
import services.DrinkService
class DrinksControllerSpec extends PlaySpec with GuiceOneAppPerTest  {

  "Drinks Controller" should {
    "return some basic info and be accesible at the correct route" in {
      val request=FakeRequest(GET,"/drinks").withHeaders("Host"->"localhost")
      val drinksPage=route(app,request).get
      status(drinksPage) mustBe OK
      contentAsString(drinksPage) must include ("<title>Drinks</title")
      contentAsString(drinksPage) must include ("<h1>Drinks</h1>")
      contentAsString(drinksPage) must include ("<p>Coke</p>")
      contentAsString(drinksPage) must include ("<p>Lemonade</p>")

    }

    "display basic drinks and additional ones" in {
      val controller=new DrinksController(FakeAdditionalDrinksController)
      val result=controller.drinks().apply(FakeRequest(GET,"Foo"))
      status(result) mustBe OK
      contentAsString(result) must include ("<title>Drinks</title")
      contentAsString(result) must include ("<h1>Drinks</h1>")
      contentAsString(result) must include ("<p>Coke</p>")
      contentAsString(result) must include ("<p>Lemonade</p>")
      contentAsString(result) must include ("<p>Foo</p>")
      contentAsString(result) must include ("<p>Bar</p>")

    }
  }

  object FakeAdditionalDrinksController extends DrinkService {
    override val returnDrinks=List[String]("Foo","Bar")
  }
}
