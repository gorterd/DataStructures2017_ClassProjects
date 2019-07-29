# Assignment: Priority Queue via Heaps

Author: David Musicant

[Assignment Link](https://www.cs.carleton.edu/faculty/dmusicant/cs201w17/heaps.html)

This assignment is to be done individually. You can share ideas and thoughts with other people in the class, but you should write up your own assignment.

We will use anonymous grading on Moodle, which means that the grader won't see your name until after the grading is done. This is an easy way to help add an extra element of fairness to the grading. Therefore, make sure your name doesn't appear on your actual submission. When you submit via Moodle, it will know you are. Thanks!

## Programming portion

[Here](https://www.cs.carleton.edu/faculty/dmusicant/cs201w17/Heap.java) is a sample program that implements a heap, and runs some tests on it.

Modify the code so that instead of just being a binary heap (i.e., every node has two children), your class can allow every node to have `k` children, where `k` is a parameter that you supply to the constructor. (We'll assume `k` is an integer greater than or equal to 2). You'll notice that the Heap class I supply already has such a parameter, but I ignore it.

This approach of having more children than 2 is less efficient than using two children. Don't walk away thinking that this is actually a good idea. But it's a neat exercise that has some interesting lessons in it.

Accomplish your goal by changing as few lines as possible in the code that I supply. We will grade you, in part, on how optimal you can be about changing as few lines as you can to make this work. We're not going to go crazy about making small distinctions, but the idea is to encourage you to solve the problem by modifying this program, as opposed to developing your own instead.

One convenient way to measure differences in files is to use the UNIX command `diff`. For example, if I have two files, I can display the differences between them to the screen via the command:
```
diff file1.txt file2.txt
```
You'll actually be interested in counting the number of lines. One can work really carefully at this, but we're just going to run this command:
```
diff OurHeap.java YourHeap.java | wc -l
```
which counts the number of lines in the entire diff output. Technically, this is double counting: it counts the number of lines in `OurHeap.java` that's different from `YourHeap.java` and vice-versa. It also counts some lines with some administrative gunk that diff spits out. But it's an easy way for us to get a rough sense of how much of the original program you have changed.

Do not change any of the code in `main`, as we will use this to test your code.

## Writeup portion

(Not included).

