javac $1.java && java $1 < $1$2.in > $1$2.out && sudo cp $1$2.out /var/www/ &&
sudo cp $1.java /var/www/ && less $1$2.out
