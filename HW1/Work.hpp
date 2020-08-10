class Work  : public Person {
public:
    static Work getWork();

    Work(string &fName, string &lName, int &pNum, string &teamIn)
    {
        setFirstName(fName);
        setLastName(lName);
        setPhoneNumber(pNum);
        setTeam(teamIn);
    }

    void setTeam(string teamIn)
    {
        team = teamIn;
    }

    string getTeam()
    {
        return team;
    }

    void print()
    {
        cout << getFirstName() << " " << getLastName() << "_" << constructPhone() << "_" << getTeam() << endl;
    }

private:

    string team;

};