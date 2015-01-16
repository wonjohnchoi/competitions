'''
Created on 2010. 6. 14.

@author: Wonjohn
'''
import string

print "Name: %s" %string.__name__
print "File name: %s" %string.__file__
print "Types: %s %s %s %s" %(string._float, string._int, string._long, string.atof_error)
print string.printable
print string.letters
print string.ascii_letters
print string.ascii_lowercase
print string.ascii_uppercase
print string.digits
print string.octdigits
print string.hexdigits
print "The float form of 12345: %s" %string.atof('12345')
print "100(2) = %s |100(8) = %s| 100(16) = %s|100(5) = %s" %(string.atoi('100', 2), string.atoi('100', 8), string.atoi('100', 16), string.atoi('100', 5))
print string.capitalize('how are you')
print string.capwords('how are you', ' ')
print string.count('Helllo World','ll')
print string.find('1234512345', '345')
print string.expandtabs('Hello\tHello',100)
print string.index('Hello', 'l')
print string.join('Hello', '!')
print string.ljust('123123', 50)
print string.rjust('123123', 50)
print string.lower("HOW ARE YOU")
print string.lstrip('aaaaaaaaaaaaaaaaaHi', 'a')
print string.rstrip('hi            ')

key = string.maketrans('Hl', '12')
str = "Hello World!"
print str.translate(key)


print string.rfind('hello','l')
print string.split('H!E!L!L!O', '!', 3)
print string.rsplit('H!E!L!L!O', '!', 3)
print string.swapcase('dsfjsdkfDFJDKFJD')
print string.zfill('abcde', 3)
print string.zfill('abcde', 10)
print len('ajdksfd')
print int('1321312')+3 

dic = {}
dic['0']=[]
dic['0'].append('Tomson')
dic['0'].append('Wonjohn')
dic['-']=['hi']

print dic['0']
print dic


str = "HELLO"
print "{0} and {1} is Hello".format(str, str)
