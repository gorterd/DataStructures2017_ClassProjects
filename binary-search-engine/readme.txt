Trial 1:
Loading Time (s): 25.148576659 
Adding Time (s): 4.276631403

Trial 2:
Loading Time (s): 28.723799536 
Adding Time (s): 5.577549101

Trial 3:
Loading Time (s): 37.050372174 
Adding Time (s): 10.122432108


Method:

I took the system time before and after the start of loading each 
movie and took their difference, to measure the loading time for 
that movie. Each time, I would add it to the sum total represented 
by the variable loadingTime. Likewise with adding to the tree.

Results:

It appears that the primary bottleneck comes from reading from the 
file. This really demonstrates the efficiency of adding to binary 
search trees, given unsorted data.