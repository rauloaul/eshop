# Adpro Eshop
Application Link : https://eshop-rauloaul.koyeb.app/

## Tutorial 1 - Reflection 1

I thought my code was already clean and secure. However, there are a few things that made me work extra to ensure that 
my code is clean, because of the habit of not paying attention to variable naming according to the instructions and 
maybe the comments in my code are still not effective, because maybe for individual code work I already understand 
what I am doing with my code even though I have been working on it for a long time. Perhaps in the future, if there 
is group assignment work, I will pay more attention to commenting sessions in the code for my teammates in the group.

## Tutorial 1 - Reflection 2 

1. After writing the unit tests, I feel more confident in my code's reliability. There's no fixed rule for the number of unit tests in a class, but they should cover various scenarios adequately. Aim for high code coverage, but remember that 100% coverage doesn't guarantee bug-free code. It's crucial to focus on writing meaningful tests that cover different scenarios and to supplement them with code reviews and integration testing for comprehensive validation.
2. Creating a new Java class for a functional test suite to verify the number of items in the product list is a good organizational approach. However, it may lead to code duplication, unclear test case names, reliance on hardcoded test data, and potential test dependencies. To improve cleanliness:
   - Reduce code duplication by extracting common setup procedures and instance variables into a separate utility class or base test class.
   - Use descriptive test case names and comments to enhance clarity and maintainability.
   - Manage test data separately from test logic using externalized configuration files, database seeding, or mock objects.
   - Ensure test isolation by resetting the system state before each test case execution, either through setup and teardown methods or transactional rollback mechanisms. These improvements will enhance the cleanliness and quality of the new functional test suite, making it more maintainable and reliable.
   
## Tutorial 2 - Reflection 1
1. I don't face any code quality issues based on SonarCloud, Although there's one problem regarding the SonarCloud on the code coverage. In Jococo index, I've manage to achieve 99% code coverage. Although, in the SonarCloud there's 0% code coverage on ProductRepository file. Until now, I don't see a way to fix that.
2. I believe my setup aligns with the concept of CI/CD. Regarding Continuous Integration (CI), I've incorporated testing utilities like JUnit, OSSF Scorecard, and Sonarcloud to ensure seamless integration and validation of the updated codebase. As for Continuous Deployment (CD), I've established automated deployment to Koyeb through a Dockerfile.

## Tutorial 3 - Reflection 1
1. I have already implemented LSP, OCP, and DIP.
   - Liskov Substitution Principle (LSP) says that when you have a class and a subclass, you should be able to use the subclass wherever you can use the parent class, without messing up the program. In simple terms, it means the subclass should behave just like the parent class. This helps keep our code flexible and easy to work with.
   - Open-Closed Principle (OCP) means that once you've written a piece of code (like a class or function), you shouldn't have to change it every time you want to add new features or fix bugs. Instead, you should be able to extend its behavior without modifying its source code. Think of it like adding new parts to a car without having to redesign the entire engine. OCP helps keep our code stable and easy to maintain.
   - Dependency Inversion Principle (DIP) is about how different parts of a program should interact with each other. Instead of high-level parts depending directly on low-level details, they should depend on abstractions or interfaces. This means that changes in low-level parts won't directly affect high-level parts, making the program more flexible and easier to change.
   
   The rest of the SOLID principles (SRP and ISP) are already implemented, as I thought in the tutorial.

2. The advantage of applying SOLID principles is that the code that we're trying to develop can be worked/developed further by the other developer.

3. The disadvantage for not implementing SOLID principles is that it can be difficult to use and developed the code by other developers. Such as when I apply the OCP, where I change the UUID can be set. If it always forces that it always generates random UUID, then, it can only support UUID type id for the id.

## Tutorial 4 - Reflection 1
1. The TDD flow is useful in my opinion since it provides early bug detection and I already know what we want to get as the output from the module, so that we can minimize wrong output or some other mistakes.

2. The code I worked on has already implemented the F.I.R.S.T. Principle.