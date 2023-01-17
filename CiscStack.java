package edu.ust.cisc;

import org.springframework.objenesis.ObjenesisException;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.*;

public class CiscStack<E> implements CiscCollection<E>{

    public static final int DEFAULT_CAPACITY = 10;
    private E[] elementData;
    private int size;

    public CiscStack() {
        this(DEFAULT_CAPACITY);
    }

    public CiscStack(int initialCapacity) {
        if(initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        elementData = (E[])new Object[initialCapacity];
        size = 0;
    }

    public E peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        E item = elementData[size-1];
        return item;
    }

    public E pop(){
       if(isEmpty()){
           throw new EmptyStackException();
       }
       E item = elementData[size - 1];
       elementData[size -  1] = null;
       size--;
       return item;
    }

    public void push(E item){
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i<size; i++){
            if(elementData[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new CiscStackIterator();
    }

    private class CiscStackIterator implements Iterator<E> {
        private int nextIndex;
        public CiscStackIterator(){
            nextIndex = size - 1;
        }
        @Override
        public boolean hasNext(){
            return nextIndex>=0;
        }
        @Override
        public E next(){
            E element = elementData[nextIndex];
            nextIndex--;
            return element;
        }
    }


    @Override
    public Object[] toArray() {
        Object[] arr = (E[]) new Object[size];
        int arrIndex = 0;
        for(int i = size-1; i>=0; i--){
            arr[arrIndex] = elementData[i];
            arrIndex++;
        }
        return arr;
    }

    @Override
    public void clear() {
        for(int i = 0; i<size; i++) {
            elementData[i] = null;
        }
        size=0;
    }
    private void ensureCapacity(int capacity) {
        if(elementData.length < capacity){
            if(elementData.length*2+1 < capacity){
                E[] newList = (E[]) new Object[capacity];
                for(int i=0; i<size; i++){
                    newList[i] = elementData[i];
                }
                elementData = newList;
            }
            else{
                E[] newList = (E[]) new Object[(elementData.length*2) +1];
                for(int i = 0; i<size; i++){
                    newList[i] = elementData[i];
                }
                elementData = newList;
            }
        }
    }
}
