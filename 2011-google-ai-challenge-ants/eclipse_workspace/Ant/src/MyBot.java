import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Starter bot implementation.
 */
public class MyBot extends Bot {
    /**
     * Main method executed by the game engine for starting the bot.
     * 
     * @param args command line arguments
     * 
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        new MyBot().readSystemInput();
    }
    
    
    class Node implements Comparable<Node> {
        int depth;
        Node prev = null;
        Node next = null;
        Tile tile;
        Node(Tile tile) {
            this(tile, 1);
        }
        Node(Tile tile, int depth) {
            this.depth = depth;
            this.tile = tile;   
        }
        
        Node next(Aim dir) {
            int maxRow = getAnts().getRows();
            int maxCol = getAnts().getCols();
            int row = tile.getRow()+dir.getRowDelta();
            int col = tile.getCol()+dir.getColDelta();
            Node n = new Node(
                    new Tile((row+maxRow) % maxRow, (col + maxCol) % maxCol), depth + 1);
            n.prev = this;
            return n;
        }
        @Override
        public int compareTo(Node n) {
            return depth - n.depth;
        }
    }
        
    /**
     * Returns nearest Tile and ITS moves needed of TYPE near LOC.
     * 
     * @param loc
     * @param type
     */
    public Node near(Tile loc, Ilk type) {
        Ants ants = getAnts();
        
        HashSet<Tile> visited = new HashSet<Tile>();
        ArrayList<Node> frontiers = new ArrayList<Node>();
        
        frontiers.add(new Node(loc));
        
        while (!frontiers.isEmpty()) {
            Node node = frontiers.remove(0);
            if (!visited.contains(node.tile) 
                && ants.getIlk(node.tile).isPassable()) {
                visited.add(node.tile);
                if (ants.getIlk(node.tile) == type) {
                    return node;
                }
                for (Aim d: Aim.values()) {
                    frontiers.add(node.next(d));
                }
            }
            
        }
        return null;
    }
    
    
    /**
     * For each visible food,
     *      Find the most close ant.
     * 			If the ant is busy, skip.
     * 		    Else Make the ant go closer to food.
     * For each unexplored tile,
     *      Find the most close ant.
     *          If the ant is busy, skip.
     *          else Make the ant go close to tile.
     * 
     */
    public void orderFood() {
        Ants ants = getAnts();
        ArrayList<Node> nodes = new ArrayList<Node>();
        for(Tile food: ants.getFoodTiles()) {
            Node node = near(food, Ilk.MY_ANT);
            if (node != null && node.prev != null) {
                nodes.add(node);
            }
        }
        Collections.sort(nodes);
        for (Node node: nodes) {
            Tile from = node.tile;
            Tile to = node.prev.tile;
            Aim dir = ants.getDirections(from, to).get(0);
            Order order = new Order(from, to, dir.getSymbol());
            if (!ants.getOrders().contains(order)) {
                ants.issueOrder(from, dir);
            }
        }
    }
    
    ArrayList<Node> frontiers = new ArrayList<Node>();
    
    public void bfs() {
        Ants ants = getAnts();
        //for(Node node: frontiers) {
            //if (ants.getIlk(tile))
            
    }
    
    public void orderExplore() {
        
    }
    @Override
    public void doTurn() {
        Ants ants = getAnts();
        orderFood();

        
        for (Tile myAnt : ants.getMyAnts()) {
            for (Aim direction : Aim.values()) {
                if (ants.getIlk(myAnt, direction).isPassable()) {
                    Tile target = new Tile((myAnt.getRow() + direction.getRowDelta() + ants.getRows()) % ants.getRows(), (myAnt.getCol() +direction.getColDelta() +ants.getCols())%ants.getCols());
                    Order ord = new Order(myAnt, target, direction.getSymbol());
                    if (!ants.getOrders().contains(ord)){//!contains(ants.getOrders(), ord)) {
                        ants.issueOrder(myAnt, direction);
                        break;
                    }
                }
            }
        }
        
    }
    
    public boolean contains(Set<Order> orders, Order order) {
        for (Order ord: orders) {
            if(ord.equals(order)) {
                return true;
            }
        }
        return false;
    }
}
