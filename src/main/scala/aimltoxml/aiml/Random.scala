package aimltoxml.aiml

import scala.xml.Node

trait RandomElement{
    def toXml:Node
}

class Random(theOptions: Set[Option[RandomElement]]) extends TemplateElement{
    require(!theOptions.isEmpty, "The Random object must have at least one option. Example: Random(Some(Text(\"Some text here.\")))")
    
    def toXml={
    	<random>{theOptions.map(theOption => theOption match{
    	    case Some(option) => <li>{option.toXml}</li>
    	    case _ => throw new IllegalArgumentException("Invalid Option to Random: \"" + theOption +"\".")
    	})}</random>
    }
}

abstract class AbstractRandom{
	final def apply(options: Option[RandomElement]*)      = { new Random(options.toSet) }
	final def apply(options: Set[Option[RandomElement]]) = { new Random(options) }
}

/**
 * The Random Companion Object.
 */
object Random extends AbstractRandom

/**
 * Shorthand for Random.
 */
object R extends AbstractRandom