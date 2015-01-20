'''
Created on 2010. 6. 15.

@author: Wonjohn Choi
'''

import Tkinter



class App:
    def __init__(self, root):
        frame = Tkinter.Frame(root)
        frame.pack()

        self.quit =Tkinter.Button(frame, text = 'QUIT', fg='red', command = frame.quit)
        self.quit.pack(side = 'right')

        self.hello = Tkinter.Button(frame, text = 'Say Hello', command = self.hello)
        self.hello.pack(side = 'left')
        
    def hello(self):
        print "Hello World!"

root = Tkinter.Tk()

app = App(root)

root.mainloop()
