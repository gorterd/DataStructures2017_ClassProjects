
# Assignment: Search Engine via Hashing

Author: David Musicant

[Assignment Link](https://www.cs.carleton.edu/faculty/dmusicant/cs201w17/searchhash.html)

## Overview

For this assignment, we'll make a rudimentary search engine. Specifically, we'll load up all of the titles and plots from the Internet Movie Database (IMDb) into a map implemented as a hash table, and then allow the user to request titles and plots for movies as desired.

## Part 1: Create a Map

Create a class called `HashMapForStrings` that will be used to map Strings to Strings. (The intention is to use it to map movie titles to movie plots.) Implement this map as a hash table with chaining via linked lists. For this assignment, you should not use the built-in Java classes that do this task such as `TreeMap`, `HashMap`, `TreeSet`, or `HashSet`. That said, you should use the built-in class `LinkedList`, for your chains. Your map should have the following methods:

* 
```
void put(String key, String value)
```
add a key and a value to your map. If the key is already present in the map, replace the old value with the new one.

*
```
String getValue(String key)
```
get the value associated with a particular key. Return `null` if the key is not in the map.

Make sure to test your map appropriately. A main method would be a nice way to do this.

You should not be searching the web or other sources for similar code to this. You are welcome to look at the textbook.

### Using the built-in LinkedList classes
This subsection was added on 2/18, 11 am

To use the built-in `LinkedList` class, review the various methods that it offers. One source of trickiness is that for each entry, you need to store both a key and a value. You'll have to think through how you want to do that. You'll also need to address how to efficiently search through the linked list and be able to change one of the items if you need to without having to find it all over again. For this purpose, the so-called for-each loop can be quite useful. Here is a sample as to how you might use it:
```
// Assume I've created a class called Entry which contains a key and a value
LinkedList<Entry> mylist = new ListList<Entry>();
// ... add a bunch of items to it ...
for (Entry e : mylist) {
   System.out.println(e.key);
   System.out.println(e.value);
   e.value = someNewValue;
}
```
The for-each loop works on any class that implements the `Iterable` interface. Java Interlude 5 in your textbook has lots more information on how iterators work, which forms the basis for what the for-each loop does.

## Part 2: Fill up the Map with data from IMDb, and run queries

### About IMDb data

IMDb makes plain text files of all of its data available for download. We're going to use a file called `plot.list` that contains titles and plot summaries for all of the movies in the database. Because the file is so large (138 MB compressed, 404 MB uncompressed), I've had to place them in a shared CS department directory with enough disk space (that location is called /Accounts/courses/). 

(Detailed instructions relating to copying the file are omitted).

### Make a smaller version

For testing your program, you should make a small version of this file where you cut out most of the movies. Reading in the entire `plot.list` file takes a long time, and if you read from that file every time you debug your program, you'll add many hours to your coding and debugging waiting for it every time. This is a key general strategy that is really useful when working with big data; make a small version of the data to use for testing. Opening up a file this big in a text editor would likely be exceedingly slow and would use a huge amount of memory. Instead, try to use the UNIX command `head` which works on both Mac and Linux. Read the man page on `head` (type `man head` at a prompt) to see how it works. `head` normally dumps the output to the terminal window; instead, you'll need to redirect the output to a file. If you don't know how to do that, look up "redirecting the output" in [this awesome UNIX tutorial](http://www.cs.carleton.edu/courses/course_resources/Unix2/).

### Load the IMDb data into your map

Create a class called `HashSearchEngine` with a main that creates a `HashMapForStrings` object, then loads up a file of movie data into it. Specifically, the movie titles should be the keys, and the plot summaries should be the values. Note that this data is messy in a few ways: this is typical for data that you get from other sources. You'll need to think about what kinds of processing you may want to do on the data to make the titles usable.

When you use `Scanner` to read the file, you'll need to add a special addition to it. The input file contains a lot of unusual characters, such as diacritical marks. Some of these odd characters will confuse `Scanner` and stop reading the file prematurely, unless you tell it that this file has an unsual encoding. When you open up the file, do it using a second parameter to Scanner like so:
```
Scanner inp = new Scanner(new File("filename.txt"), "ISO-8859-1");
```
Since you'll be using more than one input file (the big one, and your small testing one), don't hardcode the name of your file into your program like "filename.txt" above. Instead, add it as command-line parameter; use the array `args` in `main` to grab the filename and open it appropriately.

(Instructions re memory management omitted)

After loading up the data, you should enter a loop that prompts the user to enter in a movie title. For example, if I enter in `Star Wars (1977)`, I should see the plot summary for that movie. After displaying the plot summary, prompt the user again for another movie. Do this until the user types `####`, in which case the program should end. I've checked that no movie titles start with that prefix.

### Evaluation of how well it works

Finally, you should perform some experiments to see how the amount of memory you use and how the amount of time it takes to run varies with the size of the main hash array. Try a variety of array sizes, and submit a plot made with the tool of your choice showing how memory usage and runtime changes with respect to array size.

* To measure runtime, the method `System.currentTimeMillis()` is really useful. You can measure the time on the clock before and after doing something, and then calculate the difference.
* Measuring memory usage precisely in Java is hard to do accurately, but you can get a very rough estimate with the following calculation.
```
Runtime runtime = Runtime.getRuntime();
int roughEstimate = runtime.totalMemory() - runtime.freeMemory();
```
Submit a `readme.txt` file that contains a brief summary of your findings, and a PDF with your plot.

## What to turn in

**Part 1:** Turn in your `HashMapForStrings` class, which should include a main method for testing with some small data.

**Part 2:** Turn in your `HashSearchEngine` class, as well as your plot showing performance for different array sizes.

## Acknowledgements

According to IMDb licensing, I should add:

Information courtesy of IMDb (http://www.imdb.com/). Used with permission.
