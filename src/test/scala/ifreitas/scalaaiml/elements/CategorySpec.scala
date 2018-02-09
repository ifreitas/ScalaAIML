package ifreitas.scalaaiml.elements

import org.mockito.Mockito._
import scala.xml.Node

class CategorySpec extends BaseSpec {
  describe("#toXml") {
    val xml = Category(patternStub, thatStub, templateStub).toXml()

    it should behave like xmlElement(xml, "category")

    it should behave like xmlContainerFor(xml, "pattern", "that", "template")

  }
}