package capcs.choi.yr20092010.round2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 
 * @author Wonjohn Choi
 * @DWITE_Problem_Page http://dwite.ca/questions/portals_redux.html
 */
public class Q5 {
	/**
	 * variables
	 */
	private int row, col;
	private char[][] map;
	private final char WALL = '#', OPEN = '.';
	private ArrayList<Room> rooms = new ArrayList<Room>();
	
	/**
	 * main
	 */
	public static void main(String args[]){
		new Q5();
		
	}
	
	/**
	 * recursively search a room and fill it with '#'
	 * @param row
	 * @param col
	 */
	public void searchAndFill(int row, int col){
		if(isRowRange(row) && isColRange(col)){
			if(map[row][col]!=WALL){
				Room curRoom = rooms.get(rooms.size()-1);
				if(Q5.isPointOfInterest(map[row][col])){
					curRoom.pointOfInterest = map[row][col];
				}else if(Q5.isEntrance(map[row][col])){
					curRoom.entrances.add(map[row][col]);
				}else if(Q5.isExit(map[row][col])){
					curRoom.exits.add(map[row][col]);
				}else if(map[row][col]==OPEN){
					//nothing
				}
				
				map[row][col] = WALL;
				
				searchAndFill(row-1,col);
				searchAndFill(row,col-1);
				searchAndFill(row+1,col);
				searchAndFill(row, col+1);
			}
			
		}
	}
	
	/**
	 * constructor
	 */
	public Q5(){
		getInput();
		
		//get information on rooms
		for(int r=0;r<row;r++){
			for(int c=0;c<col;c++){
				if(map[r][c]!=WALL){
					rooms.add(new Room());
					searchAndFill(r,c);
				}
			}
		}
		
		//sort room by its interests
		Collections.sort(rooms);
		 
		PrintWriter pw=null;
		try {
			pw = new PrintWriter(new FileWriter("OUT5.txt"));
		} catch (IOException e) {
			System.exit(0);
			e.printStackTrace();
		}
		
		//for each room
		for(Room room: rooms){
			if(room.pointOfInterest!=Character.MAX_VALUE){
				String out = room.pointOfInterest+":";
				ArrayList<Room> reachedRooms = new ArrayList<Room>();
				reachRooms(reachedRooms, room);
				
				Collections.sort(reachedRooms);
				
				for(Room reachedRoom: reachedRooms){
					if(reachedRoom!=room){
						out+=reachedRoom.pointOfInterest+" ";
					}
				}
				
				pw.println(out.trim());
			}
			
			
		}
		
		pw.close();
	}
	
	/**
	 * resursively reach possible rooms (prevent infinite loop)
	 */
	public void reachRooms(ArrayList<Room> reachedRooms, Room cur){
		//if current room is already visited, exit function
		for(Room room: reachedRooms){
			if(room==cur){
				return;
			}
		}
		
		reachedRooms.add(cur);
		
		for(Character entrance: cur.entrances){
			char curExit =Character.toUpperCase(entrance);
			
			for(Room room: rooms){
				for(Character exit: room.exits){
					if(curExit == exit){
						reachRooms(reachedRooms, room);
						break;
					}
					
				}
			}
		}
	}
	
	public boolean isRowRange(int r){
		return 0<=r && r<row;
	}
	
	public boolean isColRange(int c){
		return 0<=c && c<col;
	}
	
	public static boolean isEntrance(char space){
		return 'a'<=space && space<='j';
	}
	
	public static boolean isExit(char space){
		return 'A'<=space && space<='J';
	}
	
	public static boolean isPointOfInterest(char space){
		return '1'<=space && space<='5';
	}
	
	/**
	 * get input (map)
	 */
	public void getInput() {
		Scanner sc = null;
		
		try {
			sc = new Scanner(new FileReader("DATA5.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		row = sc.nextInt(); 
		col= sc.nextInt();
		
		map = new char[row][col]; //create map
		
		//get map information
		for(int r=0;r<row;r++){
			map[r] = sc.next().toCharArray();
		}
		
		sc.close();
	}
	
	static class Room implements Comparable<Room>{
		char pointOfInterest = Character.MAX_VALUE;
		ArrayList<Character> entrances = new ArrayList<Character>();
		ArrayList<Character> exits = new ArrayList<Character>();
		
		@Override
		public int compareTo(Room o) {
			return this.pointOfInterest - o.pointOfInterest;
		}
	}
}