package services

import com.google.inject.ImplementedBy
import models.{DifferentSandwich, Sandwich}

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import uk.gov.hmrc.play.http.ws.WSGet
import uk.gov.hmrc.play.http.{HeaderCarrier, HttpGet, HttpReads, HttpResponse}

class realDifferentSandwichService extends DifferentSandwichService {
  class JsonValidationException(message: String) extends Exception(message)
  val http = new WSGet {
    override val hooks = NoneRequired
  }

  override def sandwiches: Future[List[DifferentSandwich]] = {
    implicit val hc = HeaderCarrier()
    val response: Future[HttpResponse] =http.GET[HttpResponse]("http://localhost:3000/sandwiches")(rds = HttpReads.readRaw, hc)
    response.flatMap{ httpResponse=>
      httpResponse.json.validate[List[DifferentSandwich]].fold(
        invalid => Future.failed(new JsonValidationException("invalid")),
        valid => Future.successful(valid)
      )
    }
  }
}

@ImplementedBy(classOf[realDifferentSandwichService])
trait DifferentSandwichService {
  def sandwiches() : Future[List[DifferentSandwich]]
}