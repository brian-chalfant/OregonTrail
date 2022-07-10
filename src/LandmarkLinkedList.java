import java.util.LinkedList;
import java.util.List;

public class LandmarkLinkedList {
    Node head;

    static class Node{
        private Landmark data;
        private Node next;
        private Node alt;

        private Node previous;

        private Node altPrevious;

        Node(Landmark data, Node next, Node alt){
            this.data = data;
            this.next = next;
            this.alt = alt;
        }

        Node(Landmark data, Node next){
            this.data = data;
            this.next = next;
            this.alt = null;
        }

        Node(Landmark data){
            this.data = data;
            this.next = null;
            this.alt = null;
        }


        public Landmark getData() {
            return data;
        }

        public void setData(Landmark data) {
            this.data = data;
        }

        public Node next() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node altNext() {
            return alt;
        }

        public void setAltNext(Node alt) {
            this.alt = alt;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getAltPrevious() {
            return altPrevious;
        }

        public void setAltPrevious(Node altPrevious) {
            this.altPrevious = altPrevious;
        }
    }

    public static LandmarkLinkedList insert(LandmarkLinkedList list, Landmark data)
    {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            if(last.previous != null){
            if(last.previous.altNext() != null) {
                last.previous.altNext().setNext(new_node);
            }
            }

            // Insert the new_node at last node
            last.next = new_node;
            new_node.setPrevious(last);
        }

        // Return the list by head
        return list;
    }

    public static LandmarkLinkedList insertAlt(LandmarkLinkedList list, Landmark data)
    {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            Node lastLast = null;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.alt = new_node;
            new_node.setPrevious(last);

            //if(last.previous.previous.next == null){
                //last.previous.previous.setNext(new_node);
            //}

        }

        // Return the list by head
        return list;
    }

    public static void printList(LandmarkLinkedList list)
    {
        Node currNode = list.head;

        System.out.print("LinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            if(currNode.altNext() != null){
                System.out.println(currNode.data + " \\\\ " + currNode.alt.getData() + " // ");
            } else {
                System.out.println(currNode.data + " ");
            }

            // Go to next node
            currNode = currNode.next;
        }
    }

    public static LandmarkLinkedList navigateList(LandmarkLinkedList list){
        Node current = list.head;
        while (current.next() != null) {
            System.out.print("Node: " + current.getData() + "  Next: " + current.next.getData());

            if(current.altNext() != null){
                System.out.print(" Alt: " + current.altNext().getData());
                System.out.println();
                int userInput = Keyboard.nextInt();
                if (userInput == 0){
                    current = current.next();
                } else {
                    current = current.altNext();
                }
            } else {
                System.out.println();
                current = current.next();
            }


        }
        return list;
    }

    public static void main(String[] args) {
        LandmarkLinkedList list = new LandmarkLinkedList();
        Landmark lm1 = new Landmark("Independence, MS", false, false, 100);
        Landmark lm2 = new Landmark("Kansas River Crossing ", false, false, 100);
        Landmark lm3 = new Landmark("Big Blue River Crossing ", false, false, 100);
        lm3.altRoute = true;
        Landmark lm4 = new Landmark("Fort Kearny ", false, false, 100);
        Landmark lm5 = new Landmark("Chimney Rock ", false, false, 100);
        Landmark lm6 = new Landmark("Fort Laramie ", false, false, 100);
        Landmark lm7 = new Landmark("Independence Rock", false, false, 100);
        Landmark lm8 = new Landmark("South Pass ", false, false, 100);
        Landmark lm9 = new Landmark("Green River Crossing", false, false, 100);

        LandmarkLinkedList.insert(list, lm1);
        LandmarkLinkedList.insert(list, lm2);
        LandmarkLinkedList.insertAlt(list, lm3);
        LandmarkLinkedList.insert(list, lm4);
        LandmarkLinkedList.insert(list, lm5);
        LandmarkLinkedList.insert(list, lm6);
        LandmarkLinkedList.insert(list, lm7);
        LandmarkLinkedList.navigateList(list);
    }
}
