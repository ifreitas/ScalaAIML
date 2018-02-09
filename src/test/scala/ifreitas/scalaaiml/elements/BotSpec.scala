package ifreitas.scalaaiml.elements

import org.mockito.Mockito._
import scala.xml.Node

class BotSpec extends BaseSpec  {
  describe("#toXml") {
    val xml = Bot(nameStub).toXml()

    it should behave like xmlElement(xml, "bot")
    
    it should behave like xmlContainerFor(xml, "name")
    
  }
}
