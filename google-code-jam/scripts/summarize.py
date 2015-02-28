#!/usr/bin/python
import fnmatch
import os
matches = []
for root, dirnames, filenames in os.walk(os.getcwd()):
    for filename in fnmatch.filter(filenames, 'SUMMARY'):
        matches.append(os.path.join(root, filename))
matches.sort()
categories = {}
problem_names = set()
for c in ('A', 'B', 'C', 'D', 'E', 'F', 'G'):
    for d in ('', '1', '2', '3', '4'):
        for ext in ('.java', '.py', '', '.cpp'):
            problem_names.add(c + d + ext)
for match in matches:
    f = open(match, "r")
    category = ''
    tokens = match.split('/')
    for line in f:
        line = line.strip()
        if line in problem_names:
            val =  tokens[-3] + '/' + tokens[-2] + '/' + line
            print val
            if category not in categories.keys():
                categories[category] = []
            categories[category].append(val)
        else:
            print line
            category = line

#print categories