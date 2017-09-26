package services

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models._
import play.api.libs.json._
import play.api.libs.ws.WSClientConfig
import uk.gov.hmrc.play.http.{HeaderCarrier, HttpResponse}
import uk.gov.hmrc.play.http.ws._

import scala.concurrent.Future

class RealOrdersService extends OrderService {
  val http = new WSGet {
    override val hooks = NoneRequired
  }
  val httpPost=new WSPost {
    override val hooks = NoneRequired
  }
  def getOrder={
    implicit val hc = HeaderCarrier()
    http.GET[myOrder]("http://localhost:9001/orders")
  }
  def submitOrder[Order](data:Order): Future[HttpResponse] ={
    implicit val hc = HeaderCarrier()
    httpPost.doPost[Order]("http://localhost:9001/make-orders",data,Seq())
  }
}

@ImplementedBy(classOf[RealOrdersService])
trait OrderService {
  def getOrder:Future[myOrder]
  def submitOrder[myOrder](data:myOrder):Future[HttpResponse]
}