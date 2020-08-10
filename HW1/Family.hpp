#include <time.h>
class Family : public Person{
public:
    static Family getFamily();

    Family(string &fName, string &lName, int &pNum, string &bDay)
    {
        setFirstName(fName);
        setLastName(lName);
        setPhoneNumber(pNum);
        setBirthday(bDay);
    }

    void setBirthday(string bDay)
    {
        birthday = bDay;
    }

    string getBirthday()
    {
        return birthday;
    }

    int dDay() //확인
    {
        time_t curr;
        struct tm *d;
        curr = time(NULL);
        d = localtime(&curr);
        
        int month[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int birthmonth = atoi(birthday.substr(2,2).c_str());
        int birthdays = atoi(birthday.substr(4,2).c_str());
        int cmonth = d->tm_mon+1;
        int cdays = d->tm_mday;

        int birthsum = birthdays;
        int csum = cdays;

        for(int i = 0 ; i < birthmonth ; i++)
        {
            birthsum += month[i];
        }
        for(int i = 0 ; i < cmonth ; i++)
        {
            csum += month[i];
        }

        int dif = birthsum - csum;

        if(dif < 0)
        {
            return dif+365;
        }
        else
        {
            return dif;
        }
    }

    void print()
    {
        cout << getFirstName() << " " << getLastName() << "_" << constructPhone() << "_" << getBirthday() << "_" << dDay() << endl;
    }

private:

    string birthday;

};