## This is for the cpp implementation of linked lists.
**Note Points** :<br/>
* In cpp we can't simply create an object of the same class inside it.
  * The code I tried was :
    ```c++
    #include<iostream>

    using namespace std;

    class Node
    {
        Node n = Node(); 
        #Error : the generated default constructor for "Node" cannot be used in an initializer for its own data member.
    };
    ```
