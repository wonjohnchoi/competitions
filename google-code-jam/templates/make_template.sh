cp ../../templates/Template.java $1.java &&
sed 's/class Template/class '$1'/' $1.java > tmp.java &&
mv tmp.java $1.java &&
sudo chown wonjohnchoi: $1.java &&
sudo chmod 664 $1.java &&

touch $1.in &&
sudo chown wonjohnchoi: $1.in &&
sudo chmod 664 $1.in &&

emacs $1.java $1.in
