import java.util.Random;
public class RandomAvlTreeAverage {
	private Node root;
	
	// Each node is defined as a separate node, each with attributes: key, balance factor, height, child and parent nodes 
	private static class Node {
		private int key;
		private int balance;
		private int height;
		private Node left;
		private Node right;
		private Node parent;
		
		// Constructor to create a new node with a key value and parent reference
		Node(int key, Node parent) {
			this.key = key;
			this.parent = parent;
		}
	}
	
	// Insert new node with a key by checking if root is null then where insertion can be done and re-balance
	public boolean insert(int key) {
		if (root == null) {
			root = new Node(key, null);
			return true;
		}
		Node n = root;
		while (true) {
			if (n.key == key)
				return false;
			Node parent = n;
			boolean goLeft = n.key > key;
			n = goLeft ? n.left : n.right;
			if (n == null) {
				if (goLeft) {
					parent.left = new Node(key, parent); 
				} else {
					parent.right = new Node(key, parent);
				} 
				rebalance(parent);
				break;
			}
		}
		return true;
	}
	
	// Re-balance AVL tree using single or double rotation 
	private void rebalance(Node n) {
		setBalance(n);
		if (n.balance == -2) {
			if (height(n.left.left) >= height(n.left.right))
				n = rotateRight(n);
			else 
				n = rotateLeftThenRight(n);	
		} else if (n.balance == 2) {
			if (height(n.right.right) >= height(n.right.left))
				n = rotateLeft(n);
			else 
				n = rotateRightThenLeft(n);
		}
		if (n.parent != null) {
			rebalance(n.parent);
		} else {
			root = n;
		}
	}
	
	// Update height and balance factor of each node
	private void setBalance(Node...nodes) {
		for (Node n: nodes) {
			reheight(n);
			n.balance = height(n.right) - height(n.left);
		}
	}
	
	// Return height of a node
	private int height(Node n) {
		if (n == null)
			return -1;
		return n.height;
	}
	
	// Update height of AVL tree
	private void reheight(Node node) {
		if (node != null) {
			node.height = 1 + Math.max(height(node.left), height(node.right));
			}
	}
	
	// Perform a single RR rotation where parent references are updated and update balance factors with setBalance
	private Node rotateLeft(Node a) {
		Node b = a.right;
		b.parent = a.parent;
		a.right = b.left;
		
		if (a.right!= null)
			a.right.parent = a;
		b.left = a;
		a.parent = b;
		if (b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}
		setBalance(a, b);
		return b;
	}
	
	// Perform single LL rotation where parent references are updated and update balance factors with setBalance
	private Node rotateRight(Node a) {
		Node b = a.left;
		b.parent = a.parent;
		a.left = b.right;
		
		if (a.left != null)
			a.left.parent = a;
		b.right = a;
		a.parent = b;
		if(b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}
		setBalance(a,b);
		return b;
	}
	
	// Perform double LR rotation 
	private Node rotateLeftThenRight(Node n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}
	
	// Perform double RL rotation 
	private Node rotateRightThenLeft(Node n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}
	
	// Print information for each node
	public void printBalance() {
		printBalance(root);
	}
	
	private void printBalance(Node n) {
	// Expanded to print additional node information
		if (n != null) {
			printBalance(n.left);
			System.out.printf("key:"+ n.key + " height:" + n.height + " balance:" + n.balance + "\n");
			try {
				System.out.printf(" parent: " + n.parent.key + "\n");
			} catch (NullPointerException e) {
				System.out.print(" parent of "+n.key+" does not exist! NullPointerException Caught\n");
			}
			printBalance(n.right);
		}
	}
	
	// Delete node by replacing with the in-order successor and calls to update balance factor of nodes with reBalance
	public void delete(Node node) {
		if (node.left == null && node.right == null) {
			if (node.parent == null) {
				root = null;
			} else {
				Node parent = node.parent;
				if (parent.left == node) {
					parent.left = null;
				} else {
					parent.right = null;
				}
				rebalance(parent);
			}
			return;
		}
		if (node.left != null) {
			Node child = node.left;
			while (child.right != null)
				child = child.right;
			node.key = child.key;
			delete(child);
		} else {
			Node child = node.right;
			while (child.left != null)
				child = child.left;
			node.key = child.key;
			delete(child);
		}
	}
	
	// Delete key value from AVL Tree by deleting node containing specific key value (delkey)
	public void delete(int delKey) {
		if (root == null)
			return;
		Node child = root;
		while (child != null) {
			Node node = child;
			child = delKey >= node.key ? node.right : node.left;
			if (delKey == node.key) {
				delete(node);
				return;
			}
		}
	}
	
	// Traverse the AVL tree and calculate the sum of all nodes
	public int sum(Node n) {
		if( n == null) {
			return 0;
		}
		return n.key + sum(n.left) + sum(n.right);
	}
	
	// Calculate average node values from sum of all nodes divided by number of nodes
	public double average(int count) {
		int sumTotal = sum(root);
		double average = (double) sumTotal / count;
		return average;
	}
	
	public static void main(String[] args) {
		RandomAvlTreeAverage tree = new RandomAvlTreeAverage();
		Random random = new Random();
		
		// Inserting 10 different numbers from 1 to 100 to build an AVL tree 
		System.out.println("Inserting 10 random values from 1 to 100");
		for (int i = 0; i < 10; i++) {
			int randomNum= random.nextInt(100) + 1;
			if (!tree.insert(randomNum)) {
				i--;
			}
		}
		//Print AVL tree
		System.out.print("Printing balance:\n");
		tree.printBalance();
		
		//Traverse AVL tree and print average of all nodes
		double averageNum = tree.average(10);
		System.out.print("Average of all node values: "+ averageNum);
		
	} // End Main
} // End Class