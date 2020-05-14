# Java Classes and Methods 
Java is an object oriented programming language.  Through many of the lessons so far, classes have been used, demonstrated, and briefly discussed. But this module will go deeper into classes and their usage.  The following list is an outline of how this lesson will progress.  

* Class usage and the main method.
* Definition of a package.
* Using classes provided by the Framework.
* How to find documentation on the Framework provided classes.
* How to create custom classes.
* standards and best practices when creating custom classes.


The primary use of a Java class so far has been to provide a home for the `main()` method.  In fact, everything in Java (except for primitive data types) is contained in a class.  The class with `main()` just happens to be a special class and the the `main()` method is the "entry point", or "starting point" of a program.  But `main()` *must* be defined inside of a class.  The following simple class definition will be explained in more detail below.
```java
package com.tts;

public class MainFile 
{
    public static void main(String[] args) 
    {
        System.out.println("Hello World!");
    }
}
```
This class demonstrates the very basics of a class and package, and also will demonstrate the relationship between classes, packages, and files.  In Java there is a one-to-one hierarchical relationship between the file on disk and the package/class.  For example, many Java environments consider a directly called `src` to be the *root* location of a project.  If using the command line and `javac` to compile, this would be wherever the `javac` command is executed.  In the following discussion we will assume that `src` is the root folder.  

Since the above file is declared in the `com.tts` package and the name of the class is `MainFile`, the folder structure will be layed out as shown below:
```
src
   |
   -> com
         |
         -> tts
              |  MainFile.java
```
These are the important take-aways:
* The folder structure on disk is the same as the package declaration.  
* The file name is the same as the class name.
* In the file itself, the package name must be the first statement.

This package-folder structure will be very evident when creating our own applications. As applications become larger and more complicated it is a best practice to separate concerns into classes and group similar classes into packages.  Custom classes will be shown a bit later, but the grouping of similar classes into packages is the main form of organization in the Java class library, also known as the Java Development Kit or JDK.

## Java JDK Classes and Packages

In the Java JDK, packages are used to group similar classes together. For example, in the module on Data Types the wrapper classes such as `Double`, `Integer`, and `Character` were discussed; these are all part of the `java.lang` package. Classes that deal with input/output are part of the `java.io` package. There are many classes and packages in the JDK and many more are added with each release. As an example of how large the standard library can be, in Java JDK 1.8 there are approximately 217 packages and 4240 classes.  

To access a class in a package, it has to either be imported or fully referenced directly by its fully qualified class name.  For example, `java.lang.Integer` or `java.io.File`. By convention package names are lower case and classes are capitalized. So how was it that in the previous lab examples that the code was able to just use `Integer` directly?  

As it turns out, the `java.lang` package is so fundamental that it is 'imported' by default into every Java file, so its classes are available in every class with having to import it or expressly reference it.  All other JDK packages however must be explicitly imported.   

**Import**  
To import a class, the keyword `import` must be used after the package statement and before the class declaration. There are two forms - importing all the classes from a package, and importing a single class.  Although importing *all* classes in a package is legal (using the `import packageName.*` syntax) it is highly discouraged. It is much cleaner and more readable to import packages explicitly. For example the following class makes use of the `List` interface and the `ArrayList` class, both of which are in the `java.util` package. But rather importing the entire package it is better to simply import only the classes being used.
```java
package com.tts;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainFile 
{
    public static void main(String[] args) 
    {
        ArrayList<String> arrList = new ArrayList<>();
        LinkedList<String> llList = new LinkedList<>();
        // Use the list
        System.out.println("Hello World!");
    }
}
```
The syntax for the actual declaration, and using a generic list, will be discussed in another lesson.  For now the focus is on the two import statements for the two seperate classes in the same package.  

One thing to note - Some languages, such as C# or C++, refer to this concept as *namespaces* rather than packages. And besides organization of similar classes, an additional purpose of packages is to prevent ambiguity when there are classes of the same name in different packages. This can be important as some IDEs offer an *auto-import* feature and the programmer has to ensure the IDE imports the correct package.   
For example, when using Eclipse, typing in the `List<String>` statement above will produce an error; hovering over the error will allow the IDE to pop up a suggestion to import the `java.util.List` class.  Select this suggestion and the IDE will automatically add the `import` line to your file.  This is fine as long as there is only one `List` class available on the classpath.  However sometimes there may be more than one suggestion such as `java.util.List` and `spring.org.List` and it is up to the programmer to ensure the desired one is imported.   

