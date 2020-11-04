package main;

import java.util.Arrays;
import java.util.Collections;

public class sortTest {

	public static void main(String[] args) {
//		int[] arr = {1, 26, 17, 25, 99, 44, 303}; //오름차순
//
//		Arrays.sort(arr);
//
//		System.out.println("정렬 : " + Arrays.toString(arr));
		
		Integer[] arr = {1, 26, 17, 25, 99, 44, 303}; //내림차순

		Arrays.sort(arr, Collections.reverseOrder());

		System.out.println("정렬 : " + Arrays.toString(arr));
	}

}
