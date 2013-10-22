package aimltoxml.aiml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class CategorySpec extends FlatSpec with Matchers {
    it should "throws an exception when no pattern" in {
        intercept[IllegalArgumentException]{Category(null, Some(Text("hi")))}
    }
    it should "throws an exception when pattern is empty" in {
    	intercept[IllegalArgumentException]{Category("", Some(Text("hi")))}
    }
    it should "throws an exception when template is empty" in {
    	intercept[IllegalArgumentException]{Category("hi", List())}
    }
    
    "#toXml" should "throws an exception when no template" in{
        intercept[IllegalArgumentException]{Category("Hi", None).toXml}
    }
    it should "generate a valid XML" in{
    	val expectedXml = <category><pattern>Hi</pattern><template>Hello</template></category>
    			Category("Hi", Some(Text("Hello"))).toXml should be(expectedXml)
    }
}