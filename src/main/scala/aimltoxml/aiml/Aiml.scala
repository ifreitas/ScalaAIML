package aimltoxml.aiml

import scala.xml.XML

class Aiml(name:String, theTopics:List[Option[Topic]]){
    require(name!=null && !name.toString().equals(""))
    require(!theTopics.isEmpty, "The Aiml object must have at least one Topic")
    
    def toXml = <aiml version="1.0.1" xmlns="http://alicebot.org/2001/AIML-1.0.1" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd">{
        theTopics.map(theTopic=>theTopic match{
            case Some(topic) => topic.toXml
            case _ => throw new IllegalArgumentException("Invalid Topic: \"" + theTopic +"\".")
            })}</aiml>
    
    def toXmlFile = XML.save(this.name, this.toXml, null, false, null)
}
object Aiml{
    def apply(name:String, topics:List[Option[Topic]])	= {new Aiml(name, topics)}
    def apply(name:String, topics:Option[Topic]*)		= {new Aiml(name, topics.toList)}
}