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
//        val aiml = A("greetings")
//        aiml.topic("*").
//            add(C("HI", R("Hello, there.", "Hi!"))).
//            add(C("HELLO", S("HI")))
//        println(aiml.toXml)
    }
}