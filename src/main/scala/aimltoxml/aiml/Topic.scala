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

import reflect.runtime.universe.TypeTag

class Topic(n: String, theCategories: Set[Category]) {
    require(n != null && !n.toString().equals(""))

    val categories = theCategories
    val name = this.n

    def toXml = {
        require(!theCategories.isEmpty, "The Topic must have at least one Category.")
        <topic name={ this.name }>{
            categories.map { theCategory =>
                theCategory match {
                    case category: Category => category.toXml
                    case _                  => throw new IllegalArgumentException("Invalid Category: \"" + theCategory + "\".")
                }
            }
        }</topic>
    }

    override def toString = this.name

    def canEqual(other: Any) = other.isInstanceOf[aimltoxml.aiml.Topic]

    override def equals(other: Any) = {
        other match {
            case that: aimltoxml.aiml.Topic => that.canEqual(Topic.this) && that.name == this.name && that.categories == this.categories
            case _                          => false
        }
    }

    override def hashCode = 41 * this.name.hashCode + this.categories.hashCode
}

abstract class AbstractTopic {
    final def apply(name: String, categories: Set[Category]) = { new Topic(name, categories) }
    final def apply(name: String, categories: Category*): Topic = { Topic(name, Set(categories: _*)) }
    final def default = Topic("*")
}

/**
 * The Topic Companion Object.
 */
object Topic extends AbstractTopic

/**
 * Shorthand for Topic.
 */
object T extends AbstractTopic
