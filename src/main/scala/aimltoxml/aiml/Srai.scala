package aimltoxml.aiml

class Srai(defaultPattern:Option[Text]) extends TemplateElement with RandomElement{
    require(defaultPattern.isDefined)
    require(defaultPattern.exists(p=>p.hasContent))
    
	def toXml= <srai>{this.defaultPattern.get.toXml}</srai>
}

abstract class AbstractSrai{
	final def apply(defaultPattern:String) 		 = {new Srai(Some(Text(defaultPattern)))}
	final def apply(defaultPattern:Text)         = {new Srai(Some(defaultPattern))}
	final def apply(defaultPattern:Option[Text]) = {new Srai(defaultPattern)}
}

/**
 * The Srai Companion Object.
 */
object Srai extends AbstractSrai

/**
 * Shorthand for Srai.
 */
object S extends AbstractSrai
