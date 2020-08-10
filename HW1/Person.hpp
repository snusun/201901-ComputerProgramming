#include <string>
using namespace std;

class Person {

public:
    Person(){};

    Person(string &fName, string &lName, int &pNum)
    {
        setFirstName(fName);
        setLastName(lName);
        setPhoneNumber(pNum);
    }

    static Person getPerson();

    void setFirstName(string &fName)
    {
        firstName = fName;
    }

    string getFirstName()
    {
        return firstName;
    }

    void setLastName(string &lName)
    {
        lastName = lName;
    }

    string getLastName()
    {
        return lastName;
    }

    void setPhoneNumber(int &pNum)
    {
        phoneNumber = pNum;
    }

    int getPhoneNumber()
    {
        return phoneNumber;
    }

    string constructPhone()
    {
        string pNum = "";
        int tmp = phoneNumber;
        int phoneNum[3];
        phoneNum[2] = tmp%10000;
        tmp/=10000;
        phoneNum[1] = tmp%10000;
        tmp/=10000;
        phoneNum[0] = tmp;

        pNum += "0" + to_string(phoneNum[0]);

        for(int i = 1 ; i<3 ; i++)
        {
            if (phoneNum[i] >= 1000)
            {
                pNum += "-";
            }
            else if (phoneNum[i]<1000 && phoneNum[i]>=100)
            {
                pNum += "-0";
            }
            else if (phoneNum[i]<100 && phoneNum[i]>=10)
            {
                pNum += "-00";
            }
            else
            {
                pNum += "-000";
            }
            pNum += to_string(phoneNum[i]);
        }

        return pNum;
    }
    void print()
    {
        cout << firstName << " " << lastName << "_" << constructPhone() << endl;
    }

private:

    string firstName;
    string lastName;
    int phoneNumber;
};