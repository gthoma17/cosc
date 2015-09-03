!!!!!!!!!!! Below is the readme for part 2, the only diff between pt1 & pt 2 is pt 2 uses AVL tree instead of BST

Part two is about expiramental analysis of run time of BST struct. We only analyze search. 
First design a class for bst of integers. Don't allow duplicates. The tree class has public method for insert and search
The tree class also has public methods for height and node count. Height defined as # of node in longest path from top to bottom.

Write a main program that:
a) Asks user for some N value and some seed value
b) Initializes RNG with seed, creates BST starting with empty, inserting N values between 1-10^9. Do not allow duplicates
c) Count & display the number of nodes in the tree
d) Measure & display the height of the tree
e) Perform 10^6 searches for random number between 1-10^9, compute average time for 10^6 searches by dividing total time by 10^6 Display average time. Use System.currentTimeMillis() to measure time.

Run program with N values 10^2, 10^3, 10^4, and 10^5. For each N value run your program 10 times with different RNG seeds. Based on 10 runs determine average # of nodes, average ehight, & average search time.
