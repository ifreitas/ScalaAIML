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

class Text(aText: String) extends TemplateElement with RandomElement with Equals {
    def toXml = new scala.xml.Text(content)
    def hasContent = content != null && !content.trim().equals("")

    val content = aText

    override def toString = { content }

    def canEqual(other: Any) = {
        other.isInstanceOf[aimltoxml.aiml.Text]
    }

    override def equals(other: Any) = {
        other match {
            case that: aimltoxml.aiml.Text =>
                that.canEqual(Text.this) &&
                that.content == this.content
            case _ => false
        }
    }

    override def hashCode() = {
        content.hashCode()
    }

}
object Text {
    def apply(aText: String) = { new Text(aText) }
}
