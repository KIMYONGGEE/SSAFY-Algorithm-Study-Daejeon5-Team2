package day0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class 백준_14888_연산자끼워넣기 {
	static int N, min, max;
	static int[] data;
	static int[] Operdata; // +, - *, /
	static int[] tempOperdata;
	static boolean[] select;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		data = new int[N];
		Operdata = new int[N - 1];
		tempOperdata = new int[N - 1];
		select = new boolean[N - 1];
		min = Integer.MAX_VALUE;
		max =Integer.MIN_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		int temp = 0;
		int OPdataidx = 0;
		for (int i = 0; i < 4; i++) {
			temp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < temp; j++) {
				Operdata[OPdataidx] = i + 1;
				OPdataidx++;
			}
		}
		Perm(0);
		
		System.out.println(max);
		System.out.println(min);

	}

	static void Perm(int cnt) {
		if (cnt == Operdata.length) {
			max = Math.max(max, operation());
			min = Math.min(min, operation());
			return;
		}

		for (int i = 0; i < Operdata.length; i++) {
			if (!select[i]) {
				tempOperdata[cnt] = Operdata[i];
				select[i] = true;
				Perm(cnt + 1);
				select[i] = false;
			}
		}
	}

	static int operation() {
		int temp = data[0], num = 0, Op = 0;
		int dataidx = 1, operidx = 0;
		while (true) {
			if (tempOperdata[operidx] == 1) {
				temp += data[dataidx];
				dataidx++;
				operidx++;
			} else if (tempOperdata[operidx] == 2) {
				temp -= data[dataidx];
				dataidx++;
				operidx++;
			} else if (tempOperdata[operidx] == 3) {
				temp = temp * data[dataidx];
				dataidx++;
				operidx++;
			} else if (tempOperdata[operidx] == 4) {
				temp = temp / data[dataidx];
				dataidx++;
				operidx++;
			}
			
			if(dataidx == N) break;
		}
		return temp;
	}

}
