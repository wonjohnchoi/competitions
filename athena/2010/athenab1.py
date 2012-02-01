inText = 'znkupuq zgql ku wgi bqi nvq ufsqixbtnf cszuwu tc cbtrr pvrfuqghru bn gbbgsac dqnw gbzufg xgqbtstxgfbc'
nForm = list(map(lambda c:ord(c), inText))
print(nForm)

def increment(n,d):
    if (n+d)>ord('z'):
        return chr(n+d-ord('z')+ord('a')-1)
    else:
        return chr(n+d)

for d in range(0,26):
    s = ''.join(list(map(lambda n: increment(n,d), nForm)))
    print(s)
    if 'athena' in s:
        print("FOUND!")
        break
    print('\n\n\n')
    


