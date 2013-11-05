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

/**
 * TODO: support for all TEMPLATE_EXPRESSION (https://docs.google.com/document/d/1wNT25hJRyupcG51aO89UcQEiG-HkXRXusukADpFnDs4/pub)
 */
class AimlSet(theVariableName: String, theVariableValue: TemplateElement) {
    val variableName = theVariableName
    val variableValue = theVariableValue
    require(variableName != null && !variableName.isEmpty)
    //require(variableValue != null && !variableValue.isValid)

    def toXml = <set name={ variableName }>{ variableValue.toXml }</set>

    override def toString = variableName + "=" + variableValue

    def canEqual(other: Any) = {
        other.isInstanceOf[aimltoxml.aiml.AimlSet]
    }

    override def equals(other: Any) = {
        other match {
            case that: aimltoxml.aiml.AimlSet => that.canEqual(AimlSet.this) && that.variableName == this.variableName && that.variableValue == this.variableValue
            case _                            => false
        }
    }

    override def hashCode() = 41 * (41 + this.variableName.hashCode) + this.variableValue.hashCode
}