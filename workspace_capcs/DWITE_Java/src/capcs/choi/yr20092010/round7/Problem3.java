package capcs.choi.yr20092010.round7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/oil_spill_area.html
 * @author Wonjohn Choi
 *
 */
public class Problem3 {
    public static void main(String args[]) throws IOException {
        //IO
        Scanner sc = new Scanner(new FileReader("DATA3.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("OUT3.txt"));
        
        char map[][] = new char[10][];
        int destX =-1, destY=-1;
        
        //for each input
        for (int _it = 0; _it < 5; _it++) {
            
            //for each row of the map
            for (int row = 0; row < 10; row++) {
                map[row] = sc.next().toCharArray(); //get information of a row
                
                //for each column
                for (int col = 0; col < 10; col++) {
                    //if the position is area of interest
                    if (map[row][col] == 'A') {
                        //store the information
                        destY = row;
                        destX = col;
                        map[row][col] = '#';
                    }
                }
            }

            //for each row
            for (int row = 0; row < 10; row++) {
                //for each column
                for (int col = 0; col < 10; col++) {
                    //if an area is found
                    if (map[row][col] == '#') {
                        //if the current position is not connected to the destination
                        if (isDisconnected(col, row, destX, destY, map)) {
                            //if the position with water
                            kill(map, col, row);
                        }
                    }
                }
            }
            
            //process to count number of '#' in the resulting map
            int count = 0;
            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    if (map[row][col] == '#') {
                        count++;
                    }
                }
            }
            
            pw.println(count);
            sc.next();
        }

        sc.close();
        pw.close();
    }

    /**
     * recursively fill every '#' that is connected to (xPos,yPos) position with '.'

     */
    public static void kill(char map[][], int xPos, int yPos) {
        if (inRange(xPos, yPos) && map[yPos][xPos] == '#') {
            map[yPos][xPos] = '.';
            kill(map, xPos + 1, yPos);
            kill(map, xPos - 1, yPos);
            kill(map, xPos, yPos + 1);
            kill(map, xPos, yPos - 1);
        }

    }

    /**
     * check if two position is disconnected
     */
    public static boolean isDisconnected(int xPos, int yPos, int xDest, int yDest, char map[][]) {
        boolean dis = true;

        if (inRange(xPos, yPos) && map[yPos][xPos] =='#') {
            map[yPos][xPos] = '.';

            if (yPos == yDest && xPos == xDest) {
                dis = false;
            } else if (!isDisconnected(xPos + 1, yPos, xDest, yDest, map)) {
                dis = false;
            } else if (!isDisconnected(xPos - 1, yPos,xDest, yDest, map)) {
                dis = false;
            } else if (!isDisconnected(xPos, yPos + 1,xDest, yDest, map)) {
                dis = false;
            } else if (!isDisconnected(xPos, yPos - 1,xDest, yDest, map)) {
                dis = false;
            }
            
            map[yPos][xPos] ='#';
        }

        return dis;

    }

    /**
     * Check if a given coordinate is out of map
     */
    public static boolean inRange(int xPos, int yPos) {
        return 0 <= xPos && 0 <= yPos && xPos < 10 && yPos < 10;
    }
}
