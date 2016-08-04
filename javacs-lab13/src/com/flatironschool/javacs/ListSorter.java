/**
 * 
 */
package com.flatironschool.javacs;

import java.util.*;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
		if (list.size() <= 1) {
			return list;
		}

		List<T> firstHalf = mergeSort(list.subList(0, list.size()/2), comparator);
		List<T> secondHalf = mergeSort(list.subList(list.size()/2, list.size()), comparator);
		List<T> result = new ArrayList<T>();

		int i = 0, j = 0;
		while (i < firstHalf.size() && j < secondHalf.size()) {
			if (comparator.compare(firstHalf.get(i), secondHalf.get(j)) < 0) {
				result.add(firstHalf.get(i++));
			} else {
				result.add(secondHalf.get(j++));
			}
		}

		while (i < firstHalf.size()) {
			result.add(firstHalf.get(i++));
		}

		while (j < secondHalf.size()) {
			result.add(secondHalf.get(j++));
		}

        return result;
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
		PriorityQueue<T> pq = new PriorityQueue<T>();

		for (T item : list) {
			pq.offer(item);
		}

		int listSize = list.size();
		list.clear();

		for (int i = 0; i < listSize; i++) {
			list.add(pq.poll());
		}
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
		PriorityQueue<T> pq = new PriorityQueue<T>();

		for (T item : list) {
			if (pq.size() < k) {
				pq.offer(item);
			} else if (comparator.compare(pq.peek(), item) < 0) {
				pq.poll();
				pq.offer(item);
			}
		}

		List<T> result = new ArrayList<T>();
		int pqSize = pq.size();
		for (int i = 0; i < pqSize; ++i) {
			result.add(pq.poll());
		}

        return result;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
