package aimltoxml.aiml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class AimlSpec extends FlatSpec with Matchers {

    it should "throws an exception when no name" in {
        intercept[IllegalArgumentException]{Aiml(null,None)}
    }
    it should "throws an exception when name is empty" in {
    	intercept[IllegalArgumentException]{Aiml("", None)}
    }
    it should "throws an exception when Topics is empty" in {
    	intercept[IllegalArgumentException]{Aiml("aAiml", List())}
    }
    
    "#toXml" should "throw an exception when no topic" in{
        intercept[IllegalArgumentException]{Aiml("aAimlName", None).toXml}
    }
    it should "generate a valid XML" in{
    	intercept[IllegalArgumentException]{Aiml("aAimlName", List(Some(Topic("aTopic", None)))).toXml}
    }
    it should "generate an XML with the same amount of Topics (list)" in {
        val topic1 = Topic("aTopic", Some(Category("hi", Some(Text("hey")))))
        val topic2 = Topic("otherTopic", Some(Category("hey", Some(Text("ho")))))
        val topic3 = Topic("yetOtherTopic", Some(Category("wow", Some(Text("lol")))))
        val aiml   = Aiml("aAimlName", List(Some(topic1), Some(topic2), Some(topic3)))
        val xml = aiml.toXml
        (xml \ "topic" ).size should be(3)
    }
    it should "generate an XML with the same amount of Topics (varargs)" in {
    	val topic1 = Topic("aTopic", Some(Category("hi", Some(Text("hey")))))
        val topic2 = Topic("otherTopic", Some(Category("hey", Some(Text("ho")))))
        val topic3 = Topic("yetOtherTopic", Some(Category("wow", Some(Text("lol")))))
    	val aiml   = Aiml("aAimlName", Some(topic1), Some(topic2), Some(topic3))
    	val xml = aiml.toXml
    	(xml \ "topic" ).size should be(3)
    }
}