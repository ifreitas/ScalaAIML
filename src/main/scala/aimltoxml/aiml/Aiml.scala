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

case class Aiml(name: String, topics: Set[Topic] = Set(Topic.default)) extends Learnable{
    require(name != null && !name.isEmpty, "The name is required.")
    require(!topics.isEmpty, "The Aiml object must have at least one Topic")

    def toXml = <aiml version="1.0.1"	xmlns="http://alicebot.org/2001/AIML-1.0.1" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd">{
        topics.map(_.toXml)
        }</aiml>

    def toXmlFile = XML.save(this.name, this.toXml, "UTF-8", false, null)
}

abstract class AbstractAiml {
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