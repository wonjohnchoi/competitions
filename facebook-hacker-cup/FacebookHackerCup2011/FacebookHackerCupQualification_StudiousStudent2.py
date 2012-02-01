file = open('input1.txt')
skipInput = next(file)

def shortestWhenJoined(words):
    if len(words)==1:
        return words[0]
    else:
        answer='z'*(len(words)*10)
        for i in range(len(words)):
            word = words.pop(i)
            answer = min([answer,word+shortestWhenJoined(words)])
            words.insert(i, word)
        return answer
out = open('output.txt','w')
for line in file:
    words = line.split(' ')
    words.pop(0)
    words = list(map(lambda s:s.strip(), words))
    out.write(shortestWhenJoined(words))
    out.write('\n')
   
file.close()
out.close()
'''
import random
for i in range(100):
    words=[chr(int(random.random()*24)+ord('a')) *10 for j in range(9)]
    print(shortestWhenJoined(words), words)
    '''
   
