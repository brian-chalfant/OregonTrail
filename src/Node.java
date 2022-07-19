public class Node{
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

    public boolean hasNext(){
        return this.next() != null || this.altNext() != null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node) {
            Node node = (Node) obj;
            return this.getData().equals(node.getData());
        }
        return false;
    }
}