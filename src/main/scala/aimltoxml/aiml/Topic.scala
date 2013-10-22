package aimltoxml.aiml

class Topic(name: String, theCategories: List[Option[Category]]) {
    require(name != null && !name.toString().equals(""))
    require(!theCategories.isEmpty, "The Topic must have at least one Category.")

    def toXml = <topic name={ this.name }>{
        theCategories.map { theCategory =>
            theCategory match {
                case Some(category) => category.toXml
                case _              => throw new IllegalArgumentException("Invalid Category: \"" + theCategory + "\".")
            }
        }
    }</topic>
}

object Topic {
    def apply(name: String, categories: List[Option[Category]]) = { new Topic(name, categories) }
    def apply(name: String, categories: Option[Category]*) 		= { new Topic(name, categories.toList) }
}