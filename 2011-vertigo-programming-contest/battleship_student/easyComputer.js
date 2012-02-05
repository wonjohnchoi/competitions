function Player2() {

    this.TeamName = "Easy Computer";

    //Easy (dumb) computer player algorithm - just searches the grid squares sequentially
    //and shoots at the first unknown square it encounters
    this.SelectTarget = function () {
        var x, y;
        var sx, sy;
        var foundTarget = false;

        for (y = 0; y < gridy; ++y) {
            for (x = 0; x < gridx; ++x) {
                if ((opponent[y][x] == "Unknown") && (!foundTarget)) {
                    sx = x; sy = y;
                    foundTarget = true;
                }
            }
        }

        fire(sy, sx);
    }

    this.SetCustomShipLocations = function () {
        //Returning null yields random ship locations
        return null;
    }
}