Packages can also be hierarchical.  So the `java.util` packages can have its classes such as `List`, `ArrayList`, etc., but it can also have subpackages such as `java.util.concurrent`, `java.util.logging`, `java.util.zip`, and others.  Each package can have classes and subpackages as well.  

#### Using Classes
So with an explanation of packages and their relationship to classes, this begs the question, why use classes other than to contain `main()`?   

Without getting ahead of ourselves and going into Object Oriented Programming (which will be coming up in a future lesson), classes are meant to represent *things* - things that have properties and actions. It has been shown that the `Integer` class represented a primitive value and had operations defined for it.  The `String` class has also been discussed - it can be throught of as an array of characters.  And it has several operations defined for it - `charAt()`, `concat()`, `equals()`, etc.  

More complex ideas can also be represented as classes. For example, in the code snippet above the `ArrayList` class is used. This class represents a collection of items in an array-like list.  Examining the documentation for ArrayList at the [Official Java Docs](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) the ArrayList class has several operations defined for it.  This allows the user of an ArrayList to perform various operations on it.  The following snippet documents some of those operations.

```java
// create an empty list of strings.  The <> syntax will be clarified later
ArrayList<String> theList = new ArrayList<>();  
theList.add("First");   // add an element to the list
theList.add("Second");  // add another element to the list
theList.size();         // returns the value 2
String val = theList.get(1);  // returns the 2nd element "Second"
```
Other methods also exist for operations that would typically be performed on a list:
* `int indexOf(Object o)` returns the index of the given value if it is in the list
* `void clear()` removes all elements from the list
* `boolean isEmpty()` returns true if the list contains no elements
* `boolean remove(Object o)` removes the first element that matches o

And many more.  Recall from the previous lesson on types that there are both *instance* methods and *static*, or *class* methods.  Methods of a class will be discussed soon.

As mentioned above, there are thousands of classes in the Java JDK. Using them, as shown above, is done by creating a variable and assigning that variable with an instance of the class using the keyword `new`.  This is known as *instantiating* an object of the class.  The `new` keyword is special in that it invokes a special function in the class known as a *constructor* (more about this special function will be discussed soon).  This is also the key difference between primitive types (which were discussed in an earlier lecture) and reference types, such as classes.  The `new` keyword, in conjunction with the constructor, allocates enough memory to represent an instance of a class, and the variable is simply a *reference* to that memory. The dot syntax `variable.method` is used to reference members of an object.   

**The null value**  
There is a special value that represents when an object varible doesn't reference anything. That value is `null` and literally means 'nothing'. Sometimes when in object is declared it is given the value `null` to signify that it is uninitialized.  It then has to be set before it is used.  This value can also be checked to ensure that the variable actually refers to an object before calling an instance method.  If not an error condition occurs.  The following snippet shows an example of this logic:
```java
ArrayList<String> someList = null;

// some other code here, may or may not assign a reference to list
//..
//..

if (someList != null)
{
    System.out.println("number elements: " + someList.size())
}
else
{
    System.out.println("List is still uninitialized.");
}
```


Before a discussion of user-defined classes are undertaken, review the following items that have been discussed so far:
* The Java JDK contains thousands of built-in classes.
* The classes are organized into packages.
* By convention package names are lower case and classes are capitalized.
* To use classes, either the fully qualified name can be used or a short name once the package is imported.
* Instantiating a class is done with the `new()` operator and invokes a class constructor method.  
* An instance of a class is called an object, and is a reference type because the variable refers to a larger piece of memory.
* The dot syntax is used to reference members of a class in an object.
* The `null` value represents an object variable that is empty and references no object.


## User Defined Classes
Now that the JDK and its packages and classes have been discussed, if only briefly, the concept of user-defined classes can be shown.  This is really the heart of the Java language - allowing the programmer to model real-world concepts as classes.  A full discussion of all the object-oriented features of Java will be in an upcoming lesson, but the basis for all of that is the user-defined class.  

