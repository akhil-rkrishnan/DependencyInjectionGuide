# The Complete DI Guide for Koin and Hilt

## What is Dependency Injection?
As we know that, **S.O.L.I.D** is one of the most significant acronyms in Oops concepts. Using SOLID principle in android development could be helpful and effective to follow the clean code principle. These five basic principles of oops design by Uncle Bob(Robert C. Martin).In SOLID, It is the fifth principle is Dependency Inversion. Dependency Injection is a design pattern used to implement inverse of control, meaning the flow of an application is inverted. We can create the dependent object outside of the class and provide those objects to the class in different ways. DI can help with, moving the creation and binding of the dependent objects outside of the class that depends on them.

## Koin
**The pragmatic Kotlin dependency injection framework**

Koin is a dependency injection framework for Kotlin. It is lightweight, can be used in Android applications, is implemented via a concise **DSL(Domain Specific Language)**, and takes advantage of Kotlin features like delegate properties rather than relying on annotations.

## Hilt
Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Doing manual dependency injection requires you to construct every class and its dependencies by hand, and to use containers to reuse and manage dependencies. **Hilt relys on annotation process.**

## Difference between Koin and Hilt

**Dagger Hilt** is a compile time injected library which means the dependencies of all of our objects will already be injected at the compile time, ie as soon as we click **Run** button it is already clear which dependency goes where. Because Dagger Hilt works a lot with annotations, annotation processing and generating some classes. These generated class provides the dependecy.

**Koin** is not a compile time injected library which makes it little slower, so it rather provides the dependecies on demand when we already run the code.So when we run it, it will figure out which dependency goes where.


