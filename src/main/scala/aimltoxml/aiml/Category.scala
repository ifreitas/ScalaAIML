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

import Constants.Asterisk

/**
 * @param thePattern
 * @param theThatPattern
 * @param theTemplateElements
 * 
 * About the that's default value:
 * (http://www.pandorabots.com/pandora/pics/wallaceaimltutorial.html)
 * Internally the AIML interpreter stores the input pattern, that pattern and topic 
 * pattern along a single path, like: INPUT <that> THAT <topic> TOPIC.  When the 
 * values of <that> or <topic> are not specified, the program implicitly sets the 
 * values of the corresponding THAT or TOPIC pattern to the wildcard '*'.
 */
case class Category(pattern: Text, templateElements:Set[TemplateElement], that:Text=Text("*")) extends Learnable{
	require(that    != null && that.hasContent, "The 'that' can not to be empty.")
    require(pattern != null && pattern.hasContent, "The pattern is required.")
    require(!templateElements.isEmpty, "The Category must have at least one Template Element. Example: Category(\"Hi\", null, \"Hello\").")
    
    def toXml = {
        <category><pattern>{pattern.content.toUpperCase}</pattern><that>{that.content.toUpperCase}</that><template>{
        	templateElements.map(_.toXml)
        }</template></category>
    }

}

abstract class AbstractCategory {
    final def apply(pattern: String, template: TemplateElement*): Category = { Category(pattern, template.toSet) }
}

/**
 * The Category Companion Object.
 */
object Category extends AbstractCategory

/**
 * Shorthand for Category.
 */
object C extends AbstractCategory
