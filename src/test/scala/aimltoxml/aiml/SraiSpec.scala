package aimltoxml.aiml

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar

class SraiSpec extends FlatSpec with Matchers{

    it should "throws an exception when no default pattern" in {
        intercept[IllegalArgumentException]{Srai(None)}
    }
    it should "throws an exception when default pattern is empty" in {
    	intercept[IllegalArgumentException]{Srai("")}
    }
    
    "#toXml" should "generate a valid XML (some text)" in{
    	val expectedXml = <srai>hi</srai>
    	Srai(Some(Text("hi"))).toXml should be(expectedXml)
    }
    it should "generate a valid XML (text)" in{
    	val expectedXml = <srai>hi</srai>
    	Srai(Text("hi")).toXml should be(expectedXml)
    }
    it should "generate a valid XML (string)" in{
    	val expectedXml = <srai>hi</srai>
   		Srai("hi").toXml should be(expectedXml)
    }
}