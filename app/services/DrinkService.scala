package services

import javax.inject.Inject

import com.google.inject.ImplementedBy

/**
  * Created by andrew on 21/08/17.
  */
class realDrinkService @Inject()(greetings:GreetingService) extends DrinkService {
  def returnDrinks=
    if (greetings.greeting=="Good Morning")
    List[String]("Tea","Coffee")
    else List[String]("Turps","White Lightning")
}

@ImplementedBy(classOf[realDrinkService])
trait DrinkService {
  def returnDrinks:List[String]
}