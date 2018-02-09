package ifreitas.scalaaiml.elements

import org.mockito.Mockito._
import scala.xml.Node

class ThatSpec extends BaseSpec {
  describe("#toXml") {
    val xml = That("some string", wordStub, evalStub, setStatementStub, patternSideBotPropertyStub).toXml()

    it should behave like xmlElement(xml, "that")

    it should behave like xmlContainerFor(xml, "word", "eval", "set", "bot")

  }
}