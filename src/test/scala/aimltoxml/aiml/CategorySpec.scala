package aimltoxml.aiml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class CategorySpec extends FlatSpec with Matchers {
    it should "throws an exception when no template" in {
        intercept[IllegalArgumentException]{new Category(null, None)}
    }
    it should "throws an exception when template is empty" in {
    	intercept[IllegalArgumentException]{new Category("", None)}
    }
    it should "generate a valid XML" in{
        val expectedXml = <category><pattern>Hi</pattern><template>Hello</template></category>
        new Category("Hi", Some(Text("Hello"))).toXml should be(expectedXml)
    }
}