package aimltoxml.aiml

import scala.xml.XML

class Aiml(name:String, topics:Option[List[Topic]]){
    require(name!=null)
    require(!name.toString().equals(""))
    
    def toXml = topics match{
        case Some(theTopics) => <aiml version="1.0.1" xmlns="http://alicebot.org/2001/AIML-1.0.1" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd">{theTopics.map{topic => topic.toXml}}</aiml>
        case _ => <aiml version="1.0.1" xmlns="http://alicebot.org/2001/AIML-1.0.1" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd" />
    }
    
    def toXmlFile = XML.save(this.name, this.toXml, null, false, null)
}
object Aiml{
    def apply(name:String, topics:Option[List[Topic]])={new Aiml(name, topics)}
}