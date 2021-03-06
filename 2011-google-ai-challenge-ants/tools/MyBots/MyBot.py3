#!/usr/bin/env python
from ants import *
'''
class Ant(object):
    def __init__(self, loc, map):
        self.duty = None
        self.loc = loc
        self.map = map
    def move(self, path):
        def duty():
            if len(path) == 0:
                return None
            else:
                return path.pop(0)
        self.duty = duty
'''
# define a class with a do_turn method
# the Ants.run method will parse and update bot input
# it will also run the do_turn method for us
class MyBot:
    DEBUG = True

    def out(self, s):
        if MyBot.DEBUG:
            self.debug.write(s + "\n")
            

    def __init__(self):
        # define class level variables, will be remembered between turns
        self.debug = open("debug.txt", 'w')
        self.out("HELO")
        self.debug.close()
    # do_setup is run once at the start of the game
    # after the bot has received the game settings
    # the ants class is created and setup by the Ants.run method
    def do_setup(self, ants):
        # initialize data structures after learning the game settings
        # (dist from hill, previous loc)
        self.dist = [[(-1, None) for _ in range(ants.cols)] for __ in range(ants.rows)]
        self.ants = ants
        #self.orders = []
        self.frontiers = []
        self.first_turn = True
        self.turns = 0
    
    def wrap(self, loc):
        return (loc[0] + self.ants.rows) % self.ants.rows, (loc[1] + self.ants.cols) % self.ants.cols

    def dist_with_bfs(self):
        #self.out("HILLS: " + str(ants.my_hills()))
        frontier = []
        for row, col in ants.my_hills():
            self.dist[row][col] = (0, None)
            frontier.append((row, col))
        
        while frontier != []:
            row, col = frontier.pop(0)
            for delta in AIM.values():
                new_row, new_col = self.wrap((row + delta[0], col + delta[1]))
                if self.dist[new_row][new_col][0] == -1:
                    if ants.map[new_row][new_col] != WATER:
                        self.dist[new_row][new_col] = (self.dist[row][col][0] + 1, (row, col))
                        frontier.append((new_row, new_col));

    #fill self.dist for visible squares
    #leave self.frontiers filled with invisible frontiers that must be searched next time.
    # TODO: fix the way dist is updated. There might be shorter path, so even if dist == -1, update.
    def bfs(self):
        for row, col in list(self.frontiers):
            if self.ants.visible((row, col)) and self.ants.map[row][col] == WATER:
                self.dist[row][col] = (0, None)
                self.frontiers.remove((row, col))

        new_frontiers = []

        while self.frontiers != []:
            row, col = self.frontiers.pop(0)
            for delta in AIM.values():
                new_row, new_col = self.wrap((row + delta[0], col + delta[1]))
                if self.dist[new_row][new_col][0] == -1:
                    if self.ants.visible((new_row, new_col)):
                        if self.ants.map[new_row][new_col] != WATER:
                            self.dist[new_row][new_col] = (self.dist[row][col][0] + 1, (row, col))
                            self.frontiers.append((new_row, new_col))
                    else:
                        if not (new_row, new_col) in new_frontiers:
                            new_frontiers.append((new_row, new_col))
                            self.dist[new_row][new_col] = (self.dist[row][col][0] + 1, (row, col))
        self.frontiers = new_frontiers
                
        '''
        self.unseen = []
        for row in range(ants.rows):
            for col in range(ants.cols):
                self.unseen.append((row, col))

        self.hills = []
        '''
        '''
        self.role = [[None for _ in ants.cols] for __ in ants.rows]
        self.workers = []
        self.fighters = []
        '''
    '''
    Resources:
    ants.map[row][col] -> ANTS(0), DEAD(-1), LAND(-2), FOOD(-3), WATER(-4)
    ants.food() -> [(row, col), ... ]
    ants.enemy_ants() 
    ants.my_ants() -> [(row, col), ...]
    ants.enemy_hills() -> [(row, col), ...]
    ants.my_hills()
    ants.visible((row, col)) ->
    ants.dead_list
    '''
    def near(self, loc, type):
        visited = [[False for _ in range(self.ants.cols)] for __ in range(self.ants.rows)]
        frontier = [(loc[0], loc[1], [])] 
        while frontier != []:
            row, col, path = frontier.pop(0)
            visited[row][col] = True
            if self.ants.map[row][col] == type:
                return (row, col), path
            for dir, delta in AIM.items():
                new_row, new_col = self.wrap((row + delta[0], col + delta[1]))
                if not visited[new_row][new_col] and self.ants.visible((row, col)) and self.ants.map[new_row][new_col] != WATER:
                    frontier.append((new_row, new_col, path + [dir]))
        return None


    
    
    def do_turn(self, ants):
        self.turns += 1
        self.out("Turn #%d:"%(self.turns))
        orders = {}
        food = {}
        if self.turns == 1:
            for row, col in ants.my_hills():
                #self.out('%d %d'%(row, col))
                self.dist[row][col] = (0, None)
                self.frontiers.append((row, col))
        #calculate shortest path
        self.bfs()

        #assign food ant
        searchs = []
        for row, col in ants.food():
            search = self.near((row, col), MY_ANT)
            if search is not None:
                searchs.append(search)
                
        searchs = sorted(filter(lambda s: len(s[1]) != 0, searchs), key = lambda s: len(s[1]))
        
        for search in searchs:
            (row, col), to_ant_path = search
            #to_food_path = list(map(lambda dir: BEHIND[dir], to_ant_path[::-1]))
            
            dir = BEHIND[to_ant_path[-1]]
            delta = AIM[dir]
            new_row = row + delta[0]
            new_col = col + delta[1]
            if (new_row, new_col) not in orders:
                #self.out("%d %d %s"%(new_row, new_col, str(dir)))
                ants.issue_order(((row, col), dir))
                orders[(new_row, new_col)] = (row, col)
        

        # assign explorer
        # explorer ant
        '''
        goals = []
        for row, col in self.frontiers:
            goal = self.near((row, col), MY_ANT)
            if goal is not None:
                goals.append(goal)
        goals = sorted(key = lambda s: len(s[1]))
        
        for goal in goals:
            (row, col), to_ant_path = goal
            self.out("Turn #%d: %s"%(self.turns, str(goal)))

            dir = BEHIND[to_ant_path[-1]]
            delta = AIM[dir]
            new_row = row + delta[0]
            new_col = col + delta[1]

            if (new_row, new_col) not in orders:
                ants.issue_order((row, col), dir)
                orders[(new_row, new_col)] = (row, col)
        '''
        '''
        if self.first_turn:
            for d in self.dist:
                self.out(' '.join(map(str, d)))
        '''
        '''
        for loc in ants.food():
            result = self.near(loc, MY_ANT)
            if result != None:
                path = list(map(lambda d: BEHIND[d], result[1][::-1]))
                orders.append(Ant(result[0], self.map))
        '''     
        '''
        for loc in ants.my_ants():
            row, col = loc

            
                
            if self.role[row][col] == None:
                if len(self.fighters) < self.num_fighter_needed:
                    self.make_fighter(row, col)
                self.make_worker(row, col)
       '''     

        '''
        # track all moves, prevent collisions
        orders = {}
        def do_move_direction(loc, direction):
            new_loc = ants.destination(loc, direction)
            if (ants.unoccupied(new_loc) and new_loc not in orders):
                ants.issue_order((loc, direction))
                orders[new_loc] = loc
                return True
            else:
                return False
        targets = {}
        def do_move_location(loc, dest):
            directions = ants.direction(loc, dest)
            for direction in directions:
                if do_move_direction(loc, direction):
                    targets[dest] = loc
                    return True
                    break
            else:
                return False
            
        # default move
        # find close 
        ant_dist = []
        for food_loc in ants.food():
            for ant_loc in ants.my_ants():
                dist = ants.distance(ant_loc, food_loc)
                ant_dist.append((dist, ant_loc, food_loc))
        ant_dist.sort()

        # attack hills
        for hill_loc, hill_owner in ants.enemy_hills():
            if hill_loc not in self.hills:
                self.hills.append(hill_loc)        
        ant_dist2 = []
        for hill_loc in self.hills:
            for ant_loc in ants.my_ants():
                if ant_loc not in orders.values():
                    dist = ants.distance(ant_loc, hill_loc)
                    ant_dist2.append((dist, ant_loc))
        ant_dist2.sort()
        for dist, ant_loc in ant_dist2:
            do_move_location(ant_loc, hill_loc)

        # prevent stepping on own hill
        for hill_loc in ants.my_hills():
            orders[hill_loc] = None

        for dist, ant_loc, food_loc in ant_dist:
            if food_loc not in targets and ant_loc not in targets.values():
                do_move_location(ant_loc, food_loc)
                
        # explore unseen areas
        for loc in self.unseen[:]:
            if ants.visible(loc):
                self.unseen.remove(loc)
        for ant_loc in ants.my_ants():
            if ant_loc not in orders.values():
                unseen_dist = []
                for unseen_loc in self.unseen:
                    dist = ants.distance(ant_loc, unseen_loc)
                    unseen_dist.append((dist, unseen_loc))
                unseen_dist.sort()
                for dist, unseen_loc in unseen_dist:
                    if do_move_location(ant_loc, unseen_loc):
                        break

        # unblock own hill
        for hill_loc in ants.my_hills():
            if hill_loc in ants.my_ants() and hill_loc not in orders.values():
                for direction in ('s','e','w','n'):
                    if do_move_direction(hill_loc, direction):
                        break
      '''
           
if __name__ == '__main__':
    # psyco will speed up python a little, but is not needed
    try:
        import psyco
        psyco.full()
    except ImportError:
        pass
    
    try:
        # if run is passed a class with a do_turn method, it will do the work
        # this is not needed, in which case you will need to write your own
        # parsing function and your own game state class
        Ants.run(MyBot())
        #print(AIM)
    except KeyboardInterrupt:
        print('ctrl-c, leaving ...')
