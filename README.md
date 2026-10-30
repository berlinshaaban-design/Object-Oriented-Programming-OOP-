# Java OOP — Student Practice System

**Object-Oriented Programming | Java**

A Java-based academic project that models a student practice system with English and quantitative questions. The application loads questions from a text file, organizes them by difficulty, creates practice sessions, and tracks student grades and practice-related fees.

## Features

- Import questions from a tab-separated text file.
- Represent English and quantitative questions as distinct types.
- Organize questions by difficulty level.
- Generate practice sessions based on a student's level.
- Track student performance, grades, and practice counts.
- Compare students by performance and revenue-related metrics.

## Object-oriented design

| Concept | Implementation |
| --- | --- |
| Abstraction | `Question` is an abstract base class. |
| Inheritance | `EnglishQuestion` and `QuantitativeQuestion` extend `Question`. |
| Polymorphism | Subclasses override `getAdditionalInfo()`. |
| Encapsulation | Class fields are private and accessed through methods. |
| Interfaces and generics | `Comparable<T>` supports ordering; `getMax()` uses a bounded generic type. |
| Exception handling | `NotOnRangeException` handles invalid question levels. |
| Collections | Uses `ArrayList`, `List`, and `Vector`. |

## Main files

| File | Responsibility |
| --- | --- |
| [Application.java](Application.java) | Imports questions and manages students and practice sessions |
| [Question.java](Question.java) | Abstract question model and difficulty comparison |
| [EnglishQuestion.java](EnglishQuestion.java) | English questions with hints |
| [QuantitativeQuestion.java](QuantitativeQuestion.java) | Quantitative questions with formulas |
| [Student.java](Student.java) | Student information, grades, and practice records |
| [Practice.java](Practice.java) | Practice session logic |
| [NotOnRangeException.java](NotOnRangeException.java) | Custom validation exception |
| [questions.txt](questions.txt) | Question input data |

## Technologies

**Java · Object-Oriented Programming · Collections · Generics · File I/O · Exception Handling**

## Project notes

This repository contains academic source code and supporting Eclipse project files. It is a coursework implementation rather than a production application. Compiled `.class` files are also present; the `.java` files are the primary source for reviewing the implementation.
