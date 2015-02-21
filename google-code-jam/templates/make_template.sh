cp ../../templates/Template.java $1.java &&
sed 's/class Template/class '$1 $1.java > tmp.java &&
mv tmp.java $1.java

