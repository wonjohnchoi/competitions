from __future__ import print_function

from math import ceil
from math import floor
from sys import argv
from sys import stderr

import simplejson

def generate_model(training_data):
    """Given an iterable of lines of JSON containing user and business data,
    build and return a predictive model for future reviews.

    Args:
        - training_data: An iterable of JSON data
    """

    user_averages = {}
    business_averages = {}
    review_data = {}

    spu =  [[0, 0] for i in range(100)] #stars per usefulness
    spf =  [[0, 0] for i in range(100)] #stars per usefulness
    spc =  [[0, 0] for i in range(100)] #stars per usefulness
    for line in training_data:
        parsed_data = simplejson.loads(line)
            
        review_id = parsed_data.get('review_id', None)
        user_id = parsed_data.get('user_id', None)
        business_id = parsed_data.get('business_id', None)
        pd = parsed_data;
        if review_id is not None:
            stars = pd['stars']
            votes = pd['votes']
            useful = votes['useful']
            funny = votes['funny']
            cool = votes['cool']
            if useful < 100 and funny < 100 and cool < 100:
            #print(useful, funny, cool, stars)
                spu[useful][0] += stars
                spu[useful][1] += 1
                spf[funny][0] += stars
                spf[funny][1] += 1
                spc[cool][0] += stars
                spc[cool][1] += 1
                
            else:
                print(useful, funny, cool, stars)
            review_data[review_id] = [useful, funny, cool]
        elif user_id is not None:
            user_averages[user_id] = parsed_data['average_stars']
        elif business_id is not None:
            business_averages[business_id] = parsed_data['stars']
        else:
            print("Record has neither a business_id nor a user_id: %s" % line, file=stderr)

            #print(spu)
    sp2 = []
    for i in range(100):#map(spu, spf, spc):
        t = [spu[i], spf[i], spc[i]]
        sp2.append([(t[0][0]/(t[0][1]+0.000000001), t[1][0]/(t[1][1]+0.000000001), t[2][0]/(t[2][1]+0.000000001))])
        #print(str(sp2[-1]) + ",")
        #print(sp2)
    return {'business_averages' : business_averages, 'user_averages' : user_averages,'review_data' : review_data}

def predict_reviews(review_data, data, output_file):
    mldata = [[(3.668940809232108, 3.6813456602542325, 3.5744556138448114)],
[(3.6319659119390755, 3.5476046394350202, 3.743642682632953)],
[(3.602675159235439, 3.4608946212948046, 3.790612013599944)],
[(3.56688256829012, 3.4352941176460225, 3.8339382940100193)],
[(3.5184338382384914, 3.424224692800805, 3.802661771453051)],
[(3.4818133895606316, 3.429254302099972, 3.8440860215019317)],
[(3.466546112112598, 3.3925811437350966, 3.8561452513912626)],
[(3.5223463687101644, 3.516059957165919, 3.79449152541569)],
[(3.599147121527507, 3.517857142846673, 3.922155688611012)],
[(3.4870967741823, 3.2909090908941323, 4.090909090891381)],
[(3.681034482742754, 3.653061224471158, 3.9479768785898965)],
[(3.7218934911022377, 3.511811023594395, 3.939130434748355)],
[(3.6014492753362215, 3.4158415841245957, 4.016949152508331)],
[(3.604166666629123, 3.5638297871961293, 3.941860465070443)],
[(3.8769230768634317, 3.5679012345238528, 3.9999999999384612)],
[(3.491803278631282, 3.5245901638766464, 3.792452830117124)],
[(3.4999999999270837, 3.586956521661153, 3.891891891786706)],
[(3.651162790612764, 3.6078431371841604, 3.9285714284311224)],
[(3.49999999978125, 3.8124999998808597, 3.968749999875977)],
[(4.032258064386056, 3.7599999998496, 3.8749999998385416)],
[(3.807692307545858, 3.846153846005917, 4.4705882350311414)],
[(3.4615384612721893, 4.117647058581315, 3.642857142596939)],
[(3.894736841900277, 3.187499999800781, 3.69999999963)],
[(3.909090908735537, 3.5555555551604936, 4.181818181438016)],
[(4.19999999958, 3.333333332777778, 4.2222222217530865)],
[(4.230769230443787, 4.09999999959, 3.9999999993333333)],
[(3.666666665444444, 3.8333333326944445, 4.4999999994375)],
[(3.8571428565918366, 3.8571428565918366, 3.999999999)],
[(4.374999999453125, 3.19999999936, 3.9999999992)],
[(3.9999999986666666, 3.7499999990625, 3.59999999928)],
[(2.7142857138979593, 3.9999999995555555, 2.9999999969999998)],
[(3.666666665444444, 3.666666665444444, 4.49999999925)],
[(4.3333333318888885, 3.499999999125, 4.39999999912)],
[(2.7499999993125, 4.333333332611111, 3.3333333322222223)]]
    review_data2 = data['review_data']
    business_averages = data['business_averages']
    user_averages = data['user_averages']
    for raw_review in review_data:
        review = simplejson.loads(raw_review)

        review_id = review.get('review_id', None)
        if review_id is None:
            print("Review record has no ID! %s" % raw_review, file=stderr)
            continue

        business_id = review.get('business_id', None)
        user_id = review.get('user_id', None)

        if business_id is None or user_id is None:
            print("Review record is incomplete: %s" % raw_review, file=stderr)
            continue

        
        
        user_average = user_averages.get(user_id, None)
        business_average = business_averages.get(business_id, None)
        rd = review_data2.get(review_id, None)

        guess = 3.6 # Set an initial guess in case we have no data.
        #i`f user_average is not None and business_average is not None:
        #    guess = (user_average + business_average) / 2.0
        #elif user_average is not None:
        #    guess = user_average
        #else:
        if user_average is not None and business_average is not None:
            guess = (business_average * user_average) ** 0.5
            #guess = (business_average + user_average) / 2.0
            #guess = business_average
        elif user_average is not None:
            guess = user_average
        else:
            guess = business_average
        if rd is not None and rd[0] < len(mldata) and rd[1] < len(mldata) and rd[2] < len(mldata):
            print(rd)
            guess = (mldata[rd[0]][0] + mldata[rd[1]][1] + mldata[rd[2]][2])/3
            
        # Do slightly more accurate rounding rather than just truncating.
        #if guess > floor(guess) + 0.5:
        #    guess = ceil(guess)
        #else:
        #    guess = floor(guess)
            
        output = {
            'review_id': review_id,
            'stars': guess,
        }

        output_file.write((simplejson.dumps(output)) + '\n')

def train_and_predict(training_filename, test_filename, output_filename):
    with open(training_filename) as training_file:
        data = generate_model(training_file)

    with open(test_filename) as test_file:
        with open(output_filename, 'w') as output_file:
            predict_reviews(test_file, data, output_file)

if __name__ == '__main__':
    if len(argv) != 4:
        print("usage: %s training-data.json test-data.json" % argv[0])
    training_filename = argv[1]
    test_filename = argv[2]
    output_filename = argv[3]

    train_and_predict(training_filename, test_filename, output_filename)
