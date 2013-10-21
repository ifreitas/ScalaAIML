package aimltoxml.aiml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class TopicSpec extends FlatSpec with Matchers {

    "A Topic" should "throws an exception when no name" in {
        intercept[IllegalArgumentException]{new Topic(null, None)}
    }
    it should "throws an exception when name is empty" in {
    	intercept[IllegalArgumentException]{new Topic("", None)}
    }
    
    it should "generate a valid XML (without Categories)" in{
        val expectedXml = <topic name="aTopicName"/>
        new Topic("aTopicName", None).toXml should be(expectedXml)
    }
    it should "generate a valid XML (with Categories)" in{
    	val expectedXml = <topic name="aTopicName"><category/></topic>
    	new Topic("aTopicName", Some(List(new Category("", None)))).toXml should be(expectedXml)
    }
    
    it should "generate an XML with the same amount of Categories" in {
        val category1 = new Category("aCategory", None)
        val category2 = new Category("otherCategory", None)
        val category3 = new Category("yetOtherCategory", None)
        val topic     = new Topic("aTopicName", Some(List(category1, category2, category3)))
        val xml       = topic.toXml
        (xml \ "category" ).size should be(3)
    }
}