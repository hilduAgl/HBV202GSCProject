# HBV202G Library System

A small yet complete **library‑management console application** built as the three‑week capstone for the HBV202G Software Construction course.  It demonstrates

* modular business logic with Java 21
* design patterns (Observer, Composite)
* Maven best practices (fat‑jar, site, reports)
* comprehensive JUnit tests
* Git collaboration workflow

---
## ✨ Features
* Add students or faculty members
* Add single books or multi‑volume *omnibus* (`CompositeBook`)
* Borrow/return books; observer notifications to the UI
* Faculty lending period extension

---
## 🚀 Quick‑start
```bash
# clone and enter the repo
git clone <repo-url>
cd HBV202GSCProject

# run the tests
mvn test

# build executable fat jar (tests + package)
mvn clean package

# launch via helper script
./run.sh
#   or directly
java -jar target/SCProject-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### Supported Maven goals
| Goal            | Purpose                                   |
|-----------------|-------------------------------------------|
| `mvn test`      | run JUnit test suite                      |
| `mvn exec:java` | start console UI (no packaging)           |
| `mvn package`   | build fat‑jar with dependencies           |
| `mvn site`      | generate HTML site + Javadoc + reports    |

---
## 🛠️ Project structure
```
src/
 ├─ main/java/is/hi/hbv202g/assignment8/  (application source)
 └─ test/java/is/hi/hbv202g/assignment8/  (JUnit tests)
UML/
 └─ library_system.uxf                 (class diagram – UMLet)
run.sh                                 (convenience launcher)
```

The full class diagram (including Observer & Composite relationships) is rendered in [`docs/design/uml.png`](docs/design/uml.png).

---
## 📐 Design patterns in use
| Pattern    | Classes / Role                                                                                    |
|------------|---------------------------------------------------------------------------------------------------|
| **Observer** | `LibrarySystem` (subject) ⇄ `LibraryObserver` interface; `LibraryUI` implements the observer      |
| **Composite**| `CompositeBook` (composite) contains multiple `Book` volumes and forwards `borrow()` calls

---
## 📄 Documentation site
Running `mvn site` produces an HTML report in `target/site/index.html` containing:
* Javadoc API docs
* Dependency graphs
* Surefire test results and coverage summary

---
## 📜 License
This project is released under the **MIT License**.  See [`LICENSE`](LICENSE) for details.

---
## 👥 Authors
* <student A>
* <student B>

---
**Happy reading – and enjoy the Library System!**

