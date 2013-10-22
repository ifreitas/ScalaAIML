package aimltoxml.aiml

import reflect.runtime.universe.TypeTag
import scala.collection.mutable.Set

class Topic(n: String, theCategories: Set[Option[Category]]) {
    require(n != null && !n.toString().equals(""))

    def toXml ={
    	require(!theCategories.isEmpty, "The Topic must have at least one Category.")
        <topic name={ this.name }>{
        theCategories.map { theCategory =>
            theCategory match {
                case Some(category) => category.toXml
                case _              => throw new IllegalArgumentException("Invalid Category: \"" + theCategory + "\".")
            }
        }
    }</topic>
    }
    
    def add(newCategory:Category*)={newCategory.map{category => this.theCategories.add(Some(category))};this}
    
    def categories = {theCategories}
    def name={this.n}
}

abstract class AbstractTopic{
	final def apply(name: String, categories: Set[Option[Category]])                           = { new Topic(name, categories) }
	final def apply(name: String, categories: Option[Category]*)                               = { new Topic(name, Set(categories: _*)) }
	final def apply(name: String, categories: Category*)(implicit ev: TypeTag[Category]):Topic = { new Topic(name, Set(categories.map{Some(_)}: _*)) }
}

/**
 * The Topic Companion Object.
 */
object Topic extends AbstractTopic

/**
 * Shorthand for Topic.
 */
object T extends AbstractTopic
