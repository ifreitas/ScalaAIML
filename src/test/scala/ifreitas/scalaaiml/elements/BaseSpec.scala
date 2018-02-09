package ifreitas.scalaaiml.elements

import scala.reflect.ClassTag
import scala.xml.XML

import org.mockito.Mockito.when
import org.scalatest.{ FunSpec, Matchers }
import org.scalatest.mockito.MockitoSugar

trait BaseSpec extends FunSpec with Matchers with XmlElementBehavior {
  def nameStub = stub[Name]("name")
  def topicStub = stub[Topic]("topic")
  def patternStub = stub[Pattern]("pattern")
  def templateStub = stub[Template]("template")
  // Pattern Expressions
  def thatStub = stub[That]("that")
  def wordStub = stub[Word]("word")
  def setStatementStub = stub[SetStatement]("set")
  def patternSideBotPropertyStub = stub[PatternSideBotProperty]("bot")
  def evalStub = stub[Eval]("eval")
  //Template Expressions
  def thinkStub = stub[Think]("think")
  def botStub = stub[Bot]("bot")
  def uppercaseStub = stub[Uppercase]("uppercase")
  def lowercaseStub = stub[Lowercase]("lowercase")
  def sraiStub = stub[Srai]("srai")
  def sraixStub = stub[Sraix]("sraix")
  def setVarStub = stub[SetVar]("set")
  def personStub = stub[Person]("person")
  def person2Stub = stub[Person2]("person2")
  def normalizeStub = stub[Normalize]("normalize")
  def mapStub = stub[Map]("map")
  def genderStub = stub[Gender]("gender")
  def intervalStub = stub[Interval]("interval")
  def conditionStub = stub[Condition]("condition")
  def denormalizeStub = stub[Denormalize]("denormalize")
  def topicStarStub = stub[TopicStar]("topicstar")
  def srStub = stub[Sr]("sr")
  def requestStub = stub[Request]("request")
  def responseStub = stub[Response]("response")
  def getVarStub = stub[GetVar]("get")
  def getPredicateStub = stub[GetPredicate]("get")
  def setPredicateStub = stub[SetPredicate]("set")
  def inputStub = stub[Input]("input")
  def randomStub = stub[Random]("random")
  def textStub = stub[Text]("text")
  def varStub = stub[Var]("var")
  def systemStub = stub[System]("system")
  def sentenceStub = stub[Sentence]("sentence")
  def formalStub = stub[Formal]("formal")
  def explodeStub = stub[Explode]("explode")
  def valueStub = stub[Value]("value")
  def thatstarStub = stub[ThatStar]("thatstar")
  def programStub = stub[Program]("program")
  def learnStub = stub[Learn]("learn")
  def learnFStub = stub[LearnF]("learnf")

  def stub[T <: ToXml](label: String)(implicit classTag: ClassTag[T]) = {
    val stubedObject = MockitoSugar.mock[T]
    when(stubedObject.toXml()).thenReturn(XML.loadString(s"<${label}/>"))
    stubedObject
  }
}
