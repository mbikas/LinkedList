import java.util.HashSet;
import java.util.Vector;


public class LinkedList {
	
	public static void main(String[] args) {
		
		//Insertion test
		ListNode linkedList = new ListNode(10);
		linkedList = add(linkedList, 20);
		linkedList = add(linkedList, 40);
		print(linkedList);
		linkedList = addFromStart(linkedList, 3, 30);
		print(linkedList);
		linkedList = addFromEnd(linkedList, 1, 35);
		print(linkedList);
		
		//deletion test
		linkedList = remove(linkedList, 1);
		print(linkedList);
		linkedList = removeFromEnd(linkedList, 1);
		print(linkedList);
		
		//test remove all even values
		linkedList = removeAllEven(linkedList);
		print(linkedList);
		
		linkedList = add(linkedList, 35);
		print(linkedList);
		
		// test remove duplicate
		linkedList = removeDuplicates(linkedList);
		print(linkedList);	
		
		
		linkedList = add(linkedList, 1);
		linkedList = add(linkedList, 2);
		linkedList = add(linkedList, 3);
		linkedList = add(linkedList, 4);
		linkedList = add(linkedList, 5);
		print(linkedList);
		
		linkedList = reverse(linkedList);
		print(linkedList);
		
		linkedList = oddEvenList(linkedList);
		print(linkedList);
		
		
		//testing merge sorted list
		ListNode linkedList1 = new ListNode(1);
		linkedList1 = add(linkedList1, 3);
		linkedList1 = add(linkedList1, 5); linkedList1 = add(linkedList1, 6);
		ListNode linkedList2 = new ListNode(2);
		linkedList2 = add(linkedList2, 4); linkedList2 = add(linkedList2, 7);
		print(linkedList1);
		print(linkedList2);
		
		print (mergeLists(linkedList1, linkedList2));
		
	}
	
	
	/**
	 * merge two sorted list
	 * @param headA 1 => 3 => 5 => 6 => NULL
	 * @param headB 2 => 4 => 7 => NULL
	 * @return 1 => 2 => 3 => 4 => 5 => 6 => 7 => NULL
	 */
	public static ListNode mergeLists(ListNode headA, ListNode headB) {
	    if (headA == null) return headB;
	    if (headB == null) return headA;
	    
	    
	    
	    ListNode head;
	    if (headB.val < headA.val){
	    	head = headB;
	    	headB = headB.next;
	    }
	    else{
	    	head = headA;
	    	headA = headA.next;
	    }
	    ListNode ans = head;
	    
	    while (headA != null || headB != null){
	        
	    	if (headA != null && headB != null){
	    		if (headA.val < headB.val){
		    		head.next = headA;
			    	headA = headA.next;
		    	}
		    	else{
		    		head.next = headB;
			    	headB = headB.next;
		    	}
	    	}
	    	else{
	    		if (headA != null){
	    			head.next = headA;
	    			headA = headA.next;
	    		}
	    		else{
	    			head.next = headB;
	    			headB = headB.next;
	    		}
	    	}
	    	
	    	head = head.next;
	    }
	    
	    return ans;
	}
	
	
	
	
	/**
	 * reverse the given list
	 * @param head 1 => 2 => 3 => 4 => 5 => null
	 * @return 5 => 4 => 3 => 2 => 1 => null
	 */
	public static ListNode reverse(ListNode head)
	{
		if (head == null || head.next==null)return head;
		
		ListNode previous = null;
		ListNode current = head;
		
		while (current != null){
			ListNode next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		return previous;
	}
	
	/**
	 * reverse doubly linked list
	 * @param head NULL <-- 2 <--> 4 <--> 6 --> NULL
	 * @return NULL <-- 6 <--> 4 <--> 2 --> NULL
	 */
	ListNode ReverseDoublyLinkedList(ListNode head) {
        if (head == null || head.next == null)return head;
	    
        ListNode temp = null;
        ListNode current = head;
	 
        //swap next and prev for all nodes
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        
        if (temp != null) {
            head = temp.prev;
        }
        return head;
	}
	
	/**
	 * https://leetcode.com/problems/odd-even-linked-list/
	 * @param head  1 => 2 => 3 => 4 => 5 => null
	 * @return 		1 => 3 => 5 => 2 => 4 => null
	 */
	public static ListNode oddEvenList(ListNode head)
	{
		if (head == null)return head;
		
		ListNode current = head;
		ListNode headEven = null;
		int count = 1;
		while(current != null) {
			if (count % 2 == 0){
				headEven = add(headEven, current.val);
			}
			count++;
			current = current.next;
		}
		
		current = head;
		count = 1;
		ListNode previous = head;
		while (current != null){
			
			if (count % 2 == 0){
				previous.next = current.next;
				current = current.next;
				count++;
				continue;
			}
			
			previous = current;
			current = current.next;
			count++;
		}
		previous.next = headEven;
		return head;
    }
	
	/**
	 * https://leetcode.com/problems/odd-even-linked-list/
	 * @param head  1 => 2 => 3 => 4 => 5 => null
	 * @return 		1 => 3 => 5 => 2 => 4 => null
	 */
	public static ListNode oddEvenListValues(ListNode head)
	{
		if (head == null)return head;
		
		Vector<Integer> values = new Vector<Integer>();
		
		boolean oddHead = false;
		if (head.val % 2 != 0)
			oddHead = true;
		
		ListNode current = head;
		ListNode headNew = null;
		while(current != null) {
			boolean condition1 = (oddHead == true && current.val % 2 == 0);
			boolean condition2 = (oddHead == false && current.val % 2 != 0);
			if (condition1 || condition2){
				headNew = add(headNew, current.val);
				values.add(current.val);
			}
			current = current.next;
		}
		
		current = head;
		ListNode previous = head;
		while (current != null){
			boolean condition1 = (oddHead == true && current.val % 2 == 0);
			boolean condition2 = (oddHead == false && current.val % 2 != 0);
			if (condition1 || condition2){
				previous.next = current.next;
				current = current.next;
				continue;
			}
		
			previous = current;
			current = current.next;
		}
		previous.next = headNew;
		return head;
    }
	
	
	/**
	 * https://leetcode.com/problems/linked-list-cycle/
	 * check if a list contains any cycle
	 * @param head  10 => 20 => 30 => 10 => null
	 * @return yes
	 */
	public static boolean hasCycle(ListNode head)
	{
		if (head == null)return false;
		
		HashSet<ListNode> listSet = new HashSet<>();
		ListNode current = head;
		while(current != null) {
			if(listSet.contains(current))
				return true;
			listSet.add(current);
			current = current.next;
		}
		
		return false;
    }
	
	/**
	 * https://leetcode.com/problems/intersection-of-two-linked-lists/
	 * https://www.hackerrank.com/challenges/find-the-merge-point-of-two-joined-linked-lists
	 * get the intersection node of two linked list
	 * Find Merge Point of Two Lists
	 * @param headA  10 => 20 => 	
	 * 									35 => 40 => 50 => null 
	 * @param headB	 5 => 15 => 25 => 	
	 * @return 35 => 40 => 50 => null 
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) 
	{
		if (headA == null || headB == null)return null;
		
		//Traverse list A and store the address / reference to each node in a hash set. 
		//Then check every node bi in list B: if bi appears in the hash set, 
		//then bi is the intersection node.
		

		HashSet<ListNode> listSet = new HashSet<>();
		ListNode currentA = headA;
		ListNode currentB = headB;

		while(currentA != null) {
			listSet.add(currentA);
			currentA = currentA.next;
		}
		
		while(currentB != null) 
		{
			if(listSet.contains(currentB))
				return currentB;
			currentB = currentB.next;
		}
		return null;
    }
	
	/**
	 * compare two list
	 * @param headA 1 => 2 => null
	 * @param headB 1 => 2 => null
	 * @return 1
	 */
	int CompareLists(ListNode headA, ListNode headB) {
	    while (headA != null && headB != null ){
	        if (headA.val != headB.val) return 0;
	        headA = headA.next;
	        headB = headB.next;
	    }
	    
	    if (headA != null || headB != null) return 0;
	    return 1;
	}
	
	
	/**
	 * remove duplicate values from the list
	 * @param head 1 => 1 => 2 => 3 => 3 => null
	 * @return head of the updated list: 1 => 2 => 3 => null
	 */
	public static ListNode removeDuplicates(ListNode head)
	{
		if (head == null)return head;
		
		ListNode current = head;
		ListNode previous = head;
		while (current != null){
			if (current.val == previous.val){
				previous.next = current.next;
				current = current.next;
				continue;
			}
			previous = current;
			current = current.next;
		}
		
		return head;
	}
	
	
	
	/**
	 * remove all the even value from the list
	 * @param head 10 => 20 => 30 => 35 => 40 => null
	 * @return head of the updated list: 35 => null
	 */
	public static ListNode removeAllEven(ListNode head)
	{
		if (head == null)return head;
		
		ListNode current = head;
		ListNode previous = head;
		while (current != null){
			if (current.val % 2 == 0){
				
				if (current == head){
					head = current.next;
					current = head;
					previous = head;
					continue;
				}
				
				previous.next = current.next;
				current = current.next;
				continue;
			}
			
			previous = current;
			current = current.next;
		}
		
		return head;
	}
	
	
	/**
	 * remove the node from the given position of the list starting from the beginning of the list
	 * @param head 10 => 20 => 30 => 35 => 40 => null
	 * @param postion 4
	 * @return head of the updated list: 10 => 20 => 30 => 40 => null
	 */
	public static ListNode remove(ListNode head, int position)
	{
		if (head == null)return head;
		
		// if position == 1 or remove head
		if (position == 1) return head.next;
		
		ListNode temp = head;
		for (int i=1;i<position-1;i++){
			temp = temp.next;
		}
		
		temp.next = temp.next.next;
		return head;
	}
	
	/**
	 * remove the node from the given position of the list starting from the end of the list
	 * @param head 10 => 20 => 30 => 35 => 40 => null
	 * @param postion 2
	 * @return head of the updated list: 10 => 20 => 30 => 40 => null
	 */
	public static ListNode removeFromEnd(ListNode head, int position)
	{
		int sz = size(head);
		int newPosition = sz - position + 1;
		return remove(head, newPosition);
	}
	
	
	
	//add a new value at the end of the list
	public static ListNode add(ListNode head, int data)
	{
		if (head == null){
			ListNode newNode = new ListNode(data);
			return newNode;
		}
		ListNode temp = head;
		while (temp.next != null){
			temp = temp.next;
		}
		ListNode newNode = new ListNode(data);
		temp.next = newNode;
		return head;
	}
	
	
	/**
	 * sorted inert to a doubly linked list
	 * @param head NULL <-- 2 <--> 4 <--> 6 --> NULL
	 * @param data 5
	 * @return NULL <-- 2 <--> 4 <--> 5 <--> 6 --> NULL
	 */
	ListNode SortedInsert(ListNode head,int data) {
	    
		ListNode newNode = new ListNode(data);
	    
	    if (head == null) {
	        return newNode;
	    }
	    
	    if (data <= head.val) {
	        newNode.next = head;
	        head.prev = newNode;
	        return newNode;
	    }
	    
	    ListNode rest = SortedInsert(head.next, data);
	    head.next = rest;
	    rest.prev = head;
	    return head;
	}
	ListNode SortedInsertIterative(ListNode head,int data) {
	    
		ListNode newNode = new ListNode(data);
		
	    if (head == null || head.val > data){
	        newNode.next = head;
	        return newNode;
	    }
	    ListNode current = head;
	    ListNode prev = null;
	    while(current != null && current.val < data){
	        prev = current;
	        current = current.next;
	    }
	    newNode.prev = prev;
	    newNode.next = prev.next;
	    prev.next = newNode;
	    return head;
	}
	
	
	/**
	 * add a new value at the given position starting from the beginning of the list
	 * @param head  10 => 20 => 40 => null
	 * @param position 3
	 * @param data 30
	 * @return head of the list after adding the value to the list
	 */
	public static ListNode addFromStart(ListNode head, int position, int data)
	{
		// if we need to insert to the head
		if (position == 1){
			ListNode newNode = new ListNode(data);
			newNode.next = head;
			return newNode;
		}
		
		ListNode temp = head;
		for (int i=1;i<position-1;i++){
			temp = temp.next;
		}
		ListNode newNode = new ListNode(data);
		newNode.next = temp.next;
		temp.next = newNode;
		return head;
	}
	
	/**
	 * add a new value at the given position starting from the end of the list
	 * @param head  10 => 20 => 40 => 50 => null
	 * @param position 2
	 * @param data 30
	 * @return head of the list after adding the value to the list
	 */
	public static ListNode addFromEnd(ListNode head, int position, int data)
	{
		int sz = size(head);
		int newPosition = sz - position + 1;
		return addFromStart(head, newPosition, data);
	}
	
	//print the list
	public static void print(ListNode head){
		ListNode temp = head;
		while (temp != null){
			System.out.print(temp.val + " => ");
			temp = temp.next;
		}
		System.out.println("null");
	}
	
	
	/**
	 * returns the size of the list
	 * @param head
	 * @return
	 */
	public static int size(ListNode head)
	{
		int listCount = 0;
		ListNode temp = head;
		while (temp != null){
			listCount++;
			temp = temp.next;
		}
		return listCount;
	}
	
	public static class ListNode 
	{
		int val;
		ListNode next;
		ListNode prev;
		ListNode(int x) 
		{ 
			val = x; 
			next = null;
		}
	}
	

}
