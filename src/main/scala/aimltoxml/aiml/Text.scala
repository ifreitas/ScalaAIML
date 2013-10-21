package aimltoxml.aiml

class Text(theText:String) extends TemplateElement{
    def toXml = new scala.xml.Text(theText)
}
object Text{
    def apply(aText:String) = { new Text(aText) }
}
