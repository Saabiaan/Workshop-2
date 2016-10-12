##Reviewed project:
**Name:** Sebastian Airisniemi  
**Username:** sa223gd     
**Workshop result:** [GitHub repository](https://github.com/Saabiaan/Workshop-2/tree/master/src/workshop2)   

##Reviewers:
* [Ulrica Skarin](https://github.com/ulricaskarin)  
* [Peter Andersson](https://github.com/sehnpaa)  
* [Christian Trosell](https://github.com/krockgardin) 

##Disclaimer:
_Worth to mention; none of us reviewers are closely familiar with the programming language used in this project, JAVA. With this said, we might have misunderstood or misinterpreted language-specific code-standards._

##Models, implementation, documentation and presentation:
The project is presented well with a good structure of folders. No documentation or instructions included to the repository.

##Test of the runnable version of the application:  
There was no runnable version provided so repository needed to be cloned and started with java / javac by trial-and-error.

###Problems / Suggestions: 
* When starting the application a _FileNotFoundException \User\Sebastian\Documents\test.txt_ was shown. The system cannot find the path specified
for storage of members. We needed to hard-code a new path to file-storage in order to make the application work on our computers. We suggest
the author to facilitate this by creating a new file with a relative path only: ```File file = new File(“test.txt”);```.

* When input of an invalid choice in menu (not int, for example “a”) we get an unhandled error: _Exception in thread “main” java.util.InputMismatchException_.
* We tested to restart the application after testing it once - i.e. members were already added to the file. This caused a _inputMismatchException_.
It seems like that if there are more than one member already registered in the file - application won't work as excpected. When explicitly
deleting member rows inside the file, we got the application to work again.
* Problem adding boat to a member. We got an error message of _invalid input_ even though input was correct. We suspect that this is
due to the logical operator ```||``` in ```/view/OutputToUser.java line 167-170```.

##Use / compiling of the source code using instructions provided:
No instructions was provided.

##Diagrams versus implementation:
###Class diagram: 
This diagram seems to be correct and conforms well with the implemented code. We cannot find any missing relations and relations also point in correct directions. UML notation is correct  [1, endpaper] [2]. However, it would have been nice if the author had emphazised connections between classes by adding association notations [1, p.252]. Another thing to add in the class diagram - for the sake of readability and understandability- may be multiplicity values.

###Sequence diagrams
Author has put a lot of work into creating nice diagrams, sequence diagrams are implemented for all object interactions within the code. However, we have only reviewed and studied two of them (in line with the requirements). 

####Sequence for Search member (i.e view one member)
In general correct and clean UML-notation with objects at top, lifelines, when object is active and so on [1, endpaper] [2]. The diagram is overall conformant to the code, but we found it somewhat hard to get a quick overall grip of certain object calls within the diagram. For example the arrow pointing from :OutputToUser to MemberRegistry (foreach Member in memberList) - does it return with anything?

It would have been easier if arrows were named after methods, if method calls within the same class was explicitly mentioned and if the arrow notation from **[this pdf](http://www.umich.edu/~eecs381/handouts/UMLNotationSummary.pdf)** was used.

Misuse of guard condition in loop fragment. Seems to be used as a naming (foreach member in memberList) for loop but should instead illustrate a condition for when the loop is running. Examples can be found **[here](http://www.ibm.com/developerworks/rational/library/3101.html#N10111)**.

####Sequence for Add member
As mentioned in review of search member diagram, this diagram is as well conformant with the code, but a little hard to get a complete grip of at first glance. In general correct and clean UML-notation with objects at top, lifelines, pointed out when object is active [1, endpaper] [2].
The last arrow seems to have two different purposes; both adding to list and saving to file. In the implementation add method is called on a memberList instance (which unfortunatly isn’t included in the diagram). We believe it should be a separate arrow. Thereafter, we suggest adding an writeToFile arrow from MemberRegistry and back to MemberRegistry.

##Architecture of the project:
The overall architecture is well separated in the Model, View pattern. The Model is not coupled to the user interface/view and no domain rules were to be found in the view.

##Requirement for unique member ID correctly done?
Yes, the application generates unique IDs by incrementing a counter. A newly created member gets the highest of the previous id’s plus one.
We could, however, not test if the application kept generating unique ID's when restarting the application (see why - noted under "Problems / Suggestions" above). 


##Quality of implementation / source code:
The code is of good quality and has high standards. Variables and functions are named in an intention-revealing manner as Martin suggests [3, p.18]. 
It is easy to understand and read the code. We did however find some duplicate code, for example in MemberRegistry.java. 
In almost every method within this class - the author repeats the functionality to retrieve a member to check if the member exist. Duplicate code represents additional work, risk and unnecessary complexity [3, p.173]. 
We suggest that this functionality is lifted out and separated within one method only - “getMemberById(id)” or similar. Something near the suggested solution below:

```
/**
* Example of calling the new method
*/
public void removeMember(int id) {
	Member member = getMemberById(id);
	memberList.remove(member);
}

/**
* Suggested new method to avoid duplication of code
*/
public Member getMemberById(int id) {
	for(Member member : memberList) {
		if(member.getID() == id)  {
			return member;
    		}
	}
}
```

No findings of so called “dead code” is found in the project. However, we would like to point out another minor finding concerning outcommented code in the project. In class Member.java, line 38 there is commented-out code. We suggest this part to be deleted according to Martin’s clean code standards [3, p.287].


##Quality of design:
The design is of good quality and clearly object-oriented. The author does not connect objects with keys / id but uses associations instead - which is conformant with the requirements. 

In general we believe that the design is built with GRASP guidelines in mind [1, p.290]
For example, classes are not dependent on too many elements and they are not connected to other entities. This is a sign of “low coupling” according to Larman [1, p.299] and a good separation of responsibilites between the classes. Information is neatly encapsulated within the classes. 

We also believe that the overall code is of “high cohesion” hence each class does not handle too many areas of responibility [1, p.314-315]. Each class engages in what it knows and what it is supposed to do. This applies to the “information expert” GRASP guideline [1. p, 294].

Furthermore, we cannot find any so called “painted types” in the code. The author has also succeeded well avoiding hidden dependencies.

##As a developer - would the diagrams help - why / why not?
The class-diagram is clean and easy to both understand and follow. We feel that this diagram gives a nice overview to new programmers entering the project - helping them getting to know the system without having to dive into the source code.
The sequence diagrams are good as well, but for us (as non-java-programmers) they would have been of more help understanding the object interaction if
arrows for example was named after actual method-calls.

##Strong points of design / implementation?
We got a good impression of the design and implementation. The folder structure and file / variable / method naming is good, code-standard is high with clear separation of concerns.

##Weaknesses of design / implementation?
Unhandled problems mentioned and listed above aggravated the task of "getting started". These are, however, easy to fix. We also would have
wished for some kind of instructions on how to "kick off" the application and / or maybe a runnable .jar file.

##Has design / implementation passed grade 2 criteria?
In order to tell if the project passes the grade 2 criteria, we checked and compared to the list of requirements handed out by course-management.
Most of the requirements are met, however - we could for example not test some of these due to issues mentioned above in this review. As
an explicit example, requirement number 2 _"List all members in two different ways"_ was incomplete (boat missing in list) due to the problem with adding a boat to
a member. Other than those issues, the project is well presented with good standards and we believe that the author should pass grade 2 
including the fixing of those minor code problems.

##References:
1. Larman, C., Applying UML and Patterns 3rd Ed, 2005, ISBN: 0-13-148906-2
2. Basic UML Class Diagram Notation [Internet]. Published [2016-09-15]. Available from: http://www.umich.edu/~eecs381/handouts/UMLNotationSummary.pdf
3. Martin, RC., Clean Code, 2009, ISBN: 0-13-235088-2
