```markdown
# Web Automation Project

Automated testing project using Selenium WebDriver, Cucumber, Java, and K6 for load testing.

## Prerequisites

- Java JDK 11 or higher
- Maven
- Microsoft Edge Browser
- Edge WebDriver
- IntelliJ IDEA (recommended)
- K6 for load testing

## Setup

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Download Edge WebDriver:
   - Go to [Microsoft Edge WebDriver Downloads](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)
   - Download the version that matches your Edge browser
   - Extract the `msedgedriver.exe` to `C:\edgedriver_win64\`

3. Install K6:
   - Windows: `winget install k6`
   - MacOS: `brew install k6`
   - Linux: Follow [K6 installation guide](https://k6.io/docs/get-started/installation/)

4. Install dependencies:
```bash
mvn clean install
```

## Project Structure
```
project-root/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/desafio/
│   │           ├── config/     # Configuration files
│   │           ├── model/      # Domain models
│   │           └── utils/      # Utility classes
│   ├── test/
│   │   ├── java/
│   │   │   └── com/desafio/
│   │   │       ├── logic/     # Test logic classes
│   │   │       ├── pages/     # Page objects
│   │   │       ├── runner/    # Test runner
│   │   │       ├── steps/     # Step definitions
│   │   │       └── utils/     # Test utilities
│   │   └── resources/
│   │       ├── features/      # Cucumber feature files
│   │       ├── config/        # Test configuration
│   │       └── log4j2.xml     # Logging configuration
│   └── load-tests/           # K6 load test scripts
│       └── test-script.js
├── .github/
│   └── workflows/
│       └── maven.yml         # CI/CD configuration
├── target/                   # Compiled output
├── .gitignore
└── pom.xml                  # Project dependencies
```

## Running Tests

### Important
- Make sure you are in the project root directory (where `pom.xml` is located) before running any Maven commands
- Example: `C:\Users\nadso\Documents\Desafio\DesafioOutsera`

### Functional Tests
1. To run all tests with @Web tag:
```bash
mvn test
```

2. To run specific features, modify the tags in `TestRunner.java`:
```java
@CucumberOptions(
    tags = "@login"  // or @Checkout
)
```

### Load Tests
1. Run K6 test script:
```bash
k6 run src/load-tests/test-script.js
```

The load test includes:
- Ramp-up: 1 minute to 100 users
- Steady state: 3 minutes with 500 users
- Ramp-down: 1 minute to 0 users

Performance thresholds:
- 95% of requests must complete under 500ms
- Error rate must be below 1%

## Test Reports

### Important
- Make sure you have run the tests before generating reports
- To view the evidence of the executed tests, access the evidence folder.

After test execution:
1. HTML reports are generated in `target/cucumber-reports`
2. PDF evidence is generated for each test case
3. K6 load test results are displayed in the console

### Allure Reports
To view detailed test execution reports:

1. Generate and open Allure report in your default browser:
```bash
mvn allure:serve
```

## CI/CD Pipeline

Our project uses GitHub Actions for continuous integration and delivery. The pipeline:

1. Triggers on:
   - Push to main branch
   - Pull requests to main branch

2. Test Execution:
   - Sets up Java 11 and Edge browser
   - Installs Edge WebDriver
   - Installs K6 for load testing
   - Runs E2E tests with Maven
   - Runs load tests with K6
   - Generates Allure reports

3. Artifacts:
   - Cucumber HTML reports
   - Allure reports
   - PDF test evidences
   - JUnit test results

4. View Results:
   - Go to "Actions" tab in GitHub
   - Select latest workflow run
   - Download artifacts for detailed reports
   - View test summary in workflow details

## Features

- Login scenarios
- Checkout process
- Screenshot capture
- PDF report generation
- Load testing with K6
```