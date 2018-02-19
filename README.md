# ph-xpath2
The attempt to create a pure Java XPath2 parser and interpreter.

Current status: grammar looks good. Currently working on the transition from syntax nodes to a domain model.

# Maven usage
No Maven release of this artifact was published so far, so you need to use the source version. 

# Grammar
The grammar is defined for JavaCC and resides in `src/main/jjtree/ParserXP2.jjt`.
The Java code generation of the grammar happens with the [ph-javacc-maven-plugin](https://github.com/phax/ph-javacc-maven-plugin) Maven plugin.

# Building from source
Just run `mvn clean install` to get the current version build.
No external Maven repositories are needed.

# Relevant links

* https://www.w3.org/TR/xpath20/ XML Path Language (XPath) 2.0 (Second Edition)
* https://www.w3.org/TR/xmlschema-2/ XML Schema Part 2: Datatypes Second Edition
* https://www.w3.org/TR/xpath-functions/ XQuery 1.0 and XPath 2.0 Functions and Operators (Second Edition)


---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
On Twitter: <a href="https://twitter.com/philiphelger">@philiphelger</a>