Thus far the only syntax for defining a class that has been shown is a simple structure for housing the `main()` method. Now that concept will be expanded on to show a fully functional class and how it it can be used. First, remember that a class models a *thing*, such as a Car.  And a Car has both attributes, aka properties, and actions, aka methods.  So, how would a blue 4-door car that can start, drive, and stop be modeled in a Java class?  As follows:
```java
// class definition
public class Car
{
    public String Color;
    public int NumberOfDoors;

    public void start()
    {
        System.out.println("Car starting");
    }

    public void stop()
    {
        System.out.println("Car stopping");
    }

    public void drive()
    {
        System.out.println("Car driving");
    }
}

// instantiating and using a car object
Car car = new Car();
car.Color = "blue";
car.NumberOfDoors = 4;
car.start();
car.drive();
```
Even though this is an extremely over-simplified definition (merely printing status messages for each method) the idea and usage should be clear. The class `Car` acts as a model for a real-world entity.  It has attrbutes `Color` and `NumberOfDoors`, and it can do three actions, which are modeled by the three methods. So when using the class, an instance of the class is creating using `new`, the attributes are set, and the methods are called to perform actions.  The class itself and the properties and methods are marked with the access modifier `public`, meaning that anyone can use this class and can call all the methods and access the preperties.

Several things can be expanding on, the first being the question - where is this magical *constructor* function that was mentioned earlier and that is called with the `new Car()` line is executed?  

It turns out that if the programmer doesn't provide *any* constructors, a default *no argument* constructor is added by the compiler.  It will set all properties to their default value - number types will be set to 0 and reference types to null.  If however the programmer provides *any* constructor, then the compiler will not provide even the basic one and the programmer must provide it as well. The programmer is free to set the values to what are considered appropriate defaults.  This is true of the other constructors as well - any value not explicitly passed in can be given a suitable default.

So if there is a *no* argument constructor, that must mean there are constructors that take arguments, correct?  Yes, this is one way to guarantee that the object is created with a known state at the very beginning.  For example, in the code above a plain car was instantiated and then, using properties, the attributes were assigned.  This can also be done with a constructor that accepts arguments.  Thus the class definition is expanded to look like the following:
```java
public class Car
{
    public String Color;
    public int NumberOfDoors;

    // no-arg constructor
    public Car() 
    {
        Color = "White";
        NumberOfDoors = 2;
    }

    // constructor with arguments
    public Car(String color, int doors)
    {
    	this.Color = color;
    	this.NumberOfDoors = doors;
    }

    // constructor with a single argument
    public Car(String color)
    {
    	this.Color = color;
    	this.NumberOfDoors = 4;
    }

    // Other methods remain as they were
}

// instantiating with the new constructor
Car car = new Car("blue", 4);
car.start();
car.drive();
```
Notice a couple of things about the constructor.  First, compared with the other methods in the class, it has no return value specified.  This is actually a requirement of the language - a constructor cannot return a value. Second, notice that there are two methods with the same name, `Car()`, but with different argument lists.  This is known as *overloading* and will be discussed further in the section on methods.  For now just know that this is perfectly legal as long as the argument lists are different.  In this case one method has no arguments while the other has two. This provides flexibility in the way that a `Car` can be instantiated. Sometimes the no-argument constructor will be used, other times the version specifying `color` and `doors` is more appropriate. Finally, inside the second constructor the properties are referenced with the keyword `this`.  While technically not necessary here, it is a convention to use `this` in constructors when referring directly to properties.  `this` is a keyword that refers to *the current object* and is commonly used in methods, especially if the name of the parameter is the same as the name of the property. 

#### Access Modifiers
One important aspect of a class is encapsalation. It was previously mentioned that because of the modifier `public`, any code using our `Car` class had the ability to call all the methods and access the properties directly.  Implementing encapsalation will alter that and help to define what users of a  `Car` can and can not do.  

The first thing to control with encapsalation is generally the properties. It is a best practice to make the actual properties `private` and provide outside users access only via *accessor methods*. These may also be referred to as *getters* and *setters*, the reason being that the by convention start with the prefixes `get` and `set`.  `Private` means that only internal methods of the class can directly use property; users of the class can only see/use whatever is marked `public`. 

The reasons to do this can be many. The range of values for a property may be restricted; having a `set` method check and validate the value on the way in may be important. If only a `get` method is provided the property essentially becomes read-only. In advanced scenarios the `get` or `set` methods may both be defined but access to them is controlled by some external security factor.  

