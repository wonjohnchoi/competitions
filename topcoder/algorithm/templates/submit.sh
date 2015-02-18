javac $1.java && java $1 > $1.out && sudo cp $1.java /var/www/ && less $1.out
