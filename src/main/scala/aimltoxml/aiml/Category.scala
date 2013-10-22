package aimltoxml.aiml

import scala.xml.Elem
import scala.xml.Node
import scala.xml.transform.RewriteRule
import scala.xml.transform.RuleTransformer

class Category(pattern:String, templateElements:List[Option[TemplateElement]]){
    require(pattern!=null && !pattern.toString().equals(""))
    require(!templateElements.isEmpty, "The Category must have at least one Template Element. Example: Category(\"Hi\", Some(Text(\"Hello\")))")

    def toXml = {
        <category><pattern>{pattern}</pattern>{templateElements.map(templateElement=> templateElement match{
            case Some(template) => <template>{template.toXml}</template>
            case _				=> throw new IllegalArgumentException("Invalid Template: \"" + templateElement + "\".")
        })}</category>
    }
}
object Category{
    def apply(pattern:String, templateElements:List[Option[TemplateElement]])	= {new Category(pattern, templateElements)}
    def apply(pattern:String, templateElements:Option[TemplateElement]*)		= {new Category(pattern, templateElements.toList)}
}
