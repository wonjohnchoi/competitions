import java.io.*;
import java.util.*;


class P1 {
	static PrintStream out = System.out;
	static Scanner in = new Scanner(System.in);
	public static void main(String args[]) {
		Arrays.sort(colors);
		Vector<String> lines = new Vector<String>();
		while(in.hasNextLine()) {
			lines.add(in.nextLine());
		}
		
		char[][] map = new char[lines.size()][lines.get(0).length()];
		for(int i = 0; i<lines.size();i+=1) {
			map[i] = lines.get(i).toCharArray();
		}
		
		
		for(int r=0;r<map.length;r+=1) {
			for(int c=0;c<map[0].length;c+=1) {
				boolean[][] visited = new boolean[lines.size()][lines.get(0).length()];
				HashSet<Character> found = new HashSet<Character>();
				if(Arrays.binarySearch(colors, map[r][c])<0) {
					search(map[r][c], map, r, c, found, visited);
					char color = '?';
					for(char col: colors) {
						if(!found.contains(col)){
							color = col;
						}
					}
					fill(map[r][c], map, r, c, color);
				}
			}
			
		}
		for(int r=0;r<map.length;r+=1) {
			for(int c=0;c<map[r].length;c+=1)
				out.print(map[r][c]);
			out.println();
		}
		
	}
	
	static int dir[][] = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
	static char[] colors = new char[]{'R','B','G','Y'};
	static //find highest nearby
	void search(char sym, char[][] map, int r, int c, HashSet<Character> found, boolean[][] visited){
		if(r>=map.length || r<0||c>=map[0].length || c<0) return;

		if(visited[r][c]) return;
		visited[r][c] = true;
		
		if(map[r][c] == sym) {
			for(int i = 0;i <4;i++) {
				for(int j=0;j<2;j++) {
					search(sym, map, r+dir[i][0], c+dir[i][1], found, visited);
				}
			}
		} else if(Arrays.binarySearch(colors, map[r][c])>=0) {
			found.add(map[r][c]);
		}
	}
	/*
0000
1223
1133
	 */
	static void fill(char sym, char[][] map, int r, int c, char color) {
		if(r>=map.length || r<0||c>=map[0].length || c<0) return;
		if(map[r][c] == sym) {
			map[r][c] = color;
			for(int i = 0;i <4;i++) {
				for(int j=0;j<2;j++) {
					fill(sym, map, r+dir[i][0], c+dir[i][1], color);
				}
			}
		}
	}
}
