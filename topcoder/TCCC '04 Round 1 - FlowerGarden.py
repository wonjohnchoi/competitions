def interfere(f1, f2):
    #flower => (height, bloom, wilt)
    return not (f1[1]>f2[2] or f1[2]<f2[1])

def getOrdering(height, bloom, wilt):
    flowers = zip(height, bloom, wilt)
    
    orderedFlowers = [next(flowers)]
    
    for newFlower in flowers:
        fIdx = 0
        tIdx = len(orderedFlowers)
        
        for i, flower in enumerate(orderedFlowers):
            #print(interfere(newFlower, flower))
            if interfere(newFlower, flower):
                if newFlower[0]>flower[0]:
                    fIdx = i+1
                else:
                    tIdx = i
                    break
                    
        #print(fIdx,tIdx)

        #print(fIdx, tIdx)
        #print(fIdx, tIdx)            
        for i in range(fIdx, tIdx):
            if newFlower[0]>orderedFlowers[i][0]:
                orderedFlowers.insert(i, newFlower)
                break
        else:
            orderedFlowers.insert(tIdx, newFlower)
        print(orderedFlowers, end='\n\n')

     
            
                    
                
    
    return [flower[0] for flower in orderedFlowers]
    
print(getOrdering([5,4,3,2,1], [1,1,1,1,1], [365,365,365,365,365]))#[1, 2, 3, 4, 5]
print(getOrdering([5,4,3,2,1], [1,5,10,15,20], [4,9,14,19,24]))#[5, 4, 3, 2, 1]
print(getOrdering([5,4,3,2,1], [1,5,10,15,20], [5,10,15,20,25]))#[1, 2, 3, 4, 5]
print(getOrdering([5,4,3,2,1], [1,5,10,15,20], [5,10,14,20,25]))#[3, 4, 5, 1, 2]
print(getOrdering([1,2,3,4,5,6], [1,3,1,3,1,3], [2,4,2,4,2,4]))#[2, 4, 6, 1, 3, 5]
print(getOrdering([3,2,5,4], [1,2,11,10], [4,3,12,13]))#[4, 5, 2, 3]
print(getOrdering([397, 268, 40, 35, 676, 939, 436, 496, 206, 67, 424, 361, 352, 592, 729, 98, 778, 495, 413, 497, 582, 618, 585, 622, 942, 701, 258, 835, 963, 678, 190, 288, 567, 96, 523, 373, 851, 893, 709, 307, 221, 243, 374, 285, 667, 127, 826, 328], [311, 68, 287, 345, 343, 4, 283, 238, 79, 258, 222, 148, 206, 140, 109, 306, 182, 234, 207, 308, 140, 356, 296, 331, 201, 225, 142, 16, 109, 205, 351, 4, 269, 271, 61, 238, 163, 323, 361, 338, 335, 268, 328, 44, 323, 126, 187, 296], [363, 110, 311, 355, 360, 352, 332, 263, 167, 296, 307, 308, 363, 322, 148, 327, 350, 278, 264, 318, 243, 363, 364, 360, 312, 350, 345, 255, 214, 261, 354, 189, 340, 293, 86, 240, 311, 336, 365, 343, 360, 306, 334, 178, 350, 306, 192, 323]))
#{40, 98, 67, 96, 127, 243, 206, 268, 35, 190, 221, 258, 328, 307, 352, 374, 397, 285, 288, 523, 361, 373, 413, 424, 495, 496, 582, 436, 497, 567, 585, 618, 709, 622, 667, 676, 592, 729, 678, 701, 778, 893, 826, 835, 851, 939, 942, 963}




