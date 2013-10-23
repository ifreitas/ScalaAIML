package aimltoxml.aiml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class CategorySpec extends FlatSpec with Matchers {
    it should "throws an exception when no pattern" in {
        intercept[IllegalArgumentException]{Category(null, Text("hi"))}
    }
    it should "throws an exception when pattern is empty" in {
    	intercept[IllegalArgumentException]{Category("", Text("hi"))}
    }
    it should "throws an exception when template is empty" in {
    	intercept[IllegalArgumentException]{Category("hi", Set[Option[TemplateElement]]())}
    }
    
//    "#toXml" should "throws an exception when no template" in{
//        intercept[IllegalArgumentException]{Category("Hi", None).toXml}
//    }
    it should "generate a valid XML (Set Some Text)" in{
    	val expectedXml = <category><pattern>HI</pattern><template>Hello</template></category>
    			Category("hi", Set[Option[TemplateElement]](Some(Text("Hello")))).toXml should be(expectedXml)
    }
    it should "generate a valid XML (Some Text)" in{
    	val expectedXml = <category><pattern>HI</pattern><template>Hello</template></category>
    			Category("hi", Text("Hello")).toXml should be(expectedXml)
    }
    it should "generate a valid XML (String)" in{
    	val expectedXml = <category><pattern>HI</pattern><template>Hello</template></category>
    			Category("hi", "Hello").toXml should be(expectedXml)
    }
    it should "generate a valid XML (multiple template Strings)" in{
    	val expectedXml = <category><pattern>HI</pattern><template>Hello how are you</template></category>
    			Category("hi", "Hello", " how are you").toXml.toString should equal(expectedXml.toString)
    }
    it should "generate a valid XML with UPPER CASE pattern" in{
    	val expectedXml = <category><pattern>UPPER CASE</pattern><template>ok</template></category>
    			Category("upper case", Text("ok")).toXml should be(expectedXml)
    }
}