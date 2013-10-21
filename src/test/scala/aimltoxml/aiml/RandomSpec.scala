package aimltoxml.aiml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class RandomSpec extends FlatSpec with Matchers {
    it should "throws an exception when no Options" in {
        intercept[IllegalArgumentException]{Random(None).toXml}
    }
    it should "generate a valid XML (varargs)" in{
        val expectedXml = <random><li>Hi</li><li>Hey</li></random>
        Random(Some(Text("Hi")), Some(Text("Hey"))).toXml should be(expectedXml)
    }
    it should "generate a valid XML (list)" in{
    	val expectedXml = <random><li>Hi</li><li>Hey</li></random>
    	Random(List(Some(Text("Hi")), Some(Text("Hey")))).toXml should be(expectedXml)
    }
}