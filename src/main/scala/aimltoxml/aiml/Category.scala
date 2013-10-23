package aimltoxml.aiml

import scala.xml.Elem
import scala.xml.Node
import scala.xml.transform.RewriteRule
import scala.xml.transform.RuleTransformer
import scala.collection.immutable.Set
import reflect.runtime.universe.TypeTag

class Category(pattern:String, templateElements:Set[Option[TemplateElement]]){
    require(pattern!=null && !pattern.toString().equals(""))
    require(!templateElements.isEmpty, "The Category must have at least one Template Element. Example: Category(\"Hi\", Some(Text(\"Hello\")))")

    def toXml = {
        <category><pattern>{pattern.toUpperCase()}</pattern><template>{templateElements.map(templateElement=> templateElement match{
            case Some(template) => {template.toXml}
            case _				=> throw new IllegalArgumentException("Invalid Template: \"" + templateElement + "\".")
        })}</template></category>
    }
}

abstract class AbstractCategory{
	final def apply(pattern:String, templateElements:Set[Option[TemplateElement]]):Category                                = {new Category(pattern, templateElements)}
	final def apply(pattern:String, templateElements:Set[TemplateElement])(implicit ev: TypeTag[TemplateElement]):Category = {Category(pattern, templateElements.map{templateElement=>Some(templateElement)}.toSet[Option[TemplateElement]])}
	final def apply(pattern:String, templateElements:TemplateElement*):Category                                            = {Category(pattern, templateElements.toSet)}
	final def apply(pattern:String, templateElements:String*)(implicit ev: TypeTag[String]):Category                       = {Category(pattern, templateElements.map{templateElement=>Some(Text(templateElement))}.toSet[Option[TemplateElement]])}
}

/**
 * The Category Companion Object.
 */
object Category extends AbstractCategory

/**
 * Shorthand for Category.
 */
object C extends AbstractCategory
