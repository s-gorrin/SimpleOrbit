# Simple Orbit Visualizer

A note about maven: The libraries and modules for JavaFX have been implemented with
maven, and everything compiles correctly on my machine. However, I have encountered
problems with `mvn javafx:run` and other maven runners. The project runs fine using
the run button in IntelliJ.

# How to compile the project

We use Apache Maven to compile and run this project. 

You need to install Apache Maven (https://maven.apache.org/)  on your system. 

Type on the command line: 

```bash
mvn clean compile
```

# How to create a binary runnable package 


```bash
mvn clean compile assembly:single
```


# How to run

```bash
mvn -q clean compile exec:java -Dexec.executable="edu.bu.met.cs665.Main" -Dlog4j.configuration="file:log4j.properties"
```

# Run all the unit test classes.


```bash
mvn clean compile test checkstyle:check  spotbugs:check
```

# Using Spotbugs to find bugs in your project 

To see bug detail using the Findbugs GUI, use the following command "mvn findbugs:gui"

Or you can create a XML report by using  


```bash
mvn spotbugs:gui 
```

or 


```bash
mvn spotbugs:spotbugs
```


```bash
mvn spotbugs:check 
```

check goal runs analysis like spotbugs goal, and make the build failed if it found any bugs. 


For more info see 
https://spotbugs.readthedocs.io/en/latest/maven.html


SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.


# Run Checkstyle 

CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style. 
You can change it to other styles like sun checkstyle. 

To analyze this example using CheckStyle run 

```bash
mvn checkstyle:check
```

This will generate a report in XML format


```bash
target/checkstyle-checker.xml
target/checkstyle-result.xml
```

and the following command will generate a report in HTML format that you can open it using a Web browser. 

```bash
mvn checkstyle:checkstyle
```

```bash
target/site/checkstyle.html
```

# Run all checks
Easily run spotbugs and checkstyle with
```bash
check.sh
```
or
```bash
check.bat
```

***

# Implementation

## Description
This program is a simple orbit visualizer. It contains a Sun, Planets, and Moons.
As one would expect, Planets orbit the Sun, and Moons orbit planets. These can
be set up directly in the code (see the `DemoPlanets` class), or eventually, in
a graphical user interface, in which a user can define parameters such as size,
speed, and orbital distance of the objects. Orbits are currently defined by simple 
circles, but it would not be hard to adapt the code to define elliptical orbits.
(To clarify: the code would not be complicated, the math is a different matter.)
The program is currently set up to display not-to-scale representations of Earth
and Mars, and can be run via the Main class.

## Non-Class Pattern
This project uses the Builder Design Pattern to create complex objects in a convenient
and streamlined way. The pattern accomplishes a few principles of good design:
* It encapsulates the process of creating a complex class in a single place.
* It contains the complicated logic of setting up the objects it builds, so that the code
in the class itself can be simpler and cleaner.
* It can make it easier and safer to create objects via a user interface by imposing
limits on attributes.
* It increases flexibility by providing an intuitive way to add more classes to the program.  

In addition to the Builder interface, there are two concrete Builder classes, which build objects
within the system: Planets and Moons. These builders make it easy to create instances
of these classes with a few reasonable values, and to get fully-features objects back out.

### Other Patterns
* The program contains two Singletons, which allow the Sun and Controller classes to be
accessed from anywhere in the program. They remain safe by not having any public methods which
could cause problems if they were accidentally called in the wrong places or times.
* 



