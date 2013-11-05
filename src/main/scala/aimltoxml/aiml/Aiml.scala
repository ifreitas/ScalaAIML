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

import scala.xml.XML

class Aiml(val name: String, val topics: Set[Topic] = Set(Topic.default)) extends Learnable{
    require(name != null && !name.isEmpty)
    require(!topics.isEmpty, "The Aiml object must have at least one Topic")

    def toXml = {
        <aiml version="1.0.1" xmlns="http://alicebot.org/2001/AIML-1.0.1" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd">{
            topics.map(theTopic => theTopic match {
                case topic: Topic => topic.toXml
                case _            => throw new IllegalArgumentException("Invalid Topic: \"" + theTopic + "\".")
            })
        }</aiml>
    }

    def toXmlFile = XML.save(this.name, this.toXml, null, false, null)

    def topic(topicName: String) = {
        (topics find (_.name == topicName)) match {
            case Some(topic) => topic
            case x           => throw new IllegalArgumentException(f"Topic \'$x\' not found")
        }
    }

    def canEqual(other: Any) = other.isInstanceOf[aimltoxml.aiml.Aiml]

    override def equals(other: Any) = {
        other match {
            case that: aimltoxml.aiml.Aiml => that.canEqual(Aiml.this) && that.name == this.name && that.topics == this.topics
            case _                         => false
        }
    }

    override def hashCode = 41 * name.hashCode + topics.hashCode

}

abstract class AbstractAiml {
    final def apply(name: String, topics: Set[Topic]) = { new Aiml(name, topics) }
    final def apply(name: String, topics: Topic*): Aiml = { Aiml(name, Set(topics: _*)) }
    final def apply(name: String) = { new Aiml(name) } // invokes the primary constructor in order to create an Aiml with a default Topic
}

/**
 *  The AIML Companion Object.
 */
object Aiml extends AbstractAiml

/**
 * Shorthand for Aiml
 */
object A extends AbstractAiml