package ifreitas.scalaaiml.elements

import org.mockito.Mockito._
import scala.xml.Node

class PatternSpec extends BaseSpec {
  describe("#toXml") {
    val xml = Pattern("some string", wordStub, evalStub, setStatementStub, patternSideBotPropertyStub).toXml()

    it should behave like xmlElement(xml, "pattern")

    it should behave like xmlContainerFor(xml, "word", "eval", "set", "bot")

  }
}