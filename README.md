## Data Structures (2017) - Class Projects

Contained in this repository are most of the class projects I completed in CS 201: *Data Structures* at Carleton College (Winter Term, 2017), taught by [Dr David Musicant](https://apps.carleton.edu/profiles/dmusicant/).

This course was based in Java, and its focus was on understanding common data structures (lists, heaps, hash tables, binary search trees, etc.) and their ideal use cases. In the beginning of the course, we created our own implementations of several built in Java data structures. Later in the course, we created programs (for encryption, search, sorting, etc.) with the data structures we had studied, creating our own implementations as well as using built-in Java implementations. We evaluated the pros and cons of different data structures and analyzed the complexity of our code. [Here is the course reading/assignment schedule.](https://www.cs.carleton.edu/faculty/dmusican/cs201/schedule.html)

The following is a brief description of some of the projects I'd like to highlight. The complete text of the original assignment is also included as a README in each project's directory.


### Changeling ([link](/changeling))

Changeling is a program that finds a way of transforming one word of a given length to another of the same length, changing one letter at a time, with the fewest possible intermediary words. The program takes three command line arguments: a list of words in the form of a text file (provided to us, each word lowercase & on its own line), the first word, and the second word. Implementation instructions were as follows (excerpt): 
> Build a graph out of the word list file where each word is a vertex of the graph and two words/vertices are connected if they are the same length and differ by only one letter. Then use a breadth-first search starting at the first word until you find the second word. Then print out the path, if any, that you discovered in this way.

### Sticks ([link](/sticks))

First significant class project. I recreate the game of sticks, wherein there is a "heap of sticks on a board. On their turn, each player picks up 1 to 3 sticks. The one who has to pick the final stick will be the loser." The game can be played between two humans, between a human and a dumb AI, and between a human and a trained AI, which requires simple machine learning.

### Hash Table Search Engine ([link](/hash-search-engine))

This assignment consisted of two parts: (1) creating a class `HashMapForStrings` that implements a String to String map from scratch using a hash table and linked lists, and (2) creating a class `HashSearchEngine` that takes a user-provided search term and prints a key-value search result(s) where the key matches the the provided search term. We used these classes by storing IDMb film information in the map using a publicly accessible IMDb data file, where the key was the film title and the value was its plot summary, and then searching for films by title using our search engine.

### Binary Tree Search Engine ([link](/binary-search-engine))

This assignment was modeled on the hash table search engine assignment, and likewise consisted of two parts: (1) creating a class `TreeMapForStrings` that implements a String to String map from scratch using a binary search tree, and (2) creating a class `TreeSearchEngine` that takes a user-provided search term and prints a list of key-value search results for which the key matches or starts with the provided search term. We used these classes by storing IDMb film information in the map using a publicly accessible IMDb data file, where the key was the film title and the value was its plot summary, and then searching for films by title using our search engine.

