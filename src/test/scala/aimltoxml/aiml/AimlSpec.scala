package aimltoxml.aiml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class AimlSpec extends FlatSpec with Matchers {

    it should "throws an exception when no name" in {
        intercept[IllegalArgumentException]{new Aiml(null,None)}
    }
    it should "throws an exception when name is empty" in {
    	intercept[IllegalArgumentException]{new Aiml("", None)}
    }
    
    it should "generate a valid XML (without Topic)" in{
        val expectedXml = <aiml version="1.0.1" xmlns="http://alicebot.org/2001/AIML-1.0.1" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd"/>
        new Aiml("aAimlName", None).toXml should be(expectedXml)
    }
    it should "generate a valid XML (with Topic)" in{
    	val expectedXml = <aiml version="1.0.1" xmlns="http://alicebot.org/2001/AIML-1.0.1" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd"><topic name="aTopic"/></aiml>
    	new Aiml("aAimlName", Some(List(new Topic("aTopic", None)))).toXml should be(expectedXml)
    }
    
    it should "generate an XML with the same amount of Topics" in {
        val topic1 = new Topic("aTopic", None)
        val topic2 = new Topic("otherTopic", None)
        val topic3 = new Topic("yetOtherTopic", None)
        val aiml   = new Aiml("aAimlName", Some(List(topic1, topic2, topic3)))
        val xml = aiml.toXml
        (xml \ "topic" ).size should be(3)
    }
}