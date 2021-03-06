/**
 * Represents an order to be issued.
 */
public class Order {
    public final int row;
    public final int col;
    public final int destRow;
    public final int destCol;
    
    private final char direction;
    
    /**
     * Creates new {@link Order} object.
     * 
     * @param tile map tile with my ant
     * @param direction direction in which to move my ant
     */
    /*public Order(Tile tile, Aim direction) {
        row = tile.getRow();
        col = tile.getCol();
        this.direction = direction.getSymbol();        
    }*/
    
    public Order(Tile from, Tile to, char direction) {
        row = from.getRow();
        col = from.getCol();
        destRow = to.getRow();
        destCol = to.getCol();
        this.direction = direction;
    }
    
    public boolean equals(Object o) {
        if (o instanceof Order) {
            Order ord = (Order) o;
            return (row == ord.row && col == ord.col) || (destRow == ord.destRow && destCol == ord.destCol);
        }
        return false;
    }
    
    public int hashCode() {
        return row * Ants.MAX_MAP_SIZE * 4 + col * 4 + Aim.symbolLookup.get(direction).ordinal();
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "o " + row + " " + col + " " + direction;
    }
}
