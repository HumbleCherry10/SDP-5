# Assignment #5 — Strategy Design Pattern

**Course:** ShP-2216 – Software Design Patterns  
**Institution:** Astana IT University  
**Student:** Dias  
**Topic:** Multi-Modal Route Navigation (Option A)

---

## 📌 Project Overview
This project implements the **Strategy behavioral design pattern** in Java 17. The system models a multi-modal navigation engine where the `Navigator` (Context) delegates route calculations to interchangeable strategies (`CarRouteStrategy`, `WalkingRouteStrategy`, `CyclingRouteStrategy`, `PublicTransitRouteStrategy`) behind a uniform `RouteStrategy` interface.

Key features:
- **Zero Conditional Logic:** No `if-else` or `switch` statements for transport modes in the Context.
- **Runtime Swapping:** The Client can seamlessly alter the active routing strategy dynamically during runtime.
- **High Extensibility:** New transportation algorithms can be plugged in without modifying existing code (Open/Closed Principle).

---

## 📂 Project Structure

```text
sdp-5/
├── src/
│   └── kz/
│       └── aitu/
│           └── strategy/
│               ├── context/
│               │   └── Navigator.java              # Context holding RouteStrategy reference
│               ├── model/
│               │   └── RoutePlan.java              # Immutable model representing computed route
│               ├── strategy/
│               │   ├── RouteStrategy.java          # Strategy interface
│               │   ├── CarRouteStrategy.java       # Concrete Strategy (Driving/Highways)
│               │   ├── WalkingRouteStrategy.java   # Concrete Strategy (Pedestrian paths)
│               │   ├── CyclingRouteStrategy.java   # Concrete Strategy (Bike lanes)
│               │   └── PublicTransitRouteStrategy.java # Concrete Strategy (Metro/Bus)
│               └── Main.java                       # Client demonstration with runtime switching
├── bin/                                            # Compiled bytecode
├── README.md                                       # Documentation & build instructions
└── REPORT.md                                       # Submission report for Moodle LMS
```

---

## 🚀 How to Build and Run

### Prerequisites
- JDK 17 (or compatible Java runtime)
- Terminal (PowerShell, Bash, or Command Prompt)

### 1. Compile the Source Code
```powershell
javac -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
```
*(On Unix / macOS / Git Bash: `javac -d bin $(find src -name "*.java")`)*

### 2. Execute the Application
```powershell
java -cp bin kz.aitu.strategy.Main
```

---

## 🧩 How to Add a New Strategy

Adding a new strategy (e.g., `ElectricScooterStrategy`) follows the **Open/Closed Principle**:

1. **Create a class implementing `RouteStrategy`**:
   ```java
   package kz.aitu.strategy.strategy;

   import kz.aitu.strategy.model.RoutePlan;

   public class ElectricScooterStrategy implements RouteStrategy {
       @Override
       public RoutePlan buildRoute(String origin, String destination) {
           return new RoutePlan(origin, destination, getModeName(), 7.5, 22, 1.20, "Use protected urban scooter lanes");
       }

       @Override
       public String getModeName() {
           return "Electric Scooter";
       }
   }
   ```

2. **Supply it to the Context (`Navigator`)**:
   ```java
   navigator.setStrategy(new ElectricScooterStrategy());
   RoutePlan scooterPlan = navigator.planRoute("AITU", "Khan Shatyr");
   ```

3. **No modification** to `Navigator`, existing strategies, or existing client logic is required!
