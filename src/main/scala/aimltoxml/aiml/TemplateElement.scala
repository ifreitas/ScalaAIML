package aimltoxml.aiml

import scala.xml.Node

abstract class TemplateElement{
    def toXml:Node
}