With the modifiers changed and the accessor methods defined, the class now is getting a little larger.  And it also should be obvious now that users have to go through the `getNumberOfDoors` and `setNumberOfDoors` methods to change those properties on the object, and that `numberOfDoors` has to be between 1 and 6 to be valid.  
```java
public class Car 
{
    // change case - convention that member variables are camel case.
    private String color;
    private int numberOfDoors;

    public String getColor() 
    {
        return color;
    }

    public void setColor(String color) 
    {
        this.color = color;
    }

    public int getNumberOfDoors() 
    {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) 
    {
        if ( numberOfDoors >= 1 && numberOfDoors <= 6)
            this.numberOfDoors = numberOfDoors;
        else
            this.numberOfDoors = -1;
    }

    // no-arg constructor
    public Car() { }

    // constructor with arguments
    public Car(String color, int doors)
    {
    	this.setColor(color);
    	this.setNumberOfDoors(doors);
    }
    
    public void start()
    {
        System.out.println("Car starting");
    }

    public void stop()
    {
        System.out.println("Car stopping");
    }

    public void drive()
    {
        System.out.println("Car driving");
    }

}
```
Java has four different levels of modifiers. The table below lists and describes each one.  
| Modifier          | Description       |
|-----------        | ----------        |
| public            | Available to all.  |
| private           | Available to only members of the same class.  |
| (None)            | Known as default or package visibility.  Other members of the same package can access it, but members in other packages cannot. |
| protected         | Avaiable to members of the same package and any derived classes.  |


A property or method can have any of the modifers, but a `class` can only have `public` or none (default).   
Now with the different constructors and accessor methoded defined, using the `Car` class would look something like this:
```java
Car sporty = new Car("Red", 2);
sporty.start();
sporty.drive();

Car family = new Car();
family.setColor("Gold");
family.setNumberOfDoors(4);
family.start();
family.drive();
```
Before the class is expanded again some conventions will be explained. Note that these are only *conventions*, not actual language rules, and an organization's policies or coding standards my differ.  They exist mainly for making a class more readable and maintainable.  
First - the arrangement of the class.  Member variables and constants (which we will see shortly) appear at the beginning of the file.  These are generally grouped according to purpose (member or constant) and within each of those groupings, by visibility modifier.  
Next - methods. The order of these can sometimes be different, but these are also grouped together by purpose and visibility.  In the `Car` class above, the accessor methods appear in pairs immediately after the member variables.  This proximity can be advantageous. Following the accessors, the constructors are grouped together, followed by the public methods of the class.  If there were any private methods or static methods, these would also be grouped together.  
Again, there is no compiler rule that enforces this arrangment or grouping. In some cases the private members are placed together at the bottom of the file with the logic being that they are not `public` and are changed or updated far less than their public accessor methods, which would be at the top of the file.  Unlike a function, where a variable must be declared *before* it is used, the compiler sees the class in a holistic manner and class members, whether functions or variables, can theoretically appear anywhere in the class.   

### Private vs. Public methods
In the simple `Car` class there are no `private` methods, only `private` member variables.  So why would there be methods also declared as `private`?  Again, this goes a little bit into object-oriented programming, but the `public` methods of a class should provide the user of the class the ability to interact and/or modify the object in predictible ways.  And if there are operations that the object should only perform on itself, those should be declared `private`.   

One example, as has been mentioned before, is a read-only property.  Assume the car class has expanded to not only drive, but to have `speed` property and both the `get` and `set` accessors.  Also, there is a `currentRPM` that represents the speed of the engine, which is calculated from the speed of the car (a public property) and some internal factos such as gears, weight, and size of engine.  The designer of the `Car` class would most likely want this to be a read-only property implemented as follows:
```java
private int currentRPM;
private int speed;

public int getCurrentRPM()
{
    return currentRPM;
}

private void setCurrentRPM(int rpm)
{
    currentRpm = rpm;
}

public void setSpeed(int speed)
{
    this.speed = speed;
    setCurrentRPM(calculateRPM(speed));
}

```
In this case, the RPM is set indirectly when the speed is set, but the implementation is hidden from the user who sets the speed.  Incidently, this also demonstrates the use of a private method.  The `calculateRPM()` method would also be declared as `private` so external users would not have access to it.
```java
private int calculateRPM(int speed)
{
    int rpm = 0;

    // represents a real rpm calcuation
    rpm = speed * getBestGear(speed) * engineFactor();

    return rpm;

}
```
### Overloading
Earlier the term 'overloading' was mentioned in the context of constructors and basically was defined as the same method with different pararmeter lists.  This principal can also be applied to non-constructor methods.  It is used quite extensively in the JDK itself and exists mainly to give the user of the class options when manipulating an object.  If the `Car` class grew to include more functionality, there could be several overloads of the `drive()` method. An example of this is shown below
```java
// Just start driving
public void drive()
{
    System.out.println("Car driving");
}

// Drive for the specified number of miles
public void drive(int miles)
{
    System.out.println("Driving for " + miles + " miles.");
}

// Drive specified miles at the specified speed
public void drive(int miles, int speed)
{
    System.out.println("Driving " + speed + "mph for " + miles + " miles.");
}

// drive to the specified destination
public void drive(String destination)
{
    System.out.println("Driving to " + destination);
}
```
Again, the purpose of overloaded methods is to provide the user of the class with multiple options when implementing similar ideas.  The alternative would be to have methods with slightly different names - `drive`, `driveForDistance`, `driveDistanceAtSpeed`, and `driveToDestination`. While this *could* work and may in some cases be more descriptive, the concept of overloading remains a powerful way to present a common idea in a concise manner.  
The key rule is that the parameter lists *have* to be different for the methods to be overloaded. Or the parameters have to be in a different order.  That is legal but not generally recommended due to possible confusion. The `return` type isn't taken into consideration.

