## Data Structures (2017) - Class Projects

Contained in this repository are a few class projects I completed in CS 201: *Data Structures* at Carleton College (Winter Term, 2017), taught by [Dr David Musicant](https://apps.carleton.edu/profiles/dmusicant/).

This course was based in Java, and its focus was on understanding common data structures (lists, heaps, hash tables, binary search trees, etc.) and their ideal use cases. In the beginning of the course, we created our own implementations of several built in Java data structures. Later in the course, we created programs (for encryption, search, sorting, etc.) with the data structures we had studied, creating our own implementations as well as using built-in Java implementations. We evaluated the pros and cons of different data structures and analyzed the complexity of our code. [Here is the course reading/assignment schedule.](https://www.cs.carleton.edu/faculty/dmusican/cs201/schedule.html)

The following is a brief description of the projects I have included. The complete text of the original assignment is also included as a README in each project's directory.


### Changeling

Changeling is a program that finds a way of transforming one word of a given length to another of the same length, changing one letter at a time, with the fewest possible intermediary words. The program takes three command line arguments: a list of words in the form of a text file (provided to us, each word lowercase & on its own line), the first word, and the second word. Implementation instructions were as follows (excerpt): 
> Build a graph out of the word list file where each word is a vertex of the graph and two words/vertices are connected if they are the same length and differ by only one letter. Then use a breadth-first search starting at the first word until you find the second word. Then print out the path, if any, that you discovered in this way.

### Binary Tree Search Engine

This assignment consisted of two parts: (1) creating a class `TreeMapForStrings` that implements a String to String map from scratch using a binary search tree, and (2) creating a class `TreeSearchEngine` that takes a user-provided search term and prints a list of key-value search results for which the key matches or starts with the provided search term. We were to test these classes by using the tree map to store IDMb film information where the key was the film title and the value was its plot summary, and then searching for films by title using our search engine.

### Heap

We were provided code for a binary heap class. We were to modify the code, changing as few lines as possible, such that the constructor took a paramater `k`, where `k` was the max number of children each node could have (i.e. we modified the binary heap into a k-ary heap).
