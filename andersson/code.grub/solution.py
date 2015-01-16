'''
Wonjohn Choi
'''

from sys import argv
import simplejson
def splited(s):
    answer = []
    syms = [' OR ', '\\', "/"]
    for sym in syms:
        if sym in s:
            for s1 in s.split(sym):
                answer += splited(s1.strip())
            break
    if len(answer) == 0:
        answer.append(s)
    return answer
def dist(s1, s2):
    table = [[-1 for i in range(len(s2))] for j in range(len(s1))]
    '''
m[0,0] = 0
m[i,0] = i,  i=1..|s1|
m[0,j] = j,  j=1..|s2|

m[i,j] = min(m[i-1,j-1]
             + if s1[i]=s2[j] then 0 else 1 fi,
             m[i-1, j] + 1,
             m[i, j-1] + 1 ),  i=1..|s1|, j=1..|s2|'''

    for i in range(len(s1)):
        table[i][0] = i
    for j in range(len(s2)):
        table[0][j] = j
    table[0][0] = 0
    for i in range(1, len(s1)):
        for j in range(1, len(s2)):
            table[i][j] = min(table[i-1][j-1] + (0 if s1[i]==s2[j] else 1),
                table[i-1][j] + 1, table[i][j - 1] + 1)
    #for i in range(len(s1)):
    #    for j in range(len(s2)):
    #       print table[i][j],
    #    print ''
    return table[len(s1)-1][len(s2)-1]
DEBUG = True
#96
#110
#760
#987
#dist('customcustomcustom', 'cootomcootomcootom')
def predict_reviews(training_file, training_file2, amenity_file, test_file, output_file):
    id2amenities = {}
    training_data = simplejson.loads(training_file.read())

    for id in training_data:
        id2amenities[id] = training_data[id]['amenities']
    training_data2 = simplejson.loads(training_file2.read())

    for id in training_data2:
        id2amenities[id] = training_data2[id]['amenities']

    amenities_raw = simplejson.loads(amenity_file.read())
    amenities_uppered = []
    amenities_words = []
    for amenity in amenities_raw:
        uppered = amenity.upper()
        #print amenity
        amenities_words.append(splited(uppered))
        amenities_uppered.append(uppered)
    common_words = set(['FOR', 'AND', 'THE'])
    punctuations = set(['!', '?', '.', ',', ';', ':', '#', '$', '%','^', '&', '*', '@', '%', '+', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '-', '_', '/', '\"', '\'', '<', '>', '~'])
    #print 'WORDS',amenities_words
    #amenities = set()
    #for amenity in amenities_raw:
    #    amenities.add(amenity.upper())
    loaded = simplejson.loads(test_file.read())
    if DEBUG:
        pos = neg = 0
    output = {}
    for id in loaded:
        item = loaded[id]

        if id in id2amenities:
            amenities_answers = id2amenities[id]
        else:
            amenities_points = [0 for i in range(len(amenities_raw))]

            description_raw = item['description'].upper()
            description = []
            for char in description_raw:
                if char in punctuations:
                    description.append(' ')
                else:
                    description.append(char)
            description = ''.join(description)
        
            #print description
            #print amenities
            description_words = description.split(' ')
            amenities_answers = []
            for i in range(len(amenities_raw)):
                if amenities_uppered[i] in description:
                    amenities_points[i] += 5.0
                    
            for i in range(len(amenities_words)):
                amenities_words2 = amenities_words[i]
                for amenity in amenities_words2:
                
                    for word in description_words:
                        if len(word) <= 2:
                            continue
                        if word in common_words:
                            continue
                        if word == amenity:
                            amenities_points[i] += 5.0
                            #print 'genius', word, amenity

                        elif dist(word, amenity) <= 1 and word[0] == amenity[0]:
                            #print word, amenity
                            #print dist(word, amenity)
                            amenities_points[i] += 1.0
                            #print word, amenity
                        
                #amenities_points[i] /= len(amenities_words)
                if amenities_points[i] > 0:
                    amenities_answers.append(amenities_raw[i])
            if DEBUG:
                print "MINE:", amenities_answers
                print 'ANSWER', item['amenities']
                print description
                input('next')
        if DEBUG:
            for answer in amenities_answers:
                if answer in item['amenities']:
                    pos += 1
                else:
                    neg -= 1
        #dump = input('next')
        output[id] = amenities_answers
    output_file.write((simplejson.dumps(output)))

    if DEBUG:
        print pos, neg
def train_and_predict(training_filename,training_filename2, amenities_filename,  test_filename, output_filename):
    with open(training_filename) as training_file:
        with open(training_filename2) as training_file2:
            with open(test_filename) as test_file:
                with open(output_filename, 'w') as output_file:
                    with open(amenities_filename) as amenity_file:
                        predict_reviews(training_file, training_file2, amenity_file, test_file, output_file)

if __name__ == '__main__':
    if len(argv) != 6:
        print "usage: %s training-data1.json training-data2.json amenityes.json test-data.json output.json" % argv[0]
        exit(0)
    training_filename = argv[1]
    training_filename2 = argv[2]
    amenities_filename = argv[3]
    test_filename = argv[4]
    output_filename = argv[5]

    train_and_predict(training_filename, training_filename2, amenities_filename,  test_filename, output_filename)
