/*
 * Question_1.cpp
 *
 *  Created on: 2010. 6. 29.
 *      Author: Wonjohn Choi
 *
 * DWITE 2009-2010 Round 6
 */


#include <iostream>
#include <cstring>

using namespace std;

int main()
{
	freopen("DATA1.txt", "r", stdin);
	freopen("OUT1.txt", "w", stdout);

	unsigned int i, j;

	for(i=0;i<5;i++)
	{
		char s[251];
		cin.getline(s,250);
		for(j=0; j<strlen(s);j++)
		{
			if('A'<=s[j] && s[j]<='Z')
			{
				s[j]=(s[j]-'A'+13)%26 +'A';
			}
		}
		cout << s << '\n';
	}

	return 0;

}
