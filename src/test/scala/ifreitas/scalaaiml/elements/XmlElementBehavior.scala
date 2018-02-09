package ifreitas.scalaaiml.elements

import scala.xml.Elem

import org.scalatest.{ FunSpec, Matchers }

trait XmlElementBehavior { this: FunSpec with Matchers =>
  def xmlElement(element: Elem, label: String) {
    it(s"should return an <$label> tag") {
      element.label should be(label)
    }
  }
  def xmlContainerFor(element: Elem, labels: String*) {
    labels.foreach { label =>
      it(s"should contain a <$label> tag") {
        element \ label should not be ('empty)
      }
    }
  }
}