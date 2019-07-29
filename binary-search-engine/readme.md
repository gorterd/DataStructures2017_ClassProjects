# Assignment: Search Engine via Binary Search Tree

Author: David Musicant

[Assignment Link](https://www.cs.carleton.edu/faculty/dmusicant/cs201w17/searchtree.html)

## Overview

This assignment is a repeat of the last one, with the difference that you'll be using a binary search tree instead of a hash table. Additionally, you'll be able to do a prefix search, since binary search trees allow you to search efficiently within a range.

## Part 1: Create a Map

Create a class called `TreeMapForStrings` that will be used to map Strings to Strings. (The intention is to use it to map movie titles to movie plots.) Implement this map as a binary search tree. For this assignment, you should not the built-in Java classes that do this task such as `TreeMap`, `HashMap`, `TreeSet`, or `HashSet`. Your map should have the following methods:

* `void put(String key, String value)`
add a key and a value to your map. If the key is already present in the map, replace the old value with the new one.

* `String getValue(String key)`
get the value associated with a particular key. Return `null` if the key is not in the map.

Make sure to test your map appropriately. A main method would be a nice way to do this.

Java reminder: since you are comparing strings, make sure to use the `equals` and `compareTo` methods instead of the <, >, and = operators.

You should not be searching the web or other sources for similar code to this. You are welcome to look at the textbook.

## Part 2a: More map

Add this method to your `TreeMapForStrings` class:

* `ArrayList<String> getKeysForPrefix(String prefix)`
Return a list of all keys that start with `prefix`. You should do this efficiently: specifically, you should not traverse the entire tree in order to find these keys.

`Part 2b: Fill up the Map with data from IMDb, and run queries`

This part of the assignment is a complete repeat of Part 2 from the previous hashing version, so you should hopefully be able to use the code that you wrote from the previous assignment and make only minimal changes necessary in order to use your tree instead of the hash table. You'll create a class called `TreeSearchEngine` that works essentially the same as `HashSearchEngine` did in the previous assignment. The key difference is that you should be able to do a prefix search, based on your `getKeysForPrefix` method. At the user prompt, if a user types in a movie name such as `Flash`, you should return back all titles and summaries that start with that prefix. Do this repeatedly until the user types `####`, in which case the program should end. I've checked that no movie titles start with that prefix.

### Unsorted data

One important difference between this and the last assignment is that you must make sure to read data that is not sorted by title. Otherwise, the binary search tree you generate will all go straight down in one direction, and end up with \(O(n)\) performance. To that end, I have created a file called `plotunsorted.list` which is formatted identically to the original `plot.list`, but the entries appear in random order. You should use that one for this assignment. There is a compressed version called `plotunsorted.list.gz` that is again sitting in `/Accounts/courses/cs201/dmusican` on the department systems; see instructions from the last assignment on how to obtain it.

### Evaluation of how well it works
A difference between this and the last assignment is that you have no control over how much memory gets used (there's no hash table array with a size for you to pick), so we'll skip this sort of analysis. Instead, I'll ask a more straightforward question.

Loading up the entire movie database is slow. Is that because reading the file itself is slow, or is because adding to the tree is slow? Experimentally determine which is the bottleneck, and explain your results. You may find the method `System.nanoTime()` very useful here. Submit your results, and an explanation of how you got them and what the relative measurements are, in a `readme.txt` file.

## What to turn in

Part 1: Turn in your `TreeMapForStrings` class, which should include a main method for testing with some small data.

Part 2: Turn in your updated `TreeMapForStrings` class and your `TreeSearchEngine` class, as well as your `readme.txt` file describing performance measurements.

## Acknowledgements

According to IMDb licensing, I should add:

Information courtesy of IMDb (http://www.imdb.com/). Used with permission.
