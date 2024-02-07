# Adpro Eshop

## Reflection 1

I thought my code was already clean and secure. However, there are a few things that made me work extra to ensure that 
my code is clean, because of the habit of not paying attention to variable naming according to the instructions and 
maybe the comments in my code are still not effective, because maybe for individual code work I already understand 
what I am doing with my code even though I have been working on it for a long time. Perhaps in the future, if there 
is group assignment work, I will pay more attention to commenting sessions in the code for my teammates in the group.

## Reflection 2

1. After writing the unit tests, I feel more confident in my code's reliability. There's no fixed rule for the number of unit tests in a class, but they should cover various scenarios adequately. Aim for high code coverage, but remember that 100% coverage doesn't guarantee bug-free code. It's crucial to focus on writing meaningful tests that cover different scenarios and to supplement them with code reviews and integration testing for comprehensive validation.
2. Creating a new Java class for a functional test suite to verify the number of items in the product list is a good organizational approach. However, it may lead to code duplication, unclear test case names, reliance on hardcoded test data, and potential test dependencies. To improve cleanliness:
   - Reduce code duplication by extracting common setup procedures and instance variables into a separate utility class or base test class.
   - Use descriptive test case names and comments to enhance clarity and maintainability.
   - Manage test data separately from test logic using externalized configuration files, database seeding, or mock objects.
   - Ensure test isolation by resetting the system state before each test case execution, either through setup and teardown methods or transactional rollback mechanisms. These improvements will enhance the cleanliness and quality of the new functional test suite, making it more maintainable and reliable.