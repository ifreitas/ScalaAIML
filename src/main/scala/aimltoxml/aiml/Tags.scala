package aimltoxml.aiml

import scala.xml.Node
import scala.xml.XML
import scala.xml.Elem
import scala.xml.transform.RewriteRule
import scala.xml.transform.RuleTransformer

class Aiml(name:String, topics:Option[List[Topic]]){
    require(name!=null)
    require(!name.toString().equals(""))
    
    def toXml = topics match{
        case Some(theTopics) => <aiml version="1.0.1" xmlns="http://alicebot.org/2001/AIML-1.0.1" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd">{theTopics.map{topic => topic.toXml}}</aiml>
        case _ => <aiml version="1.0.1" xmlns="http://alicebot.org/2001/AIML-1.0.1" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd" />
    }
    
    def toXmlFile = XML.save(this.name, this.toXml, null, false, null)
}


class Topic(name:String, categories:Option[List[Category]]){
    require(name!=null)
    require(!name.toString().equals(""))
    
    def toXml = categories match{
        case Some(theCategories) => <topic name={this.name}>{theCategories.map{category => category.toXml}}</topic>
        case None => <topic name={this.name}/>
    }
}


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


abstract class TemplateElement{
    def toXml:Node
}


class Text(theText:String) extends TemplateElement{
    def toXml = new scala.xml.Text(theText)
}
object Text{
    def apply(aText:String) = { new Text(aText) }
}

class That()
class Think()
class Srai()
class Condition()


object Temp{
    def main(args: Array[String]) {
        val opt = Some("text")
    	println(opt.get)
    }
}