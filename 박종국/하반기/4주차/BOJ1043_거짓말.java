package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.*;


public class BOJ_1043_거짓말 {
	static int N, M, initialTruthSize, cnt;
	static int[] parents;
	static boolean[] visited;
	static ArrayList<Integer> truth = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> parties = new ArrayList<>();
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		initialTruthSize = Integer.parseInt(st.nextToken());

		if(initialTruthSize == 0) {
			System.out.println(M);
			return;
		}

		parents = new int[N+1];
		visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) parents[i] = i;

		while(st.hasMoreTokens()) truth.add(Integer.parseInt(st.nextToken()));

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			ArrayList<Integer> party = new ArrayList<>();
			int size = Integer.parseInt(st.nextToken());
			for(int j = 0; j < size; j++) party.add(Integer.parseInt(st.nextToken()));
			parties.add(party);
		}

		for(ArrayList<Integer> party : parties) {
			for(int i = 0; i < party.size()-1; i++){
				int a = party.get(i);
				int b = party.get(i+1);
				if(find(a) == find(b)) continue;
				union(a, b);
			}
		}

		for(ArrayList<Integer> party : parties) {
			if(isPersonWhoKnows(party)) continue;
			cnt++;
		}
		System.out.println(cnt);

	}

	static boolean isPersonWhoKnows(ArrayList<Integer> party) {
		for(int people: party) if(truth.contains(find(parents[people]))) return true;
		return false;
	}

	static int find(int a){
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}

	static void union(int a, int b){
		int rootA = find(a);
		int rootB = find(b);
		if(truth.contains(rootB)) {
			int tmp = rootA;
			rootA = rootB;
			rootB = tmp;
		}
		parents[rootB] = rootA;
	}
}
