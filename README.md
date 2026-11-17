# Swag Labs Selenium Java + Cucumber BDD Framework

A complete, runnable, Page Object Model (POM) + Cucumber BDD automation framework targeting [Swag Labs](https://www.saucedemo.com/).

## Prerequisites
- **Java JDK 17** or higher installed (`java -version`)
- **Apache Maven** installed (`mvn -version`) or an IDE with built-in Maven support (IntelliJ IDEA / Eclipse / VS Code)
- **Google Chrome** (or Firefox/Edge) installed — Selenium Manager automatically resolves and downloads the matching browser driver.

## Project Structure
- `src/test/resources/config.properties` – Environment URL, browser, and headless execution flags.
- `src/test/resources/features/` – Data-driven Cucumber `.feature` files (`login.feature`, `inventory_and_cart.feature`, `checkout.feature`).
- `src/test/java/com/swaglabs/drivers/` – Thread-safe `DriverFactory`.
- `src/test/java/com/swaglabs/pages/` – Page Object classes (`BasePage`, `LoginPage`, `ProductsPage`, `CartPage`, `CheckoutPage`).
- `src/test/java/com/swaglabs/steps/` – Step definition classes mapped to Gherkin steps.
- `src/test/java/com/swaglabs/hooks/` – `@Before` and `@After` lifecycle hooks with automatic failure screenshots.
- `src/test/java/com/swaglabs/runners/` – TestNG Cucumber `TestRunner`.

## How to Run
1. Extract this ZIP archive and open a terminal inside the `swag-labs-cucumber-automation` folder (or open the `pom.xml` in IntelliJ IDEA / Eclipse).
2. Run all scenarios:
   ```bash
   mvn clean test
   ```
3. Run specific tags (e.g., `@smoke` or `@e2e`):
   ```bash
   mvn clean test -Dcucumber.filter.tags="@smoke"
   ```
4. View the generated HTML report after execution at:
   `target/cucumber-reports/cucumber-pretty.html`
