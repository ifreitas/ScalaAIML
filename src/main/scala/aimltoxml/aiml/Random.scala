package aimltoxml.aiml

import scala.xml.Node

trait RandomElement{
    def toXml:Node
}

class Random(theOptions: List[Option[RandomElement]]) {
    def toXml={
    	<random>{theOptions.map(theOption => theOption match{
    	    case Some(option) => <li>{option.toXml}</li>
    	    case None => throw new IllegalArgumentException("The Random object must have at least one option. Example: Random(Some(Text(\"Some text here.\")))")
    	})}</random>
    }
}
object Random {
    def apply(options: Option[RandomElement]*) 		= { new Random(options.toList) }
    def apply(options: List[Option[RandomElement]]) = { new Random(options) }
}