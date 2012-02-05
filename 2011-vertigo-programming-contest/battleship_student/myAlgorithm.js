function Player1() 
{
    this.TeamName = "Ego";

    //OFFENSE: Target selection
    this.SelectTarget = function () {
        score = [];
        var y, x;
        for (y = 0; y < gridy; y += 1) {
            score.push([]);
            for (x = 0; x < gridx; x += 1) {
                score[y].push(0);
            }
        }
        
        var piece;
        var occupied;
        var dy;
        var dscore;
        //veritical
        for (piece = 2; piece <= 5; piece += 1) {
            for (y = 0; y < gridy - piece; y += 1) {
                for (x = 0; x < gridx; x += 1) {
                    occupied = false;
                    dscore = 1;
                    for (dy = 0; dy < piece; dy += 1) {
                        if (opponent[y + dy][x] == "Miss") {
                            occupied = true;
                        }
                        if (opponent[y + dy][x] == "Hit") {
                            dscore += 30 - dy * 3;
                        }
                    }
                    if (!occupied) {
                        for (dy = 0; dy < piece; dy += 1) {
                            score[y + dy][x] += dscore;
                        }
                    }
                }
            }
        }

        //horizontal
        for (piece = 2; piece <= 5; piece += 1) {
            for (y = 0; y < gridy; y += 1) {
                for (x = 0; x < gridx - piece; x += 1) {
                    occupied = false;
                    dscore = 1;
                    for (dx = 0; dx < piece; dx += 1) {
                        if (opponent[y][x + dx] == "Miss") {
                            occupied = true;
                        }
                        if (opponent[y][x + dx] == "Hit") {
                            dscore += 30 - dx * 3;
                        }
                    }
                    if (!occupied) {
                        for (dx = 0; dx < piece; dx += 1) {
                            score[y][x + dx] += dscore;
                        }
                    }
                }
            }
        }
    
        var bestPos, bestScore = -1;
        for (y = 0; y < gridy; y += 1) {
            for (x = 0; x < gridx; x += 1) {
                if (bestScore <= score[y][x] && opponent[y][x] == "Unknown") {
                    bestScore = score[y][x]
                    bestPos = [y, x]
                }
            }
        }

        fire(bestPos[0], bestPos[1]);
    }

    //DEFENSE: Ship Placement
    this.SetCustomShipLocations = function () {
        //Returning null yields random ship locations
                
        return null;

        //Returns ships stacked vertically
        /*
        var ships = [];
        var xposition = 0, yposition = 0;
        var direction = 0;
        var shipid = 0;

        for (shiptypeindex = 0; shiptypeindex < shiptypes.length; shiptypeindex++) 
        {
        for (i = 0; i < shiptypes[shiptypeindex][2]; ++i) 
        {
        ships[shipid] = [shiptypeindex, yposition, xposition, direction];
        shipid++;
        yposition++;
        }        
        }
        return ships;
        */
        //return [[0, 2, 3, 1], [0, 10, 2, 1], [0, 14, 13, 0], [0, 9, 14, 0], [1, 6, 0, 1], [1, 5, 13, 0], [1, 8, 6, 0], [1, 15, 2, 0], [2, 2, 11, 0], [2, 6, 0, 0], [3, 11, 8, 0]];
    }
}
