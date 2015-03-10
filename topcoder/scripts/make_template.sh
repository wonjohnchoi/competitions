cp ../../scripts/Template.java $1.java &&
sed 's/class Template/class '$1'/' $1.java > tmp.java &&
sed 's/TYPE/'$2'/' tmp.java > tmp2.java &&
sed 's/NAME/'$3'/' tmp2.java > tmp3.java &&
rm -rf tmp.java &&
rm -rf tmp2.java && 
mv tmp3.java $1.java &&
sudo chown wonjohnchoi: $1.java &&
sudo chmod 664 $1.java &&

touch $1.in &&
sudo chown wonjohnchoi: $1.in &&
sudo chmod 664 $1.in &&

emacs -q $1.java $1.in &&
emacs $1.java $1.in
