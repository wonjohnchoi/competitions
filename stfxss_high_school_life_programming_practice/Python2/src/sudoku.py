# Copyright Peter J.A. Cock, 2006
# All rights reserved.
# 
# You may choose to be bound by either:
#
# (a) Licenced free for personal non-commerial use.
#      May not be redistributed without prior permission.
#
# Or:
# (b) The GPL version 2, see http://www.gnu.org/licenses/gpl.html

RUN_BUILT_IN_TESTS=True
RUN_TEST_FILES=True

TRIPLETS = [[0,1,2],[3,4,5],[6,7,8]]

#Row/Col/3x3 iteration list, each is nine lists of nine (row,col) pairs
ROW_ITER = [[(row,col) for col in range(0,9)] for row in range(0,9)]
COL_ITER = [[(row,col) for row in range(0,9)] for col in range(0,9)]
TxT_ITER = [[(row,col) for row in rows for col in cols] for rows in TRIPLETS for cols in TRIPLETS]

class sudoku:
    def __init__(self, start_grid=None) :
        #Setup list of lists (the rows), each row is a list of 9 cells, which are each a list of integers 1-9 inclusive.
        self.squares =[ [range(1,10)  for col in range(0,9)] for row in range(0,9)]
        
        if start_grid is not None:
            if len(start_grid)==81 :
                for row in range(0,9) :
                    self.set_row(row, start_grid[(row*9):((row+1)*9)])
            else :
                assert len(start_grid)==9, "Bad input!"
                for row in range(0,9) :
                    self.set_row(row, start_grid[row])
                
        #self.check()
        self._changed=False

    def solved(self) :
        for row in range(0,9) :
            for col in range(0,9) :
                if len(self.squares[row][col]) > 1 :
                    return False
        return True

    def copy(self) :
        sudoku_copy = sudoku(None)
        for row in range(0,9) :
            for col in range(0,9) :
                sudoku_copy.squares[row][col] = self.squares[row][col][:] #copy!
        sudoku_copy._changed=False
        return sudoku_copy
    
    def set_row(self,row, x_list) :
        assert len(x_list)==9
        for col in range(0,9) :
            try :
                x = int(x_list[col])
            except :
                x = 0
            #self.set_cell(row,col,x)
            self.set_cell(row,col,x)

    def set_cell(self,row,col,x):
        if self.squares[row][col] == [x] :
            #Already done!
            pass
        elif x not in range(1,9+1) :
            #Set to unknown
            pass
        else:
            assert x in self.squares[row][col], \
            "Told to set square (%i,%i) to an impossible entry, %i" % (row,col,x)
            
            self.squares[row][col] = [x]
            self.update_neighbours(row,col,x)
            self._changed=True
            
    def cell_exclude(self, row,col,x) :
        assert x in range(1,9+1)
        if x in self.squares[row][col] :
            #Remove it...
            self.squares[row][col].remove(x)
            #Should be one or more entries left...
            assert len(self.squares[row][col]) > 0, \
            "Removed last possible entry for square (%i,%i) which was %i" \
            % (row, col, x)
            #Now, has this confirmed the value for this square?
            if len(self.squares[row][col]) == 1 :
                #This cell is now definate..
                #Need to update its friends...
                #print "After exluding %i, square (%i,%i) must be %i" \
                #% (x, self.row, self.col, self[0])
                self._changed=True
                self.update_neighbours(row,col,self.squares[row][col][0])
        else :
            #Don't need to remove this, already done!
            pass
        return

    def row_exclude(self, row, x) :
        assert x in range(1,9+1)
        for col in range(0,9) :
            self.cell_exclude(row,col,x)

    def col_exclude(self, col, x) :
        assert x in range(1,9+1)
        for row in range(0,9) :
            self.cell_exclude(row,col,x)

    def update_neighbours(self,set_row,set_col,x) :
        """Call this when the square is set to x, either directly,
        or as a side effect of an exclude leaving only one entry"""
        #print "Updating (%i,%i) to be %i..."  % (self.row, self.col, x)
        #Update the possibilies in this row...
        for row in range(0,9) :
            if row <> set_row :
                self.cell_exclude(row,set_col,x)
        #Update the possibilies in this col...
        for col in range(0,9) :
            if col <> set_col :
                self.cell_exclude(set_row,col,x)
        #Update the possibilies in this 3x3 square...
        for triplet in TRIPLETS :
            if set_row in triplet : rows = triplet[:]
            if set_col in triplet : cols = triplet[:]
        #Only need to do four of the eight possibles (well, 9 if you count the cell itself)
        #as did two on the row, and two on the col
        rows.remove(set_row)
        cols.remove(set_col)
        for row in rows :
            for col in cols :
                assert row <> set_row or col <> set_col 
                #print "Updating (%i,%i) to be %i, excluding %i from (%i, %i)" \
                #% (self.row, self.col, x, x, row, col)
                self.cell_exclude(row,col,x)
            
    def get_cell_int(self,row,col) :
        if len(self.squares[row][col])==1 :
            return int(self.squares[row][col][0])
        else :
            return 0

    def get_cell_str(self,row,col) :
        if len(self.squares[row][col])==1 :
            return "(%i,%i) = %i" % (row, col, self.squares[row][col][0])
        else :
            return ("(%i,%i) = " % (row, col)) + ",".join([str(x) for x in self.squares[row][col]]) 

    def get_cell_digit_str(self,row,col) :
        if len(self.squares[row][col])==1 :
            return str(self.squares[row][col][0])
        else :
            return "0"
            
    def as_test_81(self) :
        """Return a string of 81 digits"""
        return  "".join(self.as_test_list())
            
    def simple_text(self) :
        return  "\n".join(self.as_test_list())
        
    def as_test_list(self) :
        return  [  ("".join( [self.get_cell_digit_str(row,col) for col in range(0,9)]))  for row in range(0,9) ]
        """
        answer=[]
        for row in range(0,9) :
            line=""
            for col in range(0,9) :
                line = line + self.get_cell_digit_str(row,col)
            answer.append(line)
        return answer
        """

    def __repr__(self):
        answer="[" + ",".join([ \
            ("[" + ",".join( [self.get_cell_digit_str(row,col) for col in range(0,9)]) + "]") \
            for row in range(0,9) ])
        return answer

    def __str__(self):
        answer = "   123   456   789\n"
        for row in range(0,9) :
            answer = answer + str(row+1) \
                        +   " [" + "".join([self.get_cell_digit_str(row,col).replace("0","?") for col in range(0,3)]) \
                        + "] [" + "".join([self.get_cell_digit_str(row,col).replace("0","?") for col in range(3,6)]) \
                        + "] [" + "".join([self.get_cell_digit_str(row,col).replace("0","?") for col in range(6,9)]) \
                        + "]\n"
            if row+1 in [3,6] : 
              answer = answer + "   ---   ---   ---\n"
        return answer
                    
    def check(self, level=0) :
        self._changed=True
        while self._changed:
            #print "checking..."
            self._changed=False
            self.check_for_single_occurances()
            self.check_for_last_in_row_col_3x3()
            if level >= 1 :
                self.overlapping_3x3_and_row_or_col() #(aka slicing and dicing)
            if level >= 2 :
                self.one_level_supposition()
            if level >= 3 :
                self.two_level_supposition()
            
            #If nothing happened, then self.changed==False (still)
            #and we break the loop
        return
        
    def check_for_single_occurances(self):
        #Want to see if x only occurs once in this row/col/3x3...
        for check_type in [ROW_ITER, COL_ITER, TxT_ITER]:
            for check_list in check_type :
                for x in range(1,9+1) : #1 to 9 inclusive
                    x_in_list = []
                    for (row,col) in check_list :
                        if x in self.squares[row][col] :
                            x_in_list.append((row,col))
                    if len(x_in_list)==1 :
                        (row,col) = x_in_list[0]
                        #This position MUST be be x
                        if len(self.squares[row][col]) > 1 :
                            self.set_cell(row,col,x)

    def check_for_last_in_row_col_3x3(self):
        #Now, for each row/col/3x3 want to see if there is a single
        #unknown entry...
        for (type_name, check_type) in [("Row",ROW_ITER),("Col",COL_ITER),("3x3",TxT_ITER)]:
            for check_list in check_type :
                unknown_entries = []
                unassigned_values = range(1,9+1) #1-9 inclusive
                known_values = []
                for (row,col) in check_list :
                    if len(self.squares[row][col]) == 1 :
                        assert self.squares[row][col][0] not in known_values, \
                        "Already have %i (%i,%i) in known list [%s] for %s" % (self.squares[row][col][0],row,col, ",".join(map(str,known_values)), type_name)

                        known_values.append(self.squares[row][col][0])

                        assert self.squares[row][col][0] in unassigned_values, \
                        "Expected %i (%i,%i) in list [%s] for %s" % (self.squares[row][col][0],row,col, ",".join(map(str,unassigned_values)), type_name)

                        unassigned_values.remove(self.squares[row][col][0])
                    else :
                        unknown_entries.append((row,col))
                assert len(unknown_entries) + len(known_values) == 9
                assert len(unknown_entries) == len(unassigned_values)
                if len(unknown_entries) == 1 :
                    #This cell must be the only number 1-9 not in known_values
                    x = unassigned_values[0]
                    (row,col) = unknown_entries[0]
                    
                    #assert x not in known_values

                    #print "Because its the last cell in its row/col/3x3 entry (%i,%i) must be %i" \
                    #% (row,col,x)
                    self.set_cell(row,col,x)
        """
        for row in range(0,9) : self.check_row(row)
        for col in range(0,9) : self.check_col(col)
        #Check the 3x3 squares...
        for rows in TRIPLETS :
            for cols in TRIPLETS :
                for x in range(0,9) :
                    x_in_location=[]
                    for row in rows:
                        for col in cols :
                            if x in self.squares[row][col] :
                                x_in_location.append((row,col))
                    if len(x_in_location)==1 :
                        (row,col) = x_in_location[0]
                        #This position MUST be be x
                        if len(self.squares[row][col]) > 1 :
                            self.set_cell(row,col,x)
        """
        return
        
    def diagnosis(self) :
        answer=""
        df = long(1)
        for row in range(0,9) :
            for col in range(0,9):
                if len(self.squares[row][col]) > 1 :
                    answer = answer + str(self.squares[row][col]) + "\n"
                    df = df * len(self.squares[row][col])
        answer = answer + "Degrees of freedom: %i" % df
        return answer

    def overlapping_3x3_and_row_or_col(self):
        """Block and Column / Row Interactions (name from Simon Armstrong)

        http://www.simes.clara.co.uk/programs/sudokutechnique3.htm

        Also known as 'slicing and dicing'
        """
        #For a given 3x3, and a given digit, want to see if
        #all the remaining candidates are in a single row or column..
        #Want to see if x only occurs once in this row/col/3x3...
        for check_list in TxT_ITER :
            for x in range(1,9+1) : #1 to 9 inclusive
                #print "Checking %i in 3x3" % x, check_list
                rows_for_x = []
                cols_for_x = []
                for (row,col) in check_list :
                    if x in self.squares[row][col] :
                        #print "Found possible %i at (%i,%i)" % (x, row, col)
                        if row not in rows_for_x : rows_for_x.append(row)
                        if col not in cols_for_x : cols_for_x.append(col)
                #Are they all in the same row?
                if len(rows_for_x)==1 and len(cols_for_x) > 1 :
                    #print "%i must be in row %i using cols %s" % (x, rows_for_x[0]+1, ",".join(map(lambda i : str(i+1),cols_for_x)))
                    #print self
                    #This means, we can remove X from all the rest of the row...
                    row = rows_for_x[0]
                    for col in range(0,9) :
                        if col not in cols_for_x :
                            self.cell_exclude(row,col,x)
                    #We can also remove x from all the rest of this 3x3...
                    for (row,col) in check_list :
                        if col not in cols_for_x :
                            if row not in rows_for_x :
                                self.cell_exclude(row,col,x)
                #Are they all in the same col?
                if len(cols_for_x)==1 and len(rows_for_x) > 1 :
                    #print "%i must be in col %i using rows %s" % (x, cols_for_x[0]+1, ",".join(map(lambda i : str(i+1),rows_for_x)))
                    #print self
                    #This means, we can remove X from all the rest of the row...
                    col = cols_for_x[0]
                    for row in range(0,9) :
                        if row not in rows_for_x :
                            self.cell_exclude(row,col,x)
                    #We can also remove x from all the rest of this 3x3...
                    for (row,col) in check_list :
                        if col not in cols_for_x :
                            if row not in rows_for_x :
                                self.cell_exclude(row,col,x)

    def one_level_supposition(self):
        """Probably what is known as 'Nishio', try a number and see if it leads to a dead end.
        
        For all the ambigous squares, try each possible each entry and see
        if its OK, or if it leads to a contradiction.  In the case of a contradiction
        we can remove it as a possibility...
        
        Two level suppositions (two guess) may be required for extreme puzzles..."""
        progress=True
        while progress :
            progress=False
            #print "Doing one level supposition..."
            for row in range(0,9) :
                for col in range(0,9):
                    if len(self.squares[row][col]) > 1 :
                        bad_x = []
                        for x in self.squares[row][col] :
                            #print "/-- Trying setting (%i,%i) to %i" % (row,col,x)
                            sudoku_copy = self.copy()
                            try:
                                sudoku_copy.set_cell(row,col,x)
                                sudoku_copy.check(level=1)
                            except AssertionError, e :
                                #Leads to an error :)
                                #This means that this square cannot be x
                                #print e
                                #print "%s cannot be %i" % (str(self.squares[row][col]), x)
                                bad_x.append(x)
                            del sudoku_copy
                            #print "\-- End of exp"
                        if len(bad_x) == 0 :
                            pass
                        elif len(bad_x) < len(self.squares[row][col]) :
                            for x in bad_x :
                                self.cell_exclude(row,col,x)
                                self.check() 
                            progress=True
                        else :
                            assert False, "Bugger! All possible values for square (%i,%i) fail" \
                            % (row,col)
        #print "One level supposition done"
        
    def two_level_supposition(self) :
        progress=True
        while progress :
            progress=False
            #print "Doing two level supposition..."
            for row in range(0,9) :
                for col in range(0,9):
                    if len(self.squares[row][col]) > 1 :
                        bad_x = []
                        for x in self.squares[row][col] :
                            #print "/-- Trying setting (%i,%i) to %i" % (row,col,x)
                            sudoku_copy = self.copy()
                            try:
                                sudoku_copy.set_cell(row,col,x)
                                #sudoku_copy.check()
                                #sudoku_copy.one_level_supposition()
                                sudoku_copy.check(level=2)
                            except AssertionError, e :
                                #Leads to an error :)
                                #This means that this square cannot be x
                                #print e
                                #print "%s cannot be %i" % (str(self.squares[row][col]), x)
                                bad_x.append(x)
                            del sudoku_copy
                            #print "\-- End of exp"
                        if len(bad_x) == 0 :
                            pass
                        elif len(bad_x) < len(self.squares[row][col]) :
                            for x in bad_x :
                                self.cell_exclude(row,col,x)
                                self.check() 
                            progress=True
                        else :
                            assert False, "Bugger! All possible values for square (%i,%i) fail" \
                            % (row,col)
        #print "Two level supposition done"


    
if __name__ == "__main__" :
    print "Running demonstration..."
    
    t = sudoku(["800500930",
                   "050000000",
                   "000200100",
                   "007020000",
                   "600190008",
                   "400300069",
                   "000000450",
                   "104080000",
                   "000406007"])
                   
    print "Before:"
    #print t.as_test_list()
    print t.simple_text()

    print "Check:"
    t.check()
    print t

    print "overlapping_3x3_and_row_or_col:"
    t.check(level=1)
    print t

    print "supposition:"
    t.check(level=2)
    #print t.as_test_list()
    print t

