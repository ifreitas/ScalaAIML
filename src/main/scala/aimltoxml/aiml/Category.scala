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

import scala.xml.Elem
import scala.xml.Node
import scala.xml.transform.RewriteRule
import scala.xml.transform.RuleTransformer
import scala.collection.immutable.Set
import reflect.runtime.universe.TypeTag

class Category(thePattern: String, theTemplateElements: Set[TemplateElement]) {
    require(thePattern != null && !thePattern.trim().equals(""), "The pattern is required.")
    require(!theTemplateElements.isEmpty, "The Category must have at least one Template Element. Example: Category(\"Hi\", Some(Text(\"Hello\")))")

    val pattern = thePattern
    val templateElements = theTemplateElements

    def toXml = {
        <category><pattern>{ pattern.toUpperCase() }</pattern><template>{
            templateElements.map(templateElement => templateElement match {
                case template: TemplateElement => { template.toXml }
                case _                         => throw new IllegalArgumentException("Invalid Template: \"" + templateElement + "\".")
            })
        }</template></category>
    }

    override def toString = "pattern: " + pattern + "; template: " + templateElements.map(t => t.toString)

    def canEqual(other: Any) = other.isInstanceOf[aimltoxml.aiml.Category]

    override def equals(other: Any) = {
        other match {
            case that: aimltoxml.aiml.Category => that.canEqual(Category.this) && that.pattern == this.pattern && that.templateElements == this.templateElements
            case _                             => false
        }
    }

    override def hashCode = 41 * (41 + pattern.hashCode()) + templateElements.hashCode

}

abstract class AbstractCategory {
    final def apply(pattern: String, templateElements: Set[TemplateElement]): Category = { new Category(pattern, templateElements) }
    final def apply(pattern: String, templateElements: TemplateElement*): Category = { Category(pattern, templateElements.toSet) }
    final def apply(pattern: String, templateElements: String*)(implicit ev: TypeTag[String]): Category = { Category(pattern, templateElements.map { templateElement => Text(templateElement) }.toSet[TemplateElement]) }
}

/**
 * The Category Companion Object.
 */
object Category extends AbstractCategory

/**
 * Shorthand for Category.
 */
object C extends AbstractCategory
