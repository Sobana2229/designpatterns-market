
# Design Patterns — Market Demo (Java)

This project demonstrates **six design pattern use-cases** across Behavioral, Creational and Structural categories:
- Behavioural: **Observer**, **Strategy**
- Creational: **Factory Method**, **Builder**
- Structural: **Adapter**, **Facade**

## What you will find
Each class is in its own file, packaged under `com.designpatterns.market`. The `App` class (entry point) demonstrates usage of each pattern through small, self-contained examples and defensive programming practices.

The app is configurable via `src/main/resources/config.properties` (e.g. simulation interval and run duration). A scheduled executor simulates market events instead of using `while(true)` loops.

## How to build & run locally
Requires Java 11+ and Maven.
```bash
# build
mvn -q -DskipTests package

# run
java -cp target/designpatterns-market-1.0-SNAPSHOT.jar com.designpatterns.market.App
```

## How to publish to GitHub
1. Create a new repo on GitHub.
2. Initialize git and push:
```bash
git init
git add .
git commit -m "Initial commit - design patterns market demo"
git branch -M main
git remote add origin https://github.com/<YOUR_USER>/<REPO_NAME>.git
git push -u origin main
```


