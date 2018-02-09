package ifreitas.scalaaiml.elements

import org.mockito.Mockito._
import scala.xml.Node

class AimlSpec extends BaseSpec with XmlElementBehavior  {
  describe("#toXml") {
    val xml = Aiml("file-name", topicStub).toXml()

    it should behave like xmlElement(xml, "aiml")
    
    it should behave like xmlContainerFor(xml, "topic")

    it("should have version '2.0'") {
      xml \@ "version" should be("2.0")
    }
  }
  describe("save") {
    it("should call XML's save method") {
      pending
    }
  }
}
