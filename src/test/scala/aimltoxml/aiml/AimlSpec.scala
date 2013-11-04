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

class AimlSpec extends FlatSpec with Matchers {

    it should "throws an exception when no name" in {
        intercept[IllegalArgumentException]{Aiml(null)}
    }
    it should "throws an exception when name is empty" in {
    	intercept[IllegalArgumentException]{Aiml("")}
    }
    
    "#toXml" should "throws an exception when Topics is empty" in {
    	intercept[IllegalArgumentException]{Aiml("aAiml", Set.empty[Topic]).toXml}
    }
    it should "generate a valid XML" in{
    	intercept[IllegalArgumentException]{Aiml("aAimlName", Set[Topic](Topic("aTopic", Set.empty[Category]))).toXml}
    }
    it should "generate an XML with the same amount of Topics (list)" in {
        val topic1 = Topic("aTopic", Category("hi", Text("hey")))
        val topic2 = Topic("otherTopic", Category("hey", Text("ho")))
        val topic3 = Topic("yetOtherTopic", Category("wow", Text("lol")))
        val aiml   = Aiml("aAimlName", Set[Topic](topic1, topic2, topic3))
        val xml = aiml.toXml
        (xml \ "topic" ).size should be(3)
    }
    it should "generate an XML with the same amount of Topics (varargs)" in {
    	val topic1 = Topic("aTopic", Category("hi", Text("hey")))
        val topic2 = Topic("otherTopic", Category("hey", Text("ho")))
        val topic3 = Topic("yetOtherTopic", Category("wow", Text("lol")))
    	val aiml   = Aiml("aAimlName", topic1, topic2, topic3)
    	val xml = aiml.toXml
    	(xml \ "topic" ).size should be(3)
    }
}