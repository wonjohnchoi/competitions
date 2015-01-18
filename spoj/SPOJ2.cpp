/*
 * SPOJ_2.cpp
 *
 *  Created on: 2010. 7. 1.
 *      Author: user
 */
#include <iostream>
#include <string>
using namespace std;

int main() {
	int t,max=-1;
	cin>>t;

	int input[2][t];

	for(int i=0;i<t;i++)
	{
		int s,e;
		cin>>s>>e;
		if(e>max)
		{
			max=e;
		}
	}
	cout<<1;
	int prime[10000000];
	cout<<2;
	return 0;
}
