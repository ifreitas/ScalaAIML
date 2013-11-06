AimlToXml
=========

## [AIML](http://www.pandorabots.com/pandora/pics/wallaceaimltutorial.html)
Or Artificial Intelligence Markup Language, is an XML dialect for creating natural language software agents [[1](http://en.wikipedia.org/wiki/AIML)]. AIML is a widely adopted standard for creating chat bots and mobile virtual assistants like [ALICE](http://sheepridge.pandorabots.com/pandora/talk?botid=b69b8d517e345aba&skin=custom_iframe), [Mitsuku](http://www.mitsuku.com/), English Tutor, The Professor, S.U.P.E.R. and many more[[2](https://code.google.com/p/program-ab/)]. 

### AIML Interpreter
- [ProgramD](https://github.com/noelbush/programd) - Program D is the most widely used free ("open source") AIML bot platform in the world. It is the most feature-complete, best-tested implementation of the current AIML specification.
- [ProgramAB](https://code.google.com/p/program-ab/) - Program AB is the reference implementation of the AIML 2.0 draft specification.

### [Loebner Prize](http://www.loebner.net/Prizef/loebner-prize.html)
The Loebner Prize is an annual competition in artificial intelligence that awards prizes to the chatterbot considered by the judges to be the most human-like. The format of the competition is that of a standard Turing test. In each round, a human judge simultaneously holds textual conversations with a computer program and a human being via computer. Based upon the responses, the judge must decide which is which.

Loebner Prize for "most human computer" AIML [winners](http://en.wikipedia.org/wiki/Loebner_Prize#Winners):
- [Mitsuku](http://www.mitsuku.com/) (2013)
- [ALICE](http://sheepridge.pandorabots.com/pandora/talk?botid=b69b8d517e345aba&skin=custom_iframe) (2000, 2001 and 2004)


## [AimlToXml](https://github.com/ifreitas/AimlToXml)
Is an easy to use set of Scala objects representing the AIML tags for creating AIML xml files.

### Usage
(The definitions used bellow came from the [AIML Overview](http://www.alicebot.org/TR/2011/) page)

#### Category
> The basic unit of knowledge in AIML is called a category. Each category consists of an input question,an output answer, and an optional context. The question, or stimulus, is called the pattern. The answer, or response, is called the template. The two types of optional context are called "that" and"topic." The AIML pattern language is simple, consisting only of words, spaces, and the wildcard symbols _ and *. The words may consist of letters and numerals, but no other characters. The pattern language is case invariant. Words are separated by a single space, and the wildcard characters function like words.

The code:
```scala
Category("hi", "Hello").toXml
```
Becomes:
```xml
<category>
	<pattern>HI</pattern>
	<template>Hello</template>
</category>
```

The code:
```scala
// 'C' is a shorthand for Category.
C("hi", "Hello", "Hello").toXml // Repeated 'Hello'
```
(just one 'Hello'):
```xml
<category>
	<pattern>HI</pattern>
	<template>Hello</template>
</category>
```

##### Random
> The random element instructs the AIML interpreter to return exactly one of its contained li elements randomly. The random element must contain one or more li elements of type defaultListItem, and cannot contain any other elements.

```scala
// 'R' is a shorthand for Random.
C("HI", R("Hello, there.", "Hi!")).toXml
```
```xml
<category>
	<pattern>HI</pattern>
	<template>
		<random>
			<li>Hello, there.</li>
			<li>Hi!</li>
		</random>
	</template>
</category>
```

##### Srai
> The srai element instructs the AIML interpreter to pass the result of processing the contents of the srai element to the AIML matching loop, as if the input had been produced by the user (this includes stepping through the entire input normalization process). The srai element does not have any attributes. It may contain any AIML template elements.

```scala
// 'S' is a shorthand for Srai
C("Hey", S("HI")).toXml
```
```xml
<category>
	<pattern>HEY</pattern>
	<template>
		<srai>HI</srai>
	</template>
</category>
```

#### Topic
> A topic is an optional top-level element that contains category elements. A topic element has a required name attribute that must contain a simple pattern expression. A topic element may contain one or more category elements.

```scala
T("greetings", C("hi", "Hello")).toXml
```
```xml
<topic name="greetings">
	<category>
		<pattern>HI</pattern>
		<template>Hello</template>
	</category>
</topic>
```

#### AIML
> An AIML object must have a version attribute, indicating the version of AIML that the object requires. For this version of AIML, the version should be 1.0.1. When the value is not equal to 1.0.1, forward-compatible processing mode is enabled.

```scala
val greetings = A("greetings")
greetings.topic("*").
          add(C("HI", R("Hello, there.", "Hi!"))).
          add(C("HELLO", S("HI")))
greetings.toXml
```
```xml
<aiml version="1.0.1"
	xsi:schemaLocation="http://alicebot.org/2001/AIML-1.0.1 http://aitools.org/aiml/schema/AIML.xsd"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:html="http://www.w3.org/1999/xhtml"
	xmlns="http://alicebot.org/2001/AIML-1.0.1">
	<topic name="*">
		<category>
			<pattern>HELLO</pattern>
			<template>
				<srai>HI</srai>
			</template>
		</category>
		<category>
			<pattern>HI</pattern>
			<template>
				<random>
					<li>Hello, there.</li>
					<li>Hi!</li>
				</random>
			</template>
		</category>
	</topic>
</aiml>
```

---
