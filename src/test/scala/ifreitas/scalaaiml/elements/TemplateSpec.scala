package ifreitas.scalaaiml.elements

import org.mockito.Mockito._
import scala.xml.Node

class TemplateSpec extends BaseSpec {
  describe("#toXml") {
    val xml = Template(evalStub, thinkStub, botStub, uppercaseStub, lowercaseStub, sraiStub,
      sraixStub, setVarStub, personStub, person2Stub, normalizeStub, mapStub, genderStub,
      intervalStub, conditionStub, denormalizeStub, topicStarStub, srStub, requestStub,
      responseStub).toXml()

    it should behave like xmlElement(xml, "template")

    it should behave like xmlContainerFor(xml, "eval", "think", "bot", "uppercase", "lowercase",
      "srai", "sraix", "person", "person2", "normalize", "map", "gender", "interval", "condition",
      "denormalize", "topicstar", "sr", "request", "response")

    val xml2 = Template(getVarStub, getPredicateStub, setPredicateStub, inputStub, randomStub,
      textStub, varStub, systemStub, sentenceStub, formalStub, explodeStub,
      valueStub, thatstarStub, programStub, learnStub, learnFStub).toXml()

    it should behave like xmlContainerFor(xml2, "get", "set", "input", "random", "text", "var",
      "system", "sentence", "formal", "explode", "value", "thatstar", "program", "learn", "learnf")

  }
}