package aimltoxml.aiml

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.collection.mutable.Set

class TopicSpec extends FlatSpec with Matchers{

    it should "throws an exception when no name" in {
        intercept[IllegalArgumentException]{Topic(null, None)}
    }
    it should "throws an exception when name is empty" in {
    	intercept[IllegalArgumentException]{Topic("", None)}
    }
    
    "#toXml" should "throws an exception when no categories" in{
        intercept[IllegalArgumentException]{Topic("aTopicName", None).toXml}
    }
    it should "throws an exception when categories is empty" in {
    	intercept[IllegalArgumentException]{Topic("aTopic", Set[Option[Category]]()).toXml}
    }
    it should "generate a valid XML (list)" in{
    	val expectedXml = <topic name="aTopicName"><category><pattern>HI</pattern><template>hello</template></category></topic>
    	Topic("aTopicName", Set[Option[Category]](Some(Category("hi", Some(Text("hello")))))).toXml should be(expectedXml)
    }
    it should "generate a valid XML (varargs)" in{
    	val expectedXml = <topic name="aTopicName"><category><pattern>HI</pattern><template>hello</template></category></topic>
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
    
    "#add" should "add the new Category to the its categories's set." in {
        val topic = Topic("aTopic", Set[Option[Category]]())
        topic.categories.size should be(0)
        topic.add(Category("hi", "Hello"))
        topic.categories.size should be(1)
    }
}