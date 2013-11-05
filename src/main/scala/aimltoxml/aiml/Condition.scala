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
 * <condition>
 *    ...
 * </condition>
 */
class Decision(val conditions: Set[Condition], val defaultAnswer: TemplateElement = null) extends TemplateElement {
    require(conditions != null && !conditions.isEmpty)

    def toXml = {
        <condition>{
            conditions.map(_.toXml)}{
                if (defaultAnswer != null) <li>{defaultAnswer.toXml}</li>
            }</condition>
    }
  
  def canEqual(other: Any) = {
      other.isInstanceOf[aimltoxml.aiml.Decision]
    }
  
  override def equals(other: Any) = {
      other match {
        case that: aimltoxml.aiml.Decision => that.canEqual(Decision.this) && conditions == that.conditions && defaultAnswer == that.defaultAnswer
        case _ => false
      }
    }
  
  override def hashCode() = {
      val prime = 41
      prime * (prime + conditions.hashCode) + defaultAnswer.hashCode
    }

}

abstract class AbstractDecision{
    def apply(conditions: Set[Condition], defaultAnswer: TemplateElement = null) = new Decision(conditions, defaultAnswer)
}
/**
 * Decision companion object
 */
object Decision extends AbstractDecision


/**
 * <li name="gender" value="female">attractive.</li>
 */
class Condition(val variableName: String, val variableValue: String, val results: Set[TemplateElement]) {
    require(variableName != null && !variableName.isEmpty)
    require(variableValue != null && !variableValue.isEmpty)
    require(results != null && !results.isEmpty)

    def toXml = {
        <li name={ variableName } value={ variableValue }>{
            results.map { result =>
                result match {
                    case template: TemplateElement => template.toXml
                    case _                         => throw new IllegalArgumentException("Invalid result: \"" + result + "\".")
                }
            }
        }</li>
    }

    def canEqual(other: Any) = {
        other.isInstanceOf[aimltoxml.aiml.Condition]
    }

    override def equals(other: Any) = {
        other match {
            case that: aimltoxml.aiml.Condition => that.canEqual(Condition.this) && variableName == that.variableName && variableValue == that.variableValue && results == that.results
            case _                              => false
        }
    }

    override def hashCode = {
        val prime = 41
        prime * (prime * (prime + variableName.hashCode) + variableValue.hashCode) + results.hashCode
    }

}

abstract class AbstractCondition {
    def apply(variableName: String, variableValue: String, results: Set[TemplateElement]) = new Condition(variableName, variableValue, results)
}

object Condition extends AbstractCondition