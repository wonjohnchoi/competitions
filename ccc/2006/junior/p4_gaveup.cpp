#include<iostream>
#include<vector>

using namespace std;

void make_constraints(vector< pair<int,int> > constraints){
	constraints.push_back(pair<int,int>(1,7));
	constraints.push_back(pair<int,int>(1,4));
	constraints.push_back(pair<int,int>(2,1));
	constraints.push_back(pair<int,int>(3,4));
	constraints.push_back(pair<int,int>(3,5));
}


int main(){
	cout<<"Hello Test";
	pair<int, int> x(1,7);
	cout<<x.first<<x.second<<endl;

	vector< pair<int, int> > constraints;
	make_constraints(constraints);

	cout<<constraints[0].first<<endl;


}
