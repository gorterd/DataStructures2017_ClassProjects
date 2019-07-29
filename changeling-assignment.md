Overview

In some crossword puzzle books, there is a kind of word puzzle called a Changeling. A changeling (in this context, anyway) is a pair of words with the same number of letters. To solve the changeling, you show a sequence of words, each differing from the previous word by a single letter, that transforms the first word of the changeling into the second.

For example, one solution to the changeling "CAT to DOG" is "CAT, COT, DOT, DOG." The intermediate words must, of course, be words. Note that in this example, we have the smallest possible number of words, since CAT and DOG differ in three letter positions, and there are three letter transitions in this solution. In a contest of changeling solutions, short solutions win. (Actually, short and clever solutions would be better than the short and dull solutions that are most common, but I won't ask you to write a program to judge cleverness.)

Your program

For this project, you will write a program that uses a word list to compute solutions to changelings. Here is a public domain word list designed for word games that you can use. (Make sure to right-click the previous link and save directly to a file; it's too big to conveniently open in your browser). If you like, you can read more than you ever wanted to know about where this word list came from and who made it.

Your main class for this assignment should be called Changeling, and will be used as follows:

java Changeling wordlistfile firstword secondword

This should compute a solution to the changeling starting at firstword and going to secondword using the indicated word list file. If the user runs the program with some number of command-line arguments other than three, the program should print an appropriate usage message and exit.

So, how are you going to compute changeling solutions? There are undoubtedly many ways to solve this problem, but here is the way you must do it for this assignment:

Build a graph out of the word list file where each word is a vertex of the graph and two words/vertices are connected if they are the same length and differ by only one letter. Then use a breadth-first search starting at the first word until you find the second word. Then print out the path, if any, that you discovered in this way.

You should start first with this smaller word list that I have created, which contains only the three-letter words. (Note that I'm legally able to redistribute this because the original word list is public domain.) Get the program working with this smaller word list first. Building the graph by comparing every pair of words is an \(O(n^2)\) process that could take quite a long time on the full word list. Once you have your program working on the stripped down word list, you may wish to think about how to make it work efficiently for the large one. To get full credit on the assignment, you only need to make it work on the smaller word list.

Think about this problem before running to the computer to start coding, and remember to keep your code organized and your methods short. The program should be lots of fun to play with when you're done.

Implementation specifics

When you implement your graph, I want you to do so using an adjacency-list-style implementation. You can and should do this by using built-in Java data structures to handle most of this. Specifically, here is what I want you to do:

Use a HashMap to keep track of all edges for each word. A key for this HashMap is a word from the word list; the value associated with it is a HashSet of all words it connects to. Remember that a word connects to another word if it is the same length, and if it differs by precisely one letter.

Here is some sample code to show you how I went about this:

Map<String, Set<String>> graph = new HashMap<>();
String word = "cat";
Set<String> connections = new HashSet<>();
connections.add("car");
connections.add("cab");
connections.add("mat");
// ....
graph.put(word, connections);

Submitting your work

Submit a zip file containing your Changeling.java file, as well as any other additional .java files if you created any. Do not turn in your word list files that you downloaded.

FAQ
Q: Must we do this via a graph and a breadth-first search? 
A: Yes! The purpose of this assignment is to get you practice with graphs.

Q: Must we use the built-in HashMap and HashSet as indicated above? 
A: Yes! I'd like you to get experience with using these built-in classes.

Q: Must we build a separate Graph class to manage all of this? 
A: That's up to you. The above HashMap / HashSet framework makes your graph code pretty short. It's ok with me if you handle it all in a single Changeling class if you wish.
