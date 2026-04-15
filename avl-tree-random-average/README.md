# AVL Tree with Random Input and Average Calculation
A Java program that builds a self-balancing AVL tree from randomly generated integers and computes the average of all inserted node values

## Features
**Operations**
- Insertion with automatic AVL rebalancing
- Deletion with in-order successor replacement
- In-order traversal for sorted output
- Sum and average calculation across all nodes

**Rotations Supported**
- Single left rotation (RR case)
- Single right rotation (LL case)
- Double left-then-right rotation (LR case)
- Double right-then-left rotation (RL case)

**Node Information Tracked**
- Key value
- Height
- Balance factor
- Parent, left child and right child references

## How to Compile
```
javac RandomAvlTreeAverage.java
```
## How to Run
```
java RandomAvlTreeAverage
```
## Example Output
```
Inserting 10 random values from 1 to 100
Printing balance:
key:12 height:0 balance:0
 parent: 15
key:15 height:2 balance:1
 parent: 52
key:32 height:0 balance:0
 parent: 38
key:38 height:1 balance:-1
 parent: 15
key:52 height:3 balance:0
 parent of 52 does not exist! NullPointerException Caught
key:61 height:1 balance:1
 parent: 85
key:76 height:0 balance:0
 parent: 61
key:85 height:2 balance:0
 parent: 52
key:90 height:0 balance:0
 parent: 96
key:96 height:1 balance:-1
 parent: 85
Average of all node values: 55.7
```
## Algorithmic Notes
**AVL Tree**
A self balancing binary search tree, where the balance factor of each node is kept within {-1,0,1}. After insertion or deletion of a node, the tree is restored through rotations if needed. 
**Insertion**
New nodes are inserted using BST ordering and then rebalance() is called, performing single or double rotation where the balance factor is -2 or 2.
  
**Deletion cases Handled**
- Leaf node - removed directly, parent rebalanced
- Node with left child - replaced by in-order predecessor (rightmost node of left subtree)
- Node with right child - replaced by in-order successor (leftmost node of right subtree)

## Why This Project Matters
- Demonstrates a self-balancing data structure (AVL tree) in Java
- Implements all four rotation types to maintain O(log n) height guarantees
- Uses recursive traversal for aggregate computation (sum and average)
- Tracks parent references throughout rotations
