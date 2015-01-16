'''
Created on 2010. 6. 21.

@author: user
'''
class poker():
    def __init__(self, hand):
        ranks = dict((val,idx) for idx, val in enumerate('..23456789TJQKA'))
        myHand = hand.split()
        self.mySuit = [suit for rank,suit in myHand]
        self.myRank = sorted([ranks[rank] for rank,suit in myHand], reverse=True)
        self.isflush = (len(set(self.mySuit))==1)
        self.isstraight = len(self.myRank)==5 and (max(self.myRank)-min(self.myRank))==4
        self.isroyal = all(rank>10 for rank in self.myRank)

    def ofAKind(self, n, out=None):
        for rank in self.myRank:
            if rank!=out and self.myRank.count(rank)==n:
                return rank 
    
    def evaluate(self):
        if self.isroyal and self.isflush: return 10, self.myRank
        if self.isstraight and self.isflush: return 9, self.myRank
        if self.ofAKind(4): return 8, self.ofAKind(4), self.ofAKind(1)
        if self.ofAKind(3) and self.ofAKind(2): return 7, self.ofAKind(3), self.ofAKind(2)
        if self.isflush: return 6, self.myRank
        if self.isstraight: return 5, self.myRank
        if self.ofAKind(3): return 4, self.ofAKind(3), self.myRank
        if self.ofAKind(2) and self.ofAKind(2, self.ofAKind(2)):return 3, self.ofAKind(2), self.ofAKind(2, self.ofAKind(2)), self.ofAKind(1)
        if self.ofAKind(2): return 2, self.ofAKind(2), self.myRank
        return 1, self.ofAKind(1), self.myRank