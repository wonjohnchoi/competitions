import math
T = int(raw_input())
for tc in range(1, T + 1):
    r, t = map(float, raw_input().split())
    numEst = int((-2 * r + 1 + math.sqrt((2 * r - 1) * (2 * r - 1) + 8 * t)) / 4.0)
    num = numEst + 1000
    while num > 0:
        if (2 * num * num + (2 * r - 1) * num) <= t:
            print 'Case #%d: %d' % (tc, num)
            break
        num -= 1
