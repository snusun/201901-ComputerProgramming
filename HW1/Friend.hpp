class Friend : public Person{
public:
    static Friend getFriend();

    Friend(string &fName, string &lName, int &pNum, int &ageIn)
    {
        setFirstName(fName);
        setLastName(lName);
        setPhoneNumber(pNum);
        setAge(ageIn);
    }

    void setAge(int ageIn)
    {
        age = ageIn;
    }

    int getAge()
    {
        return age;
    }

    void print()
    {
        cout << getFirstName() << " " << getLastName() << "_" << constructPhone() << "_" << getAge() << endl;
    }

private:

    int age;

};