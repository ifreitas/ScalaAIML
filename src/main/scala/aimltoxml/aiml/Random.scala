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

import scala.xml.Node

trait RandomElement {
    def toXml: Node
}

case class Random(options: Set[List[RandomElement]]) extends TemplateElement {
    require(!options.isEmpty, "The Random object must have at least one option. Example: Random(Some(Text(\"Some text here.\")))")

    def toXml = <random>{
            options.map(option => <li><sentence>{ option.map(_.toXml) }</sentence></li>)
        }</random>

}

abstract class AbstractRandom {
    final def apply(options: String*): Random = new Random(options.toSet[String].map{str=>List(Text(str))})
}

/**
 * The Random Companion Object.
 */
object Random extends AbstractRandom

/**
 * Shorthand for Random.
 */
object R extends AbstractRandom