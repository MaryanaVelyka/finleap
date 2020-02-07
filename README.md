# Finleap Case Study

## Log Analysis
The script to parse and count unique error log statements is located in `com.mvelyka.finleap.ErrorsCounter`.
The script parses only the first line of error statement. Stacktraces are omitted to simplify implementation.

### Running the script
1. `cd ../src/main/java/com/mvelyka/finleap/loganalysis`
2. `javac ErrorsCounter.java`
3. `cd ../src/main/java`
4. `java com.mvelyka.finleap.loganalysis.ErrorsCounter path/to/the/file/application.log`

or just simply run via your IDE providing path file as an argument.

### Result
Script run for file in `resources/application.log` returns 3 unique ERROR statements.

## Automation of the website
The website automation is implemented using Selenide framework which is Selenium wrapper.

### Implementation
Test framework has set of Page Object(s) implementation. There are 3 tests:
* Create User
* Update User
* Delete User

Create and Update user tests are running with success status. Delete test fails due to native popup.  

### Running the tests
Simply run `mvn clean test` from root folder.
 
## What could be improved
The task is timeboxed, so there is a room for improvement.

### Extending test suite
1. Login/Logout functionality:
    1. logging using wrong credentials
    2. validation messages on login form
    3. "Hello Luke" presence after login
2. Create user 
    1. validation for fields (optional/required, input types)
    2. cancel button on Create User step
    3. possibility to add duplications
3. Edit
    1. Back button
    2. Delete button
4. Delete
    1. Cancel on confirmation
5. Run tests in different browsers

### Code Quality improvements
1. Adding logging
2. Extracting common code blocks (duplications in selectors, common step tests)
3. Reports integration
4. Reading base url, credentials from properties that will allow to run tests on different environments
5. Figuring out the issue with native popup to fix Delete test 
 
 
 
