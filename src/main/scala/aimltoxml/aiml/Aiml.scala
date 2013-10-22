package aimltoxml.aiml

import scala.xml.XML
import scala.collection.mutable.Set

class Aiml(name:String, theTopics:Set[Option[Topic]]){
    require(name!=null && !name.toString().equals(""))
    
    def toXml = {
    	require(!theTopics.isEmpty, "The Aiml object must have at least one Topic")
    	
        <aiml version="1.0.1" xmlns="http://alicebot.org/2001/AIML-1.0.1" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd">{
        theTopics.map(theTopic=>theTopic match{
            case Some(topic) => topic.toXml
            case _ => throw new IllegalArgumentException("Invalid Topic: \"" + theTopic +"\".")
            })}</aiml>
    }
    
    def toXmlFile = XML.save(this.name, this.toXml, null, false, null)
    
    def topic(topicName:String)= (theTopics find (_.get.name == topicName)).get
}


abstract class AbstractAiml{
	final def apply(name:String, topics:Set[Option[Topic]]) = {new Aiml(name, topics)}
	final def apply(name:String, topics:Option[Topic]*)      = {new Aiml(name, Set(topics: _*))}
}

/** 
 *  The AIML Companion Object.
 */
object Aiml extends AbstractAiml

/**
 * Shorthand for Aiml
 */
object A extends AbstractAiml