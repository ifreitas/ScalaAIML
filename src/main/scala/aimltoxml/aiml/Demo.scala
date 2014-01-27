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

//import scala.collection.mutable.Set

object Demo {
    def main(args: Array[String]) {

        // Playing with Categories
        
        // C("hi", "*", "Hello").toXml => <category><pattern>HI</pattern><that>*</that><template>Hello</template></category>
        // C("Israel", "What is your name", "Hello!").toXml => <category><pattern>ISRAEL</pattern><that>WHAT IS YOUR NAME</that><template>Hello!</template></category>
        
//        println(Category("hi", Set(Text("Hello"))).toXml)
//        println(C("hi", Set(Text("Hello"))).toXml) // => <category><pattern>HI</pattern><template>Hello</template></category>
//        println(C("hi", Set(Text("Hello"), Text("Hello"))).toXml) // Repeated 'Hello' => <category><pattern>HI</pattern><template>Hello</template></category>
//        println(C("hi", Set(Text("Hello"), Text(" there"))).toXml) // => <category><pattern>HI</pattern><template>Hello there</template></category>
//        println(C("HI", Set(R("Hello, there.", "Hi!"))).toXml)
//        println(C("Hey", Set(S("HI"))).toXml)

        println("Topic")
//        println(T("greetings", C("hi", Set(Text("Hello")))).toXml)
        
        Aiml("greetings",
        		Topic("*", 
        				C("HI", R("Hello, there.", "Hi!")),
        				C("HELLO", Srai("HI"))
        		)
        ).toXml

        <aiml version="1.0.1" xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:html="http://www.w3.org/1999/xhtml" xmlns="http://alicebot.org/2001/AIML-1.0.1">
            <topic name="*">
        		<category>
        			<pattern>HI</pattern>
        			<that>*</that>
        			<template>
        				<random>
        					<li>Hello, there.</li>
        					<li>Hi!</li>
        				</random>
        			</template>
        		</category>
        		<category>
        			<pattern>HELLO</pattern>
        			<that>*</that>
        			<template>
        				<srai>HI</srai>
        			</template>
        		</category>
        	</topic>
        </aiml>
        
        // Playing with AIML
//        val aiml = A("greetings")
//        aiml.topic("*").
//            add(C("HI", R("Hello, there.", "Hi!"))).
//            add(C("HELLO", S("HI")))
//        println(aiml.toXml)
    }
}