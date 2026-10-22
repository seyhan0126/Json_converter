# JSON Converter

## Overview

JSON Converter is a Java-based project developed as a programming exercise focused on working with JSON data.

The project uses several JSON-processing libraries and is built with Maven.

## Technologies

* Java 13
* Maven
* Jackson Databind
* org.json
* JSON.simple
* JUnit 4

## Project Structure

The repository follows a Maven-based project structure.

```text
Json_converter/
├── src/
├── pom.xml
└── README.md
```

## Prerequisites

To build the original project, you need:

* JDK 13 or a compatible JDK
* Apache Maven

## Installation

Clone the repository:

```bash
git clone https://github.com/seyhan0126/Json_converter.git
```

Navigate to the project directory:

```bash
cd Json_converter
```

Compile the project:

```bash
mvn clean compile
```

Run the tests:

```bash
mvn test
```

## Dependencies

The project includes the following libraries:

| Library          | Purpose                                |
| ---------------- | -------------------------------------- |
| Jackson Databind | JSON serialization and deserialization |
| org.json         | JSON object and array manipulation     |
| JSON.simple      | Lightweight JSON processing            |
| JUnit 4          | Automated testing                      |

## Project Background

This repository represents an earlier Java programming exercise.

It is preserved as part of a software development portfolio to demonstrate practical experience with Java, Maven, and JSON-processing libraries.

## Future Improvements

Potential improvements include:

* Updating the project to Java 17 or newer.
* Reviewing and consolidating JSON-processing dependencies.
* Migrating tests to JUnit 5.
* Adding more comprehensive unit tests.
* Improving input validation and exception handling.
* Introducing GitHub Actions for automated builds and tests.
* Expanding the documentation with input/output examples.

## License

No license has been specified for this repository.
