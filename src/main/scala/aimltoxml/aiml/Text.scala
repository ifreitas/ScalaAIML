package aimltoxml.aiml

class Text(theText:String) extends TemplateElement with RandomElement{
    def toXml = new scala.xml.Text(theText)
    def hasContent = theText!=null && !theText.trim().equals("")
}
object Text{
    def apply(aText:String) = { new Text(aText) }
}
