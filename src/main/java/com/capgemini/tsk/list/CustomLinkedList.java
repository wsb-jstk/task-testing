package com.capgemini.tsk.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * List based on recursively related objects
 *
 * @param <T>
 */
public class CustomLinkedList<T> {

    private int size = 0;
    private Node head = null;
    private Node tail = null;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public Iterator<T> iterator() {
        return new CustomLinkedListIterator<>();
    }

    public boolean add(T t) {
        Node newNode = new Node(t);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
        return true;
    }

    public boolean remove(Object o) {
        Node nodeToRemove = head;
        if (o == null) {
            if (nodeToRemove.getObject() == null) { // checks if object in first Node is null
                head = head.getNext();
                size--;
                return true;
            } else {
                for (Node previousNode = head; previousNode != null; previousNode = nodeToRemove.getNext()) {
                    nodeToRemove = nodeToRemove.getNext();
                    if (nodeToRemove.getObject() == null) {
                        previousNode.setNext(nodeToRemove.getNext());
                        size--;
                        return true;
                    }
                }
            }
        } else {
            if (nodeToRemove.getObject()
                    .equals(o)) { // checks if object in first Node is equals o
                head = head.getNext();
                size--;
                return true;
            } else {
                for (Node previousNode = head; previousNode != null; previousNode = nodeToRemove.getNext()) {
                    nodeToRemove = nodeToRemove.getNext();
                    if (nodeToRemove.getObject()
                            .equals(o)) {
                        previousNode.setNext(nodeToRemove.getNext());
                        size--;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Generate a universal message for IndexOutOfBoundsException
     *
     * @param index - index of CustomLinkedList element
     * @return universal IndexoutOfBoundsException message
     */
    private String indexOutOfBoundsMessage(int index) {
        return "Index " + index + " out of bound for length " + size;
    }

    /**
     * Checks if the index is within CustomLinkedList bounds
     *
     * @param index - index of CustomLinkedList element
     */
    private boolean checkIndexBoundaryConditions(int index) {
        return index >= size || index < 0;
    }

    public T get(int index) {
        if (checkIndexBoundaryConditions(index)) {
            throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
        }
        Node tempNode = getNode(index);
        return (T) tempNode.getObject();
    }

    public T set(int index, T element) {
        if (checkIndexBoundaryConditions(index)) {
            throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
        }
        Node tempNode = getNode(index);
        T elementToReturn = (T) tempNode.getObject();
        tempNode.setObject(element);
        return elementToReturn;
    }

    public void add(int index, T element) {
        if (index == size) {
            add(element);
        } else {
            if (checkIndexBoundaryConditions(index)) {
                throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
            }
            Node newNode = new Node(element);
            if (index == 0) {
                newNode.setNext(head);
                head = newNode;
            } else {
                Node tempNode = getNodeBeforeTheIndex(index);
                newNode.setNext(tempNode.getNext());
                tempNode.setNext(newNode);
            }
            size++;
        }
    }

    /**
     * Finds Node before a given index
     *
     * @param index - index of CustomLinkedList element
     * @return Node before the specified position
     */
    private Node getNodeBeforeTheIndex(int index) {
        Node nodeToFind = head;
        for (int i = 0; i < (index - 1); i++) {
            nodeToFind = nodeToFind.getNext();
        }
        return nodeToFind;
    }

    /**
     * Finds Node at a given index
     *
     * @param index - index of CustomLinkedList element
     * @return Node at the specified position
     */
    private Node getNode(int index) {
        Node nodeToFind = head;
        for (int i = 0; i < (index); i++) {
            nodeToFind = nodeToFind.getNext();
        }
        return nodeToFind;
    }

    public T remove(int index) {
        if (checkIndexBoundaryConditions(index)) {
            throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
        }
        Node nodeToRemove = head;
        if (index == 0) {
            head = head.getNext();
        } else {
            Node tempNode = getNodeBeforeTheIndex(index);
            nodeToRemove = tempNode.getNext();
            tempNode.setNext(nodeToRemove.getNext());
        }
        size--;
        return (T) nodeToRemove.getObject();
    }

    public int indexOf(Object o) {
        Node tempNode = head;
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (tempNode.getObject() == null) {
                    return index;
                }
                tempNode = tempNode.getNext();
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(tempNode.getObject())) {
                    return index;
                }
                tempNode = tempNode.getNext();
            }
        }
        return -1;
    }

    /**
     * Iterator for CustomLinkedList
     */
    private class CustomLinkedListIterator<E> implements Iterator<E> {

        private Node cursor = head;
        private int index = 0;
        private boolean flag = false;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node nodeBeforeCursor = cursor;
            cursor = cursor.getNext();
            flag = true;
            index++;
            return (E) nodeBeforeCursor.getObject();
        }

        @Override
        public void remove() {
            if (!flag) {
                throw new IllegalStateException();
            }
            index--;
            CustomLinkedList.this.remove(index);
            flag = false;
        }

    }

}
