# Refactoring Lecture notes

  * 29.03.2018
  * Sebastien Mosser

Based on the book _"Refactoring: Improving the Design of Existing Code"_ by fowler, Beck, Brant, Opdyke & Roberts (1999). New version will be published in 2018, using Javascript instead of Java as reference language. 
  
## Generalities


  * Definition
    * Refactoring: _a change made to the internal structure of software to make it easier to understand and cheaper to modify without changing its observable behavior._
    * Refactor: _to restructure software by applying a series of refactorings without changing its observable behavior._
  * Refactoring versus over-engineering (Tomasz Jaskula)
    * Refactoring = code transformation based on what you’ve learnt from the past
    * Over-engineering = code transformation based on the speculation for the future.
  * Role of tests
    * _without changing its observable behavior_  
  * Relationship with design
    * _refactoring can be an alternative to upfront design_ (emergent architecture)
    * Refactoring changes the role of upfront design. 
    * If you don't refactor, there is a lot of pressure in getting that upfront design right. 
    * The sense is that any changes to the design later are going to be expensive

### Why should we refactor?

  * Programs 
    * that are hard to read are hard to modify.
    * that have duplicated logic are hard to modify.
    * that require additional behavior that requires you to change running code are hard to modify.
    * with complex conditional logic are hard to modify
  * Refactoring 
    * Improves the Design of Software
    * Makes Software Easier to Understand
    * Helps You Find Bugs
    * Helps You Program Faster

###  When should we refactor?

  * Three strikes and you refactor.
    * _The first time you do something, you just do it. The second time you do something similar, you wince at the duplication, but you do the duplicate thing anyway. The third time you do something similar, you refactor_
  * Refactor When You Add Function
  * Refactor When You Need to Fix a Bug
  * Refactor As You Do a Code Review  


## Bad smells triggering refactoring

_If it stinks, change it._

  * Duplicated code
    * _If you see the same code structure in more than one place, you can be sure that your program will be better if you find a way to unify them_ 
  * Long method
    * _The key here is not method length but the semantic distance between what the method does and how it does it_
  * Large Class
    * _When a class is trying to do too much, it often shows up as too many instance variables_ 
  * Long Parameter list
    * _with objects you don't pass in everything the method needs; instead you pass enough so that the method can get to everything it needs_ 
  * Divergent change
    * _one class is commonly changed in different ways for different reasons_ 
  * Shotgun surgery
    * _every time you make a kind of change, you have to make a lot of little changes to a lot of different classes_
  * Feature envy
    * _A classic smell is a method that seems more interested in a class other than the one it actually is in_
  * Data Clumps
    * _Bunches of data that hang around together really ought to be made into their own object_
  * Primitive Obsession
    * _You can easily write little classes that are indistinguishable from the built-in types of the language_
  * Switch statements
    * _The problem with switch statements is essentially that of duplication. Often you find the same switch statement scattered about a program in different places_
  * Parallel Inheritance Hierarchies
    * _every time you make a subclass of one class, you also have to make a subclass of another_ 
  * Lazy Class
    * _A class that isn't doing enough to pay for itself should be eliminated. Often this might be a class that used to pay its way but has been downsized with refactoring. Or it might be a class that was added because of changes that were planned but not made. Either way, you let the class die with dignity._ 
  * Speculative generality
    * _Oh, I think we need the ability to this kind of thing someday_ 
  * Temporary fields
    * _an object in which an instance variable is set only in certain circumstances_ 
  * Message Chains
    * _You see message chains when a client asks one object for another object, which the client then asks for yet another object, which the client then asks for yet another another object, and so on._ 
  * Middle man
    * _You look at a class's interface and find half the methods are
delegating to this other class._ 
  * Inappropriate intimacy
    * _Sometimes classes become far too intimate and spend too much time delving in each others'private parts_ 
  * Alternative class with ≠ interfaces
    * _any methods that do the same thing but have different signatures for what they do_ 
  * Incomplete library class
    * _The trouble is that it is often bad form, and usually impossible, to modify a library class to do something you'd like it to do_ 
  * Data class
    * _These are classes that have fields, getting and setting methods for the fields, and nothing else._ 
  * Refused Bedquest
    * _Subclasses get to inherit the methods and data of their parents. But what if they don't want or need what they are given? They are given all these great gifts and pick just a few to play with._ 
  * Comments          
    * _Our first action is to remove the bad smells by refactoring. When we're finished, we often find that the comments are superfluous._ 

## Refactoring examples


### Method contents level

  * Extract method
    * Situation: You have a code fragment that can be grouped together.
    * Refactoring: Turn the fragment into a method whose name explains the purpose of the method.
   * Inline method:
     * situation: A method's body is just as clear as its name.
     * refactoring: Put the method's body into the body of its callers and remove the method.
   * Introduce Explaining Variable
     * situation: You have a complicated expression.
     * refactoring: Put the result of the expression, or parts of the expression, in a temporary variable with a name   


### Moving features between objects

  * Move method:
    * situation: A method is, or will be, using or used by more features of another class than the class on which it is defined.
    * refactoring: Create a new method with a similar body in the class it uses most. Either turn the old method into a simple delegation, or remove it altogether.
  * move field:
    * situation: A field is, or will be, used by another class more than the class on which it is defined.
    * refactoring: Create a new field in the target class, and change all its users.
  * extract class:
    * situation: You have one class doing work that should be done by two.
    * refactoring: Create a new class and move the relevant fields and methods from the old class into the new class.
  * Remove middle man
    * situation: A class is doing too much simple delegation.
    * refactoring: Get the client to call the delegate directly. 

### Simplifying conditionals

  * Decompose conditionals
    * situation: You have a complicated conditional (if-then-else) statement.
    * refactoring: Extract methods from the condition, then part, and else parts.
  * Replace Conditional with Polymorphism
    * situation: You have a conditional that chooses different behavior depending on the type of an object.
    * refactoring: Move each leg of the conditional to an overriding method in a subclass. Make the original method abstract.
  
## Problems with refactoring

  * Databases
  * Changing interfaces
  * Time-consuming
  * Performance impact (easier to understand => slower ?)
  * Toos support.