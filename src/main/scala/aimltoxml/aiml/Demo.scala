package aimltoxml.aiml

import scala.collection.mutable.Set

object Demo {
    def main(args: Array[String]) {
        
        // Playing with Categories
        println(Category("hi", "Hello").toXml)
    	println(C("hi", "Hello").toXml) // => <category><pattern>HI</pattern><template>Hello</template></category>
    	println(C("hi", "Hello", "Hello").toXml) // Repeated 'Hello' => <category><pattern>HI</pattern><template>Hello</template></category>
    	println(C("hi", "Hello", " there").toXml) // => <category><pattern>HI</pattern><template>Hello there</template></category>
    	println(C("HI", R("Hello, there.", "Hi!")).toXml)
    	println(C("Hey", S("HI")).toXml)
    	
    	println("Topic")
    	println(T("greetings", C("hi", "Hello")).toXml)
    	
    	// Playing with AIML
    	val aiml = A("greetings")
    	aiml.topic("*").
    		add(C("HI", R("Hello, there.", "Hi!"))).
    		add(C("HELLO", S("HI")))
    	println(aiml.toXml)
    }
}