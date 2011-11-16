def inOrder(word1, word2, words, idx):
    if len(word1)==len(word2): return word1<=word2
    elif word2.startswith(word1):
        for k in range(idx, len(words)):
            if inOrder(word2, word1+words[k],words, k+1):
                return False
        return True
    elif word1.startswith(word2):
        for k in range(idx, len(words)):
            if inOrder(word1,word2+words[k],words, k+1):
                return True
        return False
    else:
       return word1<=word2

def shortestWhenJoined(words):
    nWords = len(words)
    if nWords == 0: return ''
    elif nWords == 1: return words[0]
    else:
        if all(map(lambda word: len(words[0])==len(word), words)):
            return ''.join(words)
        if all(map(lambda word: words[0][0]==word[0], words)):
            done = False

            while not done:
                done = True

                for i in range(len(words)-1):
                    print(words, words[i], words[i+1])
                    if not inOrder(words[i], words[i+1], words, i+2):
                        tmp =words[i]
                        words[i] =words[i+1]
                        words[i+1] = tmp
                        done = False
            return ''.join(words)
                        
        else:
            groups = []
            groupedWords=[]
            marker = 0
            while True:                
                if len(groupedWords)==0:
                    groupedWords.append(words[marker])
                elif groupedWords[0][0]==words[marker][0]:
                    groupedWords.append(words[marker])
                else:
                    groups.append(groupedWords)
                    groupedWords = [words[marker]]
                
                marker+=1
            
                if marker==len(words):
                    groups.append(groupedWords)
                    break

            return ''.join([shortestWhenJoined(group) for group in groups])

if False:
    file = open('input.txt')
    skipInput = next(file)
    answers='''cupfacebookforhackerstudentsstudious
duzklvrawqrc
dyroiymybeaxeyubxzdr
bwjibwjibwjijp
dcyihopjijliuiuy'''.strip().split('\n')
    i=0
    for line in file:
        words = line.split(' ')
        words.pop(0)
        words = list(map(lambda s:s.strip(), words))
        words = sorted(words)
        x=shortestWhenJoined(words)
        print(x, answers[i], words, sep='|')
        if x==answers[i]:
            print('Right!!')
        else:
            print('WRONG!!')
        i+=1
        print()
        

        
    file.close()

#print(shortestWhenJoined(['abcd','abc','ab','a','aa', 'aaa','aaad','abb','abd','adc']))
        
    
