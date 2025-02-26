# 1.Polynomial Calculator

## Overview
This **Polynomial Calculator** is a Java-based application designed to perform fundamental polynomial arithmetic operations. The application follows **object-oriented programming (OOP) principles** to ensure a clean and efficient design. It supports multiple operations, including addition, subtraction, multiplication, division, differentiation, and integration of polynomials.

## Key Features
- **Polynomial Arithmetic:** Supports addition, subtraction, multiplication, and division.
- **Calculus Operations:** Implements differentiation and integration.
- **Input Validation:** Ensures correct polynomial formatting.
- **Graphical User Interface:** Simple and intuitive interface for user interaction.
- **Modular Design:** Follows the **MVC (Model-View-Controller)** pattern for scalability and maintainability.

## Usage
1. **Launch the Application:** Start the program to open the calculator interface.
2. **Input Polynomials:** Enter two polynomials in the designated fields.
3. **Select Operation:** Choose an operation from the available options:
   - **Addition**: Computes the sum of two polynomials.
   - **Subtraction**: Computes the difference between two polynomials.
   - **Multiplication**: Computes the product of two polynomials.
   - **Division**: Divides the first polynomial by the second, returning quotient and remainder.
   - **Differentiation**: Computes the derivative of the polynomial.
   - **Integration**: Computes the integral of the polynomial.
4. **Execute Calculation:** Click the corresponding button to perform the operation.
5. **View Results:** The output is displayed in the results section.
6. **Error Handling:** If an invalid polynomial format is detected, an error message is displayed.

## Design & Implementation

### System Architecture
The system follows the **Model-View-Controller (MVC) pattern**:
- **Model:** Represents polynomials and their operations.
- **View:** User interface for polynomial input and results display.
- **Controller:** Manages user input and invokes the necessary operations.

### Class Structure
- **Model Package:**
  - `Monomial`: Represents a single term of a polynomial.
  - `Polynomial`: Represents a polynomial as a collection of monomials.
  - `DivisionResult`: Holds the quotient and remainder of polynomial division.
- **Controller Package:**
  - `Operations`: Implements polynomial arithmetic and calculus operations.
  - `Validator`: Checks if user inputs conform to polynomial syntax.
  - `PolynomialParser`: Parses polynomial strings into structured objects.
  - `CalculatorController`: Coordinates interactions between the UI and model.
- **View Package:**
  - `CalculatorView`: Provides the user interface elements.

## Results
The polynomial calculator was tested using **JUnit 5**, and the results were validated for correctness. Example test cases and results:

| Operation     | Input 1         | Input 2         | Output                 |
|--------------|----------------|----------------|-------------------------|
| Addition     | `2x^3 + 4x + 5` | `3x^3 - 2x + 3` | `5x^3 + 2x + 8`         |
| Subtraction  | `5x^2 + 3x - 7` | `2x^2 - x + 4` | `3x^2 + 4x - 11`        |
| Multiplication | `x^2 + 2` | `3x - 4` | `3x^3 - 4x^2 + 6x - 8` |
| Division     | `x^3 - 6x^2 + 11x - 6` | `x - 2` | `Quotient: x^2 - 4x + 8, Remainder: -16` |
| Differentiation | `3x^2 + 5x + 2` | - | `6x + 5` |
| Integration  | `2x^2 + 6x + 9` | - | `2/3x^3 + 3x^2 + 9x + C` |

## Future Improvements
- **Enhanced UI:** Improve the graphical representation of polynomials.
- **Graph Plotting:** Visual representation of polynomials and their operations.
- **Database Storage:** Save and retrieve calculation history.
- **Extended Functionality:** Support additional polynomial operations and error handling improvements.

## References
- [Java Regex Patterns](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html)
- [JUnit 5 Testing Guide](https://www.baeldung.com/junit-assert-exception)
- [Polynomial Operations Reference](https://dsrl.eu/courses/pt/materials/PT_2024_A1_S1.pdf)



# 2.Queue Management Simulation

## Overview
This **Queue Management Application** simulates a multi-queue system where clients arrive, wait, get served, and leave. It leverages **multi-threading** and **synchronization mechanisms** to model realistic concurrency, ensuring safe and efficient execution.

## Key Features
- **Multi-Threaded Queues:** Each queue runs in its own thread.
- **Configurable Simulation:** Adjust the number of clients, queues, and time limits.
- **Live Visualization:** Watch real-time updates of queue evolution.
- **Statistical Output:** Automatically computes and displays final metrics.
- **Logging:** Saves simulation logs for later analysis.

## Usage
1. **Launch the Application:** Start the program to open the main menu.
2. **Configure Simulation Parameters:**
   - Set the number of clients.
   - Set the number of queues.
   - Define the maximum simulation time.
   - Specify arrival and service time ranges.
   - Choose a queue management strategy: **Shortest Queue** or **Shortest Time**.
3. **Run the Simulation:**
   - Observe real-time queue updates.
   - Monitor client arrivals, waiting, and service times.
4. **View Statistics:**
   - Peak hour analysis.
   - Average waiting time.
   - Average service time.
5. **Check Logs:**
   - Logs are saved in a text file for review.

## Design & Implementation

### System Architecture
The system follows an **MVC (Model-View-Controller)** pattern:
- **Model:** Represents clients, queues, and simulation data.
- **View:** Graphical user interface for user interactions.
- **Controller:** Handles simulation logic and user input processing.

### Class Structure
- **Client Class:** Defines client attributes (ID, arrival time, service time).
- **SimulationQueue Class:** Represents a queue, managed as a thread-safe structure.
- **QueueManager Class:** Manages queue assignments and client distribution.
- **SimulationManager Class:** Runs the simulation logic and tracks metrics.
- **View Classes:** Provides user interface elements for setup and real-time updates.
- **Strategy Classes:** Implements queue allocation strategies (**Shortest Queue**, **Shortest Time**).

## Results
The simulation generates various statistics and logs. Example results include:

| Simulation Run | Peak Hour | Avg Waiting Time | Avg Service Time |
|---------------|-----------|-----------------|-----------------|
| Example 1     | 5         | 3.2s            | 5.4s            |
| Example 2     | 8         | 4.5s            | 6.1s            |
| Example 3     | 6         | 3.8s            | 5.7s            |

## Future Improvements
- **Improved UI Design:** Enhance the visualization with graphical representations.
- **Database Integration:** Store and retrieve simulation histories for analysis.
- **User Authentication:** Implement user accounts to track individual simulations.
- **Custom Simulations:** Allow users to define unique simulation rules.

## References
- [Java Concurrency Tutorial](http://tutorials.jenkov.com/java-concurrency/index.html)
- [Understanding Thread Dumps](https://product.hubspot.com/blog/understanding-thread-dumps)
- [Java Threads Overview](https://www.geeksforgeeks.org/java-threads/)
- [Oracle Java Documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)

