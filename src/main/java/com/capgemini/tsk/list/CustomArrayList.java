package com.capgemini.tsk.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * List based on the table
 *
 * @param <T>
 */
public class CustomArrayList<T> {

    private static final int DEFAULT_LIST_CAPACITY = 10;
    private static final double MAX_LIMIT_BEFORE_ARRAY_RESIZE = 0.9;
    private static final double MIN_LIMIT_BEFORE_ARRAY_RESIZE = 0.6;
    private double capacityResizeFactor;
    private Object[] customArray;
    private int size;

    public CustomArrayList() {
        this(DEFAULT_LIST_CAPACITY);
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            customArray = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("IllegalCapacity: " + initialCapacity);
        }
        size = 0;
        capacityResizeFactor = MIN_LIMIT_BEFORE_ARRAY_RESIZE
                + ((MAX_LIMIT_BEFORE_ARRAY_RESIZE - MIN_LIMIT_BEFORE_ARRAY_RESIZE) / 2);
    }

    public int size() {
        return size;
    }

    public int getInternalCapacity() {
        return customArray.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public Iterator<T> iterator() {
        return new CustomArrayListIterator<>();
    }

    public boolean add(T t) {
        if (customArray.length == 0) {
            customArray = new Object[1];
        } else {
            double percentageOfUsedCapacity = (double) (size + 1) / (double) customArray.length;
            if (percentageOfUsedCapacity >= MAX_LIMIT_BEFORE_ARRAY_RESIZE) {
                Object[] newArray = resize(size + 1);
                System.arraycopy(customArray, 0, newArray, 0, size);
                customArray = newArray;
            }
        }
        customArray[size] = t;
        size++;
        return true;
    }

    /**
     * Resize the length of the customArray when the size is equal or greater than
     * MAX_LIMIT_BEFORE_ARRAY_RESIZE or smaller than MIN_LIMIT_BEFORE_ARRAY_RESIZE
     * of customArray capacity.
     */
    private Object[] resize(int newSize) {
        int newCapacity = (int) Math.ceil((double) newSize / capacityResizeFactor);
        return new Object[newCapacity];
    }

    /**
     * Returns true if the percentage of customArray capacity exceed or is equal to
     * MIN_LIMIT_BEFORE_ARRAY_RESIZE
     *
     * @param percentageOfUsedCapacity - percentage of customArray capacity
     * @return true if the percentageOfUsedCapacity is greater or equal
     * MIN_LIMIT_BEFORE_ARRAY_RESIZE
     */
    private boolean isSizeInProperRange(double percentageOfUsedCapacity) {
        return percentageOfUsedCapacity >= MIN_LIMIT_BEFORE_ARRAY_RESIZE;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        remove(index);
        return true;
    }

    public void clear() {
        customArray = new Object[DEFAULT_LIST_CAPACITY];
    }

    /**
     * Generate a universal message for IndexOutOfBoundsException
     *
     * @param index - index of CustomArrayList element
     * @return universal IndexOutOfBoundsException message
     */
    private String indexOutOfBoundsMessage(int index) {
        return "Index " + index + " out of bound for length " + size;
    }

    public T get(int index) {
        if (checkIndexBoundaryConditions(index)) {
            throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
        }
        return (T) customArray[index];
    }

    /**
     * Checks if the index is within CustomArrayList bounds
     *
     * @param index - index of CustomArrayList
     */
    private boolean checkIndexBoundaryConditions(int index) {
        return index >= size || index < 0;
    }

    public T set(int index, T element) {
        if (checkIndexBoundaryConditions(index)) {
            throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
        }
        T elementToReturn = (T) customArray[index];
        customArray[index] = element;
        return elementToReturn;
    }

    public void add(int index, T element) {
        if (index == size) {
            add(element);
        } else {
            if (checkIndexBoundaryConditions(index)) {
                throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
            }
            double percentageOfUsedCapacity = (double) (size + 1) / (double) customArray.length;
            if (percentageOfUsedCapacity >= MAX_LIMIT_BEFORE_ARRAY_RESIZE) {
                Object[] newArray = resize(size + 1);
                System.arraycopy(customArray, 0, newArray, 0, index);
                System.arraycopy(customArray, index, newArray, index + 1, size - index);
                customArray = newArray;
            } else {
                System.arraycopy(customArray, index, customArray, index + 1, size - index);
            }
            customArray[index] = element;
            size++;
        }
    }

    public T remove(int index) {
        if (checkIndexBoundaryConditions(index)) {
            throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
        }
        T elementToReturn = (T) customArray[index];
        double usedCapacity = (double) (size - 1) / (double) customArray.length;
        double oldUsedCapacity = (double) (size) / (double) customArray.length;
        if (oldUsedCapacity >= MIN_LIMIT_BEFORE_ARRAY_RESIZE && usedCapacity < MIN_LIMIT_BEFORE_ARRAY_RESIZE) {
            Object[] newArray = resize(size - 1);
            System.arraycopy(customArray, 0, newArray, 0, index);
            System.arraycopy(customArray, index + 1, newArray, index, size - index);
            customArray = newArray;
        } else {
            System.arraycopy(customArray, index + 1, customArray, index, size - index);
        }
        size--;
        return elementToReturn;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (customArray[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(customArray[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Iterator for CustomArrayList
     */
    private class CustomArrayListIterator<E> implements Iterator<E> {

        private int cursor = 0;
        private boolean flag = false;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            flag = true;
            return (E) customArray[cursor++];
        }

        @Override
        public void remove() {
            if (!flag) {
                throw new IllegalStateException();
            }
            cursor--;
            CustomArrayList.this.remove(cursor);
        }

    }

}
