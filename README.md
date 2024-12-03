# Monte Carlo Simulation
This repository contains a Java-based Monte Carlo simulation that models a share price initialised at £100. The application accepts and validates user inputs for the <b>Number of Steps (S)</b> and <b>Number of Walks (W)</b> in the simulation. The result is a table displaying the probability distribution of the final share prices.

## Requirements
To run this application, you need <b>Java 21</b> installed on your system.

* Download Java 21 from the official Oracle site: https://www.oracle.com/java/technologies/downloads/#java21
  
## How to Use
### Option 1: Running The Precompiled JAR File
1. Download the Monte-Carlo-Simulation-1.jar file
   * I have emailed a copy for easier access.
   * Alternatively, you can find the JAR file in this repository (in the ./dist directory)
2. Open a terminal and navigate to the directory where the JAR file is saved.
3. Run the application using the following command:
    ``` bash
    java -jar Monte-Carlo-Simulation-1.jar
    ```

### Option 2: Compiling and Running the Project Locally
1. Clone the repository:
   ```bash
   git clone https://github.com/JudeWallace/Monte-Carlo-Simulation.git
   ```
3. Navigate to the project in the terminal:
   ```bash
   cd Monte-Carlo-Simulation
   ```
5. To compile enter the following commands:
   * Note: This first command may not be necessary, as mvnw should already have the correct permissions. However, if it doesn't, this command will set them appropriately.
   ```bash
   chmod +x ./mvnw
   ```
    ```bash
   ./mvnw package
   ```
7. Now we have compiled the project we can run it using:
   ```bash
   java -jar target/Monte-Carlo-Simulation-1.jar
   ```
    
