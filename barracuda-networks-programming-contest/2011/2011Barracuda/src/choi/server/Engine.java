package choi.server;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;



public class Engine {
    boolean[] fixed;
    int[] rack;
    int[] theirs;
    SortedSet<Integer> garbage;
    int discard = -1;
    
    int nMoves = 0;
    int totalScore = 0;
    int totalGame = 0;
    int wins = 0;
    int ties = 0;
    int loses = 0;
    int illegal;
    static int version = 9;

    public boolean isGoodForThem(int val) {
        int pre_score = evalThem(theirs);
        
        int best = Integer.MIN_VALUE;
        for(int i = 0; i < 20; i += 1) {
            int tmp = theirs[i];
            theirs[i] = val;
            best = Math.max(best, evalThem(theirs));
            theirs[i] = tmp;
        }
        int cur_score = best;
        
        return pre_score < cur_score && cur_score >= 3 && pre_score < 5;
    }
    
    public int evalThem(int[] cards) {
        int best = -1;
        int size = 1;
        for(int i = 1; i < cards.length; i += 1) {
            if(cards[i] == cards[i-1] + 1) {
                size += 1;
            } else if (size == 4 && garbage.contains(cards[i-1]+1) && (i==4 || garbage.contains(cards[i-4] - 1))) {
                size = 1;
                continue;
            } else {
                best = Math.max(best, size);
                size = 1;
            }
        }
        
        //return eval(toIntArray(cards));
        if (size == 4 && garbage.contains(cards[cards.length-1] + 1)) {
            return best;
        } else {
            return Math.max(best, size);
        }
    }
    
    public Engine() {
        reset();
    }

    public void reset() {
        illegal = 0;
        fixed = new boolean[20];
        rack = null;
        garbage = new TreeSet<Integer>();
        theirs = new int[20];
        Arrays.fill(theirs, -1);
        nMoves = 0;
    }
    public void initRack(Object[] rackO) {
        if (rack == null) {
            rack = new int[20];
            for (int i = 0; i < rack.length; i += 1) {
                int val = (Integer) rackO[i];
                int idx = insert(val);
                if(idx == i) fixed[i] = true;
                rack[i] = val;

            }
        }
    }
    
    public int insert(int val) {
        int[] b = getBorders(val);
        int sections = b[1] - b[0] + 1;
        if(b[1] == -1) return 0;
        if(b[0] == 20) return 19;
        int left = 1;
        if (b[0] != 0) {
            left = rack[b[0] - 1] + 1;
        }
        int right = 80;
        if (b[1] != 19) {
            right = rack[b[1] + 1] - 1;
        }

        if(sections == 0) {
            return better(b[0], b[1], val);

        }
        
        int idx = b[0] + (int)Math.round((b[1] - b[0]) * ((val - left + 1)/(double)(right - left + 1)));

        return idx;
    }
    public int better(int i1, int i2, int val) {
        int tmp = rack[i1];
        rack[i1] = val;
        int p1 = eval(rack);
        rack[i1] = tmp;
        
        tmp = rack[i2];
        rack[i2] = val;
        int p2 = eval(rack);
        rack[i2] = tmp;
        if (p1 >= p2) {
            return i1;
        } else{
            return i2;
        }
    }
    
    public int eval(int[] cards) {
        int best = -1;
        int size = 1;
        for(int i = 1; i < cards.length; i += 1) {
            if(cards[i] == cards[i-1] + 1) {
                size += 1;
            } else {
                best = Math.max(best, size);
                size = 1;
            }
        }
        return Math.max(best, size);
    }
        
    public int[] getBorders(int val) {
        int left = 0;
        for (int i = 0; i < rack.length; i += 1) {
            if (fixed[i]) {
                if (rack[i] < val) {
                    left = i + 1;
                } else {
                    break;
                }
            }
        }
        
        int right = 19;
        for (int i = 19; i >= 0; i -= 1) {
            if (fixed[i]) {
                if (val < rack[i]) {
                    right = i - 1;
                } else {
                    break;
                }
            }
        }
        return new int[]{left, right};
    }

    public int getIdx(int val, boolean forced) {
        
        int idx = insert(val);
        
        //evaluation of pre and new
        int preVal = eval(rack);
        int tmp = rack[idx];
        rack[idx] = val;
        int newVal = eval(rack);
        rack[idx] = tmp;
        
        if (!forced && fixed[idx] && (preVal >= newVal) && !isGoodForThem(val)) {
            return -1;
        }
        
        if(forced && (preVal > newVal)) {
            if (illegal < 2) {
                illegal += 1;
                return -2;
            }
        }
        rack[idx] = val;
        fixed[idx] = true;
        return idx;
    }
}
