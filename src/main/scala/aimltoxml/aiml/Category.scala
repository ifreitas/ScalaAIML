package aimltoxml.aiml

import scala.xml.transform.RewriteRule
import scala.xml.Elem
import scala.xml.transform.RuleTransformer
import scala.xml.Node

class Category(pattern:String, templateElements:Option[TemplateElement]*){
    require(pattern!=null)
    require(!pattern.toString().equals(""))
    
    def toXml = {
        val xml = <category><pattern>{pattern}</pattern><template/></category>
        val rule1 = new RewriteRule {
		  override def transform(n: Node) = n match {
		  	case Elem(prefix, "template", attribs, scope, _*) =>
		  	    val templateElementOptions = templateElements.map(templateElementOption => templateElementOption.get.toXml)
		  	    <template>{templateElementOptions}</template>
		    case _ => n 
		  }
		}
        new RuleTransformer(rule1).transform(xml)
    }
}
object Category{
    def apply(pattern:String, templateElements:Option[TemplateElement]*)={new Category(pattern, templateElements: _*)}
}
