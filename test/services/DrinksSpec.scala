package services

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest

/**
  * Created by andrew on 21/08/17.
  */
class DrinksSpec extends PlaySpec {
 "DrinksService" should {
   "Return Tea and Coffee if before 12 " in {
     val ds = new realDrinkService(morning)
     ds.returnDrinks mustBe List("Tea","Coffee")
   }

   "Return Turps and White lighting if after 12 " in {
     val ds = new realDrinkService(afternoon)
     ds.returnDrinks mustBe List("Turps","White Lightning")
   }
 }
  object morning extends GreetingService {
    override def greeting ="Good Morning"
  }
  object afternoon extends GreetingService {
    override def greeting ="Good Afternoon"
  }
}


