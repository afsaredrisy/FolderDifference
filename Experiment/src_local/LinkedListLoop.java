package co.zoho.test;

import java.util.HashSet;
import java.util.Set;

class Node{
	int data;
	Node next;
	
	public Node(int data) {
		this.data = data;
	}
}
public class LinkedListLoop {

	public static boolean isLoop(Node head) {
		Set<Node> nodes = new HashSet<>();
		Node temp = head;
		while(temp!=null) {
			if(nodes.contains(temp)) {
				return true;
			}
			nodes.add(temp);
			temp = temp.next;
		}
		
		return false;
	}
	
	public void testFunc(){
		System.out.println("Local Test");
	
	}
	
	public static void main(String[] args) {
		// Sample Linked List
		Node head = new Node(7);
		head.next = new Node(14);
		head.next.next = new Node(21);
		head.next.next.next = head;// Loop here
		if(isLoop(head)) {
			System.out.println("Loop found in Linked List");
		}
		else {
			System.out.println("Loop does not found in Linked List");
		}

	}

}
/*
Approach:

Using Set to keep all Nodes of linked list and checking while traversing linked list in set 
Time Complexity : O(n)
Space Complexity : O(n)
*/