/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Israel Freitas -- ( gmail => israel.araujo.freitas)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
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
    	intercept[IllegalArgumentException]{Category("hi", Set[TemplateElement]())}
    }
    
    "#toXml" should "generate a valid XML (Set Some Text)" in{
    	val expectedXml = <category><pattern>HI</pattern><template>Hello</template></category>
    			Category("hi", Text("Hello")).toXml should be(expectedXml)
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