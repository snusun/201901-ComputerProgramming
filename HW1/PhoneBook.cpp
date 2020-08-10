#include <iostream>
#include <vector>
#include <stdlib.h>
#include <regex>
#include <ctype.h>
#include "Person.hpp"
#include "Work.hpp"
#include "Family.hpp"
#include "Friend.hpp"

using namespace std;

void initial();
string selecting();
int putPhoneNum();
string getFullName();
string makeFirst(string fullname);
string makeLast(string fullname);
string getValidBirth();
int getValidAge();

int main(void)
{
    int count = 0;
    initial();
    vector<Person> PhoneBook;
    vector<Person> ::iterator iter = PhoneBook.begin();

    while(true)
    {
        string input = selecting();

        if(input.compare("1") == 0)
        {
            while (true)
            {
                printf("Select Type\n1.Person\n2.Work\n3.Family\n4.Friend\n");
                string select = selecting();
                if(select.compare("1") == 0) PhoneBook.push_back(Person::getPerson());
                else if(select.compare("2") == 0) PhoneBook.push_back(Work::getWork());
                else if(select.compare("3") == 0) PhoneBook.push_back(Family::getFamily());
                else if(select.compare("4") == 0) PhoneBook.push_back(Friend::getFriend());
                break;
            }
            count++;
        }
        else if(input.compare("2") == 0)
        {
            printf("Enter index of person: ");
            int index;
            cin >> index;
            if(index < count && index >= 0)
            {
                int i = 0;
                for(iter = PhoneBook.begin() ; i < index-1 ; i++);
                PhoneBook.erase(iter);
                printf("A person is successfully deleted from the Phone Book!\n");
            }
            else
            {
                printf("Person does not exist!\n");
            }
            count--;
        }
        else if(input.compare("3") == 0)
        {
            int tmp = 1;

            for(iter = PhoneBook.begin() ; iter!= PhoneBook.end() ; ++iter)
            {   cout << tmp++ << ". ";
                iter -> print();
            }
        }
        else if(input.compare("exit") == 0)
        {
            break;
        }
    }
}

void initial()
{
    printf("CP-2017-18538>(input enter)");
    string init;
    getline(cin,init);
    while(true)
    {
        if (init.compare("") == 0)
        {
            printf("Phone Book\n1.Add person\n2.Remove person\n3.Print phone book\n");
            break;
        }
        else if (init.compare("exit") == 0) exit(EXIT_FAILURE);;
    }

}

int putPhoneNum()
{
    string input;
    string delimiter = "-";
    string tmp;

    regex matchregex("^(02|010)(-\\d{4}){2}");

    do
    {
        printf("Phone_number: ");
        cin >> input;
    }while(!regex_match(input, matchregex));

    int pnums[3];
    int i = 0;
    int begin = 0;
    while((begin = input.find(delimiter)) != string::npos)
    {
        tmp = input.substr(0,begin);
        pnums[i] = atoi(tmp.c_str());
        input.erase(0, begin + delimiter.length());
        i++;
    }
    pnums[2] = atoi(input.c_str());

    int phoneNumber = pnums[0]*100000000 + pnums[1]*10000 + pnums[2];
    return phoneNumber;

}

string selecting()
{
    printf("CP-2017-18538>");
    string sel;
    cin >> sel;
    return sel;
}

string getFullName()
{
    string result;
    bool vname; //false when result has char which is not alphabet
    bool vform; //false when result has no ' ' or two or more of '


    do
    {
        cout << "Name: ";
        getline(cin, result);
        vname = true; //false when result has char which is not alphabet
        vform = false; //false when result has no ' ' or two or more of ' '

        for(int i = 0 ; i < result.length() ; i++)
        {
            if(result.at(i) >= '0' && result.at(i) <= '9')
            {
                vname = false;
            }
        }

        if(result.find(" ") != string::npos)
        {
            vform = true;
        }

        if(!vform || !vname)
        {
            continue;
        }

        else if(vname && vform) break;
    }while(!vname || !vform);

    return result;
}

string makeFirst(string fullname)
{
    return fullname.substr(0,fullname.find(" "));
}

string makeLast(string fullname)
{
    return fullname.substr(fullname.find(" ")+1);
}

string getValidBirth()
{
    string result;
    printf("Birthday: ");
    cin >> result;

    bool flag; // false when result contains what is not nature number

    while(true)
    {
        flag = true;
        for(int i = 0 ; i < result.length() ; i++)
        {
            if(!(result.at(i) >= '0') || !(result.at(i) <= '9')) flag = false;
        }

        if(result.size() != 6) flag = false;

        if(!flag)
        {
            printf("Birthday: ");
            cin >> result;
            continue;
        }
        else break;
    }

    return result;
}

int getValidAge()
{
    string result;
    printf("Age: ");
    cin >> result;

    bool flag; //false when result is not a nature number

    while(true)
    {
        flag = true;

        for(int i = 0 ; i < result.length() ; i++)
        {
            if(!(result.at(i) >= '0' && result.at(i) <= '9')) flag = false;
        }

        if(!flag)
        {
            printf("Age: ");
            cin >> result;
            continue;
        }
        else break;
    }

    return atoi(result.c_str());
}

Person Person :: getPerson()
{
    //printf("Name: ");
    string name = getFullName();
    string first = makeFirst(name);
    string second = makeLast(name);
    //printf("Phone_number: ");
    int pn = putPhoneNum();

    string& fst = first;
    string& lst = second;
    int& pnb = pn;

    printf("Successfully added new person.\n");
    Person result(fst, lst, pnb);
    return result;
}

Work Work :: getWork()
{
    //printf("Name: ");
    string name = getFullName();
    string first = makeFirst(name);
    string second = makeLast(name);
    //printf("Phone_number: ");
    int pn = putPhoneNum();
    printf("Team: ");
    string tm;
    cin >> tm;

    string& fst = first;
    string& lst = second;
    int& pnb = pn;
    string& tem = tm;

    printf("Successfully added new person.\n");
    Work result(fst, lst, pnb, tem);
    return result;
}

Family Family :: getFamily()
{
    //printf("Name: ");
    string name = getFullName();
    string first = makeFirst(name);
    string second = makeLast(name);
    //printf("Phone_number: ");
    int pn = putPhoneNum();
    string bD = getValidBirth();

    string& fst = first;
    string& lst = second;
    int& pnb = pn;
    string& bir = bD;

    printf("Successfully added new person.\n");
    Family result(fst, lst, pnb, bir);
    return result;
}

Friend Friend :: getFriend()
{
    //printf("Name: ");
    string name = getFullName();
    string first = makeFirst(name);
    string second = makeLast(name);
    //printf("Phone_number: ");
    int pn = putPhoneNum();
    int age = getValidAge();

    string& fst = first;
    string& lst = second;
    int& pnb = pn;
    int& ag = age;
    printf("Successfully added new person.\n");
    Friend result(fst, lst, pn , ag);
    return result;
}