package aimltoxml.aiml

class Srai(defaultPattern:Option[Text]) extends TemplateElement with RandomElement{
    require(defaultPattern.isDefined)
    require(defaultPattern.exists(p=>p.hasContent))
    
	def toXml= <srai>{this.defaultPattern.get.toXml}</srai>
}
object Srai{
    def apply(defaultPattern:String) 		= {new Srai(Some(Text(defaultPattern)))}
    def apply(defaultPattern:Text)	 		= {new Srai(Some(defaultPattern))}
    def apply(defaultPattern:Option[Text])	= {new Srai(defaultPattern)}
}
