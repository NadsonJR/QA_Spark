I'll update the README.md to include the K6 load testing instructions:

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
src/
├── test/
│   ├── java/
│   │   └── org/desafio/
│   │       ├── logic/     # Test logic classes
│   │       ├── pages/     # Page objects
│   │       ├── runner/    # Test runner
│   │       ├── steps/     # Step definitions
│   │       └── utils/     # Utilities
│   └── resources/
│       └── org/desafio/features/  # Feature files
└── load-tests/              # K6 load test scripts
    └── test-script.js
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

## Features

- Login scenarios
- Checkout process
- Screenshot capture
- PDF report generation
- Load testing with K6
```