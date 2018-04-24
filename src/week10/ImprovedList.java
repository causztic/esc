package week10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ImprovedList<E> {
	private final List<E> list;
		
	public ImprovedList () {
		this.list = new ArrayList<E>();
	}
	
	public synchronized boolean putIfAbsent(E x) {
		boolean contains = list.contains(x);
		
		if (!contains) {
			list.add(x);
		}
		
		return !contains; 
	}

	public synchronized boolean add(E e) {
		return list.add(e);
	}

	public synchronized void add(int index, E element) {
		list.add(index, element);		
	}

	public synchronized boolean addAll(Collection<? extends E> c) {
		return list.addAll(c);
	}

	public synchronized boolean addAll(int index, Collection<? extends E> c) {
		return list.addAll(index, c);
	}

	public synchronized void clear() {
		list.clear();
	}

	public synchronized boolean contains(Object o) {
		return list.contains(o);
	}

	public synchronized boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public synchronized E get(int index) {
		return list.get(index);
	}

	public synchronized int indexOf(Object o) {
		return list.indexOf(o);
	}

	public synchronized boolean isEmpty() {
		return list.isEmpty();
	}

	public synchronized Iterator<E> iterator() {
		return list.iterator();
	}

	public synchronized int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public synchronized ListIterator<E> listIterator() {
		return list.listIterator();
	}

	public synchronized ListIterator<E> listIterator(int index) {
		return list.listIterator(index);
	}

	public synchronized boolean remove(Object o) {
		return list.remove(o);
	}

	public synchronized E remove(int index) {
		return list.remove(index);
	}

	public synchronized boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	public synchronized boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	public synchronized E set(int index, E element) {
		return list.set(index, element);
	}

	public synchronized int size() {
		return list.size();
	}

	public synchronized java.util.List<E> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	public synchronized Object[] toArray() {
		return list.toArray();
	}

	public synchronized <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
}