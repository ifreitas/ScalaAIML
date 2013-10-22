package aimltoxml.aiml

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar

class TopicSpec extends FlatSpec with Matchers with MockitoSugar{

    it should "throws an exception when no name" in {
        intercept[IllegalArgumentException]{Topic(null, None)}
    }
    it should "throws an exception when name is empty" in {
    	intercept[IllegalArgumentException]{Topic("", None)}
    }
    it should "throws an exception when categories is empty" in {
    	intercept[IllegalArgumentException]{Topic("aTopic", List())}
    }
    
    "#toXml" should "throws an exception when no categories" in{
        intercept[IllegalArgumentException]{Topic("aTopicName", None).toXml}
    }
    it should "generate a valid XML (list)" in{
    	val expectedXml = <topic name="aTopicName"><category><pattern>hi</pattern><template>hello</template></category></topic>
    	Topic("aTopicName", List(Some(Category("hi", Some(Text("hello")))))).toXml should be(expectedXml)
    }
    it should "generate a valid XML (varargs)" in{
    	val expectedXml = <topic name="aTopicName"><category><pattern>hi</pattern><template>hello</template></category></topic>
    	Topic("aTopicName", Some(Category("hi", Some(Text("hello"))))).toXml should be(expectedXml)
    }
    
    it should "generate an XML with the same amount of Categories" in {
        val category1 = Category("hi", Some(Text("hello")))
        val category2 = Category("how are you", Some(Text("I'm fine. And you?")))
        val category3 = Category("Me too", Some(Text("good")))
        val topic     = Topic("aTopicName", Some(category1), Some(category2), Some(category3))
        
        val xml       = topic.toXml
        (xml \ "category" ).size should be(3)
    }
}