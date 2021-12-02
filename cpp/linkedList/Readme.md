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
> Q. Can we create an object of a class inside the same class in C++?
> <br/>In C++ : You can not do this, As it will be recursive structure (no end for calculating object size) , to Overcome this problem, Use Self Referential Pointer i.e. the Pointer having the address of Same class type

* [Const keyworkd before and after the method name](https://stackoverflow.com/questions/15999123/const-before-parameter-vs-const-after-function-name-c/15999237)


### Building 
Run the command from the root of the project where the makefile is present :

```bash
$ mkdir obj
$ make
```

### Executing 
Run the compiled binary app `myapp`
```bash
$ ./myapp
```