### Class Methods and Constants
Thus far all the methods and variables have been *instance* methods and variables, meaning that each instantiated object has their own separate copy.  The `sporty` Car was Red and the `family` car was Gold for instance.  Each one can `start()` or `drive()` independantly of the other.  
Often, a class will declare values that it can use and/or expose.  For instance, it is considered bad practice to have hard-coded 'magic' numbers, such as `6` in the code above. A much better way is to declare constants.  The following snippet shows how to declare a few constants, both public and private, and to use them.
```java
public class Car 
{
    // private (internal) constants
	private static final int MAX_DOORS = 6;
	private static final int MIN_DOORS = 1;
	
    // public (external) constants
	public static final String TYPE_SPORTY = "Sporty";
	public static final String TYPE_FAMILY = "Family";
	
    // a new property and its respective accessors
    private String carType;
	
    public String getCarType() 
    {
        return carType;
    }
    public void setCarType(String carType) 
    {
        this.carType = carType;
    }
    // rest of class the same with the exception of setNumberOfDoors
    public void setNumberOfDoors(int numberOfDoors) 
    {
        if ( MIN_DOORS >= 1 && numberOfDoors <= MAX_DOORS)
            this.numberOfDoors = numberOfDoors;
        else
            this.numberOfDoors = -1;
    }
}

Car fam = new Car("White", 4)
fam.setCarType(Car.TYPE_FAMILY);
```
In Java, class 'constants' are typcially declared as `static final` and are initialized when they are declared.  As per the visibility modifiers discussed earlier, the `private` constants are only visible inside the class, and the public ones are used outside the class.  They are required to be accessed outside the class using both the class and property name - `Car.TYPE_SPORTY`.  Notice that the constants are declared as all caps with underscores between the words.  Again, this is a *convention* and not enforced by the compiler.

Although the `final` modifier is what makes the value not able to be changed, the `static` modifier is quite important as well.  The `static` modifier means that the value is a class-level member, not an instance member.  This is why it has to be referenced through the class - `Car.TYPE_FAMILY` and not through an instance such as `fam.TYPE_FAMILY`.  This will save on memory because instead of each instance having this value there will only be one copy of `TYPE_FAMILY` shared amonst all instances of the class.   
The `static` modifier can also be applied to methods. Because this makes the function a "class-level" function it is also referenced through the class.  The caveat is that a static function can only access other `static` members of the class. Given that limitation there are still certain cases where `static` functions make sense.  The reader is encouraged to go back and review the `static` methods of the wrapper classes to see how they are defined and what their purpose is in those scenarios.  

For the `Car` class, an example might be if the ability to compare two cars based solely on the number of doors they had.  This *could* be done with an instance method, but it could also be implemented with a `static` method as follows:
```java
public static boolean areDoorsEqual(Car c1, Car c2)
{
    return c1.getNumberOfDoors() == c2.getNumberOfDoors();
}

// And this could be used as follows
boolean doorsTheSame = Car.areDoorsEqual(family, sporty);
```
Another reason might be to define a "utility" method that is applicable to the `Car` class in general and maybe doesn't need to be on each and every instance.  For example, say there is another attribute added to `Car`, a `grossWeightInLbs` member with its accompying accessors.  A utility method might be to convert the weight to kilograms:
```java
public static int convertGrossWeightToKGs(Car c)
{
    double kgs = c.grossWeightInLbs/2.2;
    return (int)kgs;
}
```


