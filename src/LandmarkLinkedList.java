

public class LandmarkLinkedList {
    Node head;



    public static void insert(LandmarkLinkedList list, Landmark data)
    {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.setNext(null);

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next() != null) {
                last = last.next();
            }

            // Insert the new_node at last node
            last.setNext(new_node);
            new_node.setPrevious(last);
        }

        // Return the list by head
    }


    public static void printList(LandmarkLinkedList list)
    {
        Node currNode = list.head;

        System.out.print("LinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            if(currNode.altNext() != null){
                System.out.println(currNode.getData() + " \\\\ " + currNode.altNext().getData() + " // ");
            } else {
                System.out.println(currNode.getData() + " ");
            }

            // Go to next node
            currNode = currNode.next();
        }
    }

    public static LandmarkLinkedList navigateList(LandmarkLinkedList list){
        Node current = list.head;
        while (current.next() != null) {
            System.out.print("Node: " + current.getData() + "  Next: " + current.next().getData());

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

}
