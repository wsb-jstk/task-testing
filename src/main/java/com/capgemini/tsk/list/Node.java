package com.capgemini.tsk.list;

/**
 * Component of linked list that stores a value and reference to the next
 * element.
 */
class Node {

    private Object object;
    private Node next;

    public Node(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
