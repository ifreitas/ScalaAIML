package aimltoxml.aiml

class Topic(name:String, categories:Option[List[Category]]){
	require(name!=null)
	require(!name.toString().equals(""))
	
	def toXml = categories match{
	case Some(theCategories) => <topic name={this.name}>{theCategories.map{category => category.toXml}}</topic>
	case None => <topic name={this.name}/>
	}
}

object Topic{
    def apply(name:String, categories:Option[List[Category]])={new Topic(name, categories)}
}