### Object Base Class and Overrides
At the risk of encroaching on the object-oriented topic, it should be noted that every user-defined class, once it is compiled, is actually a child class of the `java.lang.Object` class. In fact, every class in Java, even those defined by the JDK, are children of `Object`.  The reasoning behind that is beyond the scope of this course, but the important thing to realize is this gives every class some common behavior represented as methods.  The two most common are `toString()` and `equals()`.  It is possible to "override" these in the user-defined classes to have our classes exhibit consistent behavior.  
The term "overriding" means that a method that is defined in a class' parent type can be redefined with behavior that is desirable in the child.  Again, not wanting to step all over the object oriented lecture, but using the two methods from the previous paragraph will be a good example without having to explain too much of the OO theory.  

In the `Object` class, the `toString()` method returns "a string representation of the object."  But what exactly does that mean, it is surely different for each class?  By default, the `Object` `toString()` method returns the name of the class with a few other symbols and a hash code. This method is called any time an object needs to be converted to a `String`. This default implementation in general is not very useful as it looks something like this when called on a `Car` object:
```java
System.out.println(fam);

com.tts.Car@15db9742
```
This is obviously not very informational. The compiler tries to find a way to print the object, so it does so by internally calling the object's `toString()` method.  To override and provide a useful implementation looks like this:
```java
@Override
public String toString() 
{
    return String.format("This %s car has %d doors and is %s", 
            this.getCarType(),
            this.getNumberOfDoors(),
            this.getColor());
}

// this looks like:
System.out.println(fam);

This Family car has 4 doors and is Blue
```
The `@Override` statement is known as an annotation. These will be discussed in more detail in a later lesson. For now it is enough to know that it should be declared on methods that are overridden to help the compiler know that this is an overridden method.  Other than that the method is a normal method implementation.  

The other common method to override is `equals()` to test the equality of two objects. Remember that user-defined objects are reference types. And as was discussed in the types lecture, reference types are only equal if they refer to the same object.  So the following code actually produces the same value of `false`.
```java
Car car1 = new Car("Blue", 2);
Car car2 = new Car("Blue", 2);

System.out.println("Do they point to the same: " + (car1 == car2));
System.out.println("Are they equal: " + car1.equals(car2));

// The output is:
Do they point to the same: false
Are they equal: false
```
So a very simple implementation of the `Car equals()` method would be:
```java
@Override
public boolean equals(Object obj) 
{
    Car c = (Car)obj;
    
    return (this.color.equals(c.color) && (this.numberOfDoors == c.numberOfDoors);
}

// With this definition of equals, the output is:
Do they point to the same: false
Are they equal: true
```
Notice that since the method is declared in the `Object` class, the type of argument is `Object`.  This must be cast to the appropriate type before the comparison of the members is made.  Notice that the comparison is this case is simple, based on the color and number of doors. The comparison may be a more complex memberwise comparison, or there could be a single attribute that is unique and should be used for all comparison.  In a more complex car model the `equals()` method might be implemented as follows:
```java
@Override
public boolean equals(Object obj) 
{
    Car c = (Car)obj;   
    // Assume the Car class has a VIN attribute, which should be unique for every car.
    return (this.VIN.equals(c.VIN));
}
```

--

--

--

--


## Questions
The package that is imported into every Java file by default is:
* base.package
* java.lang
* java.util
* java.system

--

For a class called "Utils" in the "com.tts.misc" package, the folder structure will be:
* src/Utils.java
* src/packages/Utils.java
* src/com.tts.misc.Utils.java
* src/com/tts/misc/Utils.java

--

The method used to create an object from a class definition is called
* init method
* main method
* new method
* constructor method

--

It is required that the `main()` method be in the no-name default package
* true
* false

--

In java, class constancts are declared how?
* static final int CONSTANT_VAL = 0;
* constant final int CONSTANT_VAL = 0;
* static void int CONSTANT_VAL = 0;
* static locked int CONSTANT_VAL = 0;

--

Which is NOT a visiblity modifier defined in Java?
* public
* private
* protected

--

What statement is used to bring a class in scope from a package?
* insert
* import
* using
* define

--

Multiple methods with the same name and different parameter lists is known as:
* Overriding
* Duplicating
* Duplicate Defining
* Overloading

--

Redefining methods defined in a parent class is known as:
* toString()
* Overloading
* Overriding
* Hiding