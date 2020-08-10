#include <iostream>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;


class Vehicle{
    public:

    Vehicle(){};

    void setSpeed(int Spd)
    {
        Speed = Spd;
    }

    void setDistance(int Dis)
    {
        Distance = Dis;
    }
    
    int getDistance()
    {
        return Distance;
    }

    int getSpeed()
    {
        return Speed;   
    }

    void setEnergy(int Enr)
    {
        Energy = Enr;
    }

    int getEnergy()
    {
        return Energy;
    }

    void setTraveled(int Tra)
    {
        Traveled = Tra;
    }

    int getTraveled()
    {
        return Traveled;
    }

    void setOxygenRate(int Oxy)
    {
        OxygenRate = Oxy;
    }

    int getOxygenRate()
    {
        return OxygenRate;
    }

    private:
        int OxygenRate;
        int Traveled;
        int Distance;
        int Speed;
        int Energy;
};

class Car : public Vehicle{
public:
    Car(){};
    static Car getCar(string cases);
    static Car getCar(string cases, Vehicle prev);

    Car(int Dis, int Tra, int Spd, int Enr, int Tem, int Hum, int Oxy, bool Solar)
    {
        setDistance(Dis);
        setTraveled(Tra);
        setSpeed(Spd);
        setEnergy(Enr);
        setTemperature(Tem);
        setHumidity(Hum);
        setOxygenRate(Oxy);
        setSolarPanelRecharge(Solar);
    }

    void setTemperature(int Tem)
    {
        Temperature = Tem;
    }

    int getTemperature()
    {
        return Temperature;
    }

    void setHumidity(int Hum)
    {
        Humidity = Hum;
    }

    int getHumidity()
    {
        return Humidity;
    }

    void setSolarPanelRecharge(bool Solar)
    {
        SolarPanelRecharge = Solar;
    }

    bool getSolarPanelRecharge()
    {
        return SolarPanelRecharge;
    }

    void firstMoveMode()
    {
        if(getTemperature()>0 && getTemperature() < 40) setEnergy(getEnergy()-5);
        else if(getTemperature() >= 40) setEnergy(getEnergy()-10);
        else if(getTemperature() == 0) setEnergy(getEnergy()-8);

        if(getHumidity() < 50) setEnergy(getEnergy()-5);
        else if(getHumidity() >= 50) setEnergy(getEnergy()-8);

        if(getHumidity() < 50) setSolarPanelRecharge(true);
        else setSolarPanelRecharge(false);

        if(getSolarPanelRecharge())
        {
            if(getEnergy() > 800) setEnergy(1000);
            else setEnergy(getEnergy()+200);
        }

        setOxygenRate(100);

        if(getEnergy() < 0) setEnergy(0);

        setDistance(getDistance()-50);
        setTraveled(getTraveled()+50);

        printf("Successfully moved to next 50 km\n");
        printf("Current Status: Car\n");
        cout << "Distance: " << getTraveled() << " km" << endl;
        printf("Speed: 80 km/hr\n");
        cout << "Energy: " << getEnergy() << endl;
        cout << "Temperature: " << getTemperature() << " C" << endl;
        cout << "Humidity: " << getHumidity() << endl;
    }

    bool secondMoveMode()
    {    
        int targetDistance = getDistance();
        while(true)
        {
            if(getTemperature()>0 && getTemperature() < 40) setEnergy(getEnergy()-5);
            else if(getTemperature() >= 40) setEnergy(getEnergy()-10);
            else if(getTemperature() == 0) setEnergy(getEnergy()-8);

            if(getHumidity() < 50) setEnergy(getEnergy()-5);
            else if(getHumidity() >= 50) setEnergy(getEnergy()-8);

            if(getHumidity() < 50) setSolarPanelRecharge(true);
            else setSolarPanelRecharge(false);

            if(getSolarPanelRecharge())
            {
                if(getEnergy() > 800) setEnergy(1000);
                else setEnergy(getEnergy()+200);
            }

            setOxygenRate(100);

            if(getEnergy() < 0) setEnergy(0);

            setDistance(getDistance()-50);
            setTraveled(getTraveled()+50);

            if(getDistance() == 0)
            {
                printf("Successfully moved to next %d km\n",targetDistance);
                printf("Current Status: Car\n");
                cout << "Distance: " << getTraveled() << " km" << endl;
                printf("Speed: 80 km/hr\n");
                cout << "Energy: " << getEnergy() << endl;
                cout << "Temperature: " << getTemperature() << " C" << endl;
                cout << "Humidity: " << getHumidity() << endl;
                return true;
            }
            else if(getEnergy() == 0)
            {
                printf("Successfully moved to next %d km\n",targetDistance);
                printf("Current Status: Car\n");
                cout << "Distance: " << getTraveled() << " km" << endl;
                printf("Speed: 80 km/hr\n");
                cout << "Energy: " << getEnergy() << endl;
                cout << "Temperature: " << getTemperature() << " C" << endl;
                cout << "Humidity: " << getHumidity() << endl << endl;
                cout << "!FINISHED : Energy failure" << endl;
                return false;
            }
        }
    }

private:
    int Temperature;
    int Humidity;
    bool SolarPanelRecharge;
};

class Airplane : public Vehicle{
public:
    Airplane(){};
    static Airplane getAirplane(string cases, Vehicle prev);

    Airplane(int Dis, int Tra, int Spd, int Enr, int Tem, int Hum, int Alt, int Oxy, int Air)
    {
        setDistance(Dis);
        setTraveled(Tra);
        setSpeed(Spd);
        setEnergy(Enr);
        setTemperature(Tem);
        setHumidity(Hum);
        setAltitude(Alt);
        setOxygenRate(Oxy);
        setAirDensity(Air);
    }

    void setTemperature(int Tem)
    {
        Temperature = Tem;
    }

    int getTemperature()
    {
        return Temperature;
    }

    void setHumidity(int Hum)
    {
        Humidity = Hum;
    }

    int getHumidity()
    {
        return Humidity;
    }

    void setAltitude(int Alt)
    {
        Altitude = Alt;
    }

    int getAltitude()
    {
        return Altitude;
    }

    void setAirDensity(int Air)
    {
        AirDensity = Air;
    }

    int getAirDensity()
    {
        return AirDensity;
    }

    void firstMoveMode()
    {
        if(getTemperature()>0 && getTemperature() < 40) setEnergy(getEnergy()-5);
        else if(getTemperature() >= 40) setEnergy(getEnergy()-10);
        else if(getTemperature() == 0) setEnergy(getEnergy()-8);

        if(getHumidity() < 50) setEnergy(getEnergy()-5);
        else if(getHumidity() >= 50) setEnergy(getEnergy()-8);

        if(getAirDensity() >= 30) 
        {   
            if(getSpeed() == 900) setSpeed(getSpeed()-200);
        }
        else if(getAirDensity() >= 50) 
        {   
            if(getSpeed() == 900) setSpeed(getSpeed()-300);
        }
        else if(getAirDensity() >= 70) 
        {
            if(getSpeed() == 900) setSpeed(getSpeed()-500);
        }

        setOxygenRate(getOxygenRate()-10*(getAltitude()/1000));

        if(getEnergy() < 0) setEnergy(0);
        if(getOxygenRate() < 0) setOxygenRate(0);

        setDistance(getDistance()-1000);
        setTraveled(getTraveled()+1000);

        printf("Successfully moved to next 1000 km\n");
        printf("Current Status: Airplane\n");
        cout << "Distance: " << getTraveled() << " km" << endl;
        cout << "Speed: " << getSpeed() << " km/hr" << endl;
        cout << "Energy: " << getEnergy() << endl;
        cout << "Oxygen Level: " << getOxygenRate() << endl;
        cout << "Temperature: " << getTemperature() << " C" << endl;
        cout << "Humidity: " << getHumidity() << endl;
        cout << "Altitude: " << getAltitude() << " m" << endl;
        cout << "Air Density: " << getAirDensity() << endl;
    }

    bool secondMoveMode()
    {   
        int targetDistance = getDistance();
        while(true)
        {
            if(getTemperature()>0 && getTemperature() < 40) setEnergy(getEnergy()-5);
            else if(getTemperature() >= 40) setEnergy(getEnergy()-10);
            else if(getTemperature() == 0) setEnergy(getEnergy()-8);

            if(getHumidity() < 50) setEnergy(getEnergy()-5);
            else if(getHumidity() >= 50) setEnergy(getEnergy()-8);

            if(getAirDensity() >= 30) 
            {   
                if(getSpeed() == 900) setSpeed(getSpeed()-200);
            }
            else if(getAirDensity() >= 50) 
            {   
            if(getSpeed() == 900) setSpeed(getSpeed()-300);
            }
            else if(getAirDensity() >= 70) 
            {
                if(getSpeed() == 900) setSpeed(getSpeed()-500);
            }

            setOxygenRate(getOxygenRate()-10*(getAltitude() / 1000));

            if(getEnergy() < 0) setEnergy(0);
            if(getOxygenRate() < 0) setOxygenRate(0);

            setDistance(getDistance()-1000);
            setTraveled(getTraveled()+1000);

            if(getDistance() == 0)
            {
                printf("Successfully moved to next %d km\n",targetDistance);
                printf("Current Status: Airplane\n");
                cout << "Distance: " << getTraveled() << " km" << endl;
                cout << "Speed: " << getSpeed() << " km/hr" << endl;
                cout << "Energy: " << getEnergy() << endl;
                cout << "Oxygen Level: " << getOxygenRate() << endl;
                cout << "Temperature: " << getTemperature() << " C" << endl;
                cout << "Humidity: " << getHumidity() << endl;
                cout << "Altitude: " << getAltitude() << " m" << endl;
                cout << "Air Density: " << getAirDensity() << endl;
                return true;
            }
            else if(getEnergy() == 0)
            {
                printf("Successfully moved to next %d km\n",targetDistance);
                printf("Current Status: Airplane\n");
                cout << "Distance: " << getTraveled() << " km" << endl;
                cout << "Speed: " << getSpeed() << " km/hr" << endl;
                cout << "Energy: " << getEnergy() << endl;
                cout << "Oxygen Level: " << getOxygenRate() << endl;
                cout << "Temperature: " << getTemperature() << " C" << endl;
                cout << "Humidity: " << getHumidity() << endl;
                cout << "Altitude: " << getAltitude() << " m" << endl;
                cout << "Air Density: " << getAirDensity() << endl << endl;
                cout << "!FINISHED : Energy failure" << endl;

                return false;
            }
            else if(getOxygenRate() == 0)
            {
                printf("Successfully moved to next %d km\n",targetDistance);
                printf("Current Status: Airplane\n");
                cout << "Distance: " << getTraveled() << " km" << endl;
                cout << "Speed: " << getSpeed() << " km/hr" << endl;
                cout << "Energy: " << getEnergy() << endl;
                cout << "Oxygen Level: " << getOxygenRate() << endl;
                cout << "Temperature: " << getTemperature() << " C" << endl;
                cout << "Humidity: " << getHumidity() << endl;
                cout << "Altitude: " << getAltitude() << " m" << endl;
                cout << "Air Density: " << getAirDensity() << endl << endl;
                cout << "!FINISHED : Oxygen failure" << endl;

                return false;
            }
        }
    }

private:
    int Temperature;
    int Humidity;
    int Altitude;
    int AirDensity;
};

class Submarine : public Vehicle{
public:
    Submarine(){};
    static Submarine getSubmarine(string cases, Vehicle prev);

    Submarine(int Dis, int Tra, int Spd, int Enr, int Tem, int Dep, bool Lig, int Oxy, int Wat)
    {
        setDistance(Dis);
        setTraveled(Tra);
        setSpeed(Spd);
        setEnergy(Enr);
        setTemperature(Tem);
        setDepth(Dep);
        setLight(Lig);
        setOxygenRate(Oxy);
        setWaterFlow(Wat);
    }

    void setTemperature(int &Tem)
    {
        Temperature = Tem;
    }

    int getTemperature()
    {
        return Temperature;
    }

    void setDepth(int Dep)
    {
        Depth = Dep;
    }

    int getDepth()
    {
        return Depth; 
    }

    void setLight(bool Lig)
    {
        Light = Lig;
    }

    bool getLight()
    {
        return Light;
    }

    void setWaterFlow(int Wat)
    {
        WaterFlow = Wat;
    }

    int getWaterFlow()
    {
        return WaterFlow; 
    }

    void firstMoveMode()
    {
        if(getTemperature()>0 && getTemperature() < 40) setEnergy(getEnergy()-5);
        else if(getTemperature() >= 40) setEnergy(getEnergy()-10);
        else if(getTemperature() == 0) setEnergy(getEnergy()-8);

        setOxygenRate(getOxygenRate()-5*(getDepth() / 50));

        setEnergy(getEnergy()-30);

        if(getWaterFlow() >= 30) 
        {
            if(getSpeed() == 20) setSpeed(getSpeed()-3);
        }
        else if(getWaterFlow() >=50) 
        {
            if(getSpeed() == 20) setSpeed(getSpeed()-5);
        }
        else if(getWaterFlow() >= 70) 
        {
            if(getSpeed() == 20) setSpeed(getSpeed()-10);        
        }

        if(getEnergy() < 0) setEnergy(0);
        if(getOxygenRate() < 0) setOxygenRate(0);

        setDistance(getDistance()-10);
        setTraveled(getTraveled()+10);

        printf("Successfully moved to next 10 km\n");
        printf("Current Status: Submarine\n");
        cout << "Distance: " << getTraveled() << " km" << endl;
        cout << "Speed: " << getSpeed() << " km/hr" << endl;
        cout << "Energy: " << getEnergy() << endl;
        cout << "Oxygen Level: " << getOxygenRate() << endl;
        cout << "Temperature: " << getTemperature() << " C" << endl;
        cout << "Depth: " << getDepth() << " m" << endl;
        cout << "Water Flow: " <<  getWaterFlow() << endl;
    }

    bool secondMoveMode()
    {   
        int targetDistance = getDistance();
        while(true)
        {
            if(getTemperature()>0 && getTemperature() < 40) setEnergy(getEnergy()-5);
            else if(getTemperature() >= 40) setEnergy(getEnergy()-10);
            else if(getTemperature() == 0) setEnergy(getEnergy()-8);

            setOxygenRate(getOxygenRate()-5*(getDepth() / 50));

            setEnergy(getEnergy()-30);

            if(getWaterFlow() >= 30) 
            {
                if(getSpeed() == 20) setSpeed(getSpeed()-3);
            }
            else if(getWaterFlow() >=50) 
            {
                if(getSpeed() == 20) setSpeed(getSpeed()-5);
            }
            else if(getWaterFlow() >= 70) 
            {
                if(getSpeed() == 20) setSpeed(getSpeed()-10);        
            }

            if(getEnergy() < 0) setEnergy(0);
            if(getOxygenRate() < 0) setOxygenRate(0);

            setDistance(getDistance()-10);
            setTraveled(getTraveled()+10);

            if(getDistance() == 0)
            {
                printf("Successfully moved to next %d km\n",targetDistance);
                printf("Current Status: Submarine\n");
                cout << "Distance: " << getTraveled() << " km" << endl;
                cout << "Speed: " << getSpeed() << " km/hr" << endl;
                cout << "Energy: " << getEnergy() << endl;
                cout << "Oxygen Level: " << getOxygenRate() << endl;
                cout << "Temperature: " << getTemperature() << " C" << endl;
                cout << "Depth: " << getDepth() << " m" << endl;
                cout << "Water Flow: " <<  getWaterFlow() << endl;

                return true;
            }
            else if(getEnergy() == 0)
            {
               printf("Successfully moved to next %d km\n",targetDistance);
                printf("Current Status: Submarine\n");
                cout << "Distance: " << getTraveled() << " km" << endl;
                cout << "Speed: " << getSpeed() << " km/hr" << endl;
                cout << "Energy: " << getEnergy() << endl;
                cout << "Oxygen Level: " << getOxygenRate() << endl;
                cout << "Temperature: " << getTemperature() << " C" << endl;
                cout << "Depth: " << getDepth() << " m" << endl;
                cout << "Water Flow: " <<  getWaterFlow() << endl << endl;
                cout << "!FINISHED : Energy failure" << endl;
                return false;
            }
            else if(getOxygenRate() == 0)
            {
               printf("Successfully moved to next %d km\n",targetDistance);
                printf("Current Status: Submarine\n");
                cout << "Distance: " << getTraveled() << " km" << endl;
                cout << "Speed: " << getSpeed() << " km/hr" << endl;
                cout << "Energy: " << getEnergy() << endl;
                cout << "Oxygen Level: " << getOxygenRate() << endl;
                cout << "Temperature: " << getTemperature() << " C" << endl;
                cout << "Depth: " << getDepth() << " m" << endl;
                cout << "Water Flow: " <<  getWaterFlow() << endl << endl;
                cout << "!FINISHED : Oxygen failure" << endl;
                return false;
            }
        }
    }

private:
    int Temperature;
    int Depth;
    bool Light;
    int WaterFlow;
};


vector<string> getCases(string TC)
{
    vector<string> result;
    string substr;
    int begin = 0;

    while((begin = TC.find(',')) != string::npos)
    {
        substr = TC.substr(0,begin);
        if((TC.at(1) == 'X') || (TC.at(1) == 'Y'))
        {
            TC.erase(0,begin+1);
            continue;
        }
        else
        {
            result.push_back(substr);
            TC.erase(0,begin+1);
        } 
    }

    result.push_back(TC);

    return result;
}

string factorSplit(string cases)
{
    string str = cases;

    if(str.at(1) == 'R') //car
    {
        string R;
        string T;
        string H;
        int i;
        string factor;

        str.erase(0, 2);

        i = str.find('T');
        R = str.substr(0, i);

        str.erase(0, i+1);

        i = str.find('H');
        T = str.substr(0, i);

        str.erase(0, i+1);

        i = str.find(']');
        H = str.substr(0, i);

        factor = R + " " + T + " " + H;
        return factor;
    }
    else if(str.at(1) == 'S') //airplane
    {
        string S;
        string T;
        string H;
        string A;
        string D;
        int i;
        string factor;

        str.erase(0, 2);

        i = str.find('T');
        S = str.substr(0, i);

        str.erase(0, i+1);

        i = str.find('H');
        T = str.substr(0, i);

        str.erase(0, i+1);

        i = str.find('A');
        H = str.substr(0, i);

        str.erase(0, i+1);

        i = str.find('D');
        A = str.substr(0, i);

        str.erase(0, i+1);

        i = str.find(']');
        D = str.substr(0, i);

        factor = S + " " + T + " " + H + " " + A + " " + D;
        return factor;
    }
    else //submarine
    {
        string O;
        string T;
        string D;
        string W;
        int i;
        string factor;

        str.erase(0, 2);

        i = str.find('T');
        O = str.substr(0, i);

        str.erase(0, i+1);

        i = str.find('D');
        T = str.substr(0, i);

        str.erase(0, i+1);

        i = str.find('W');
        D = str.substr(0, i);

        str.erase(0, i+1);

        i = str.find(']');
        W = str.substr(0, i);

        factor = O + " " + T + " " + D + " " + W;
        return factor;
    }
}

int getFactor(string factors, int index)
{
    int blankFinder;
    string result;

    for(int i = 0 ; i < index ; i++)
    {
        blankFinder = factors.find(' ');
        factors.erase(0,blankFinder+1);
    }

    blankFinder = factors.find(' ');
    result = factors.substr(0,blankFinder);

    return atoi(result.c_str());
}

int initial();
void printBlackBox(string b1, string b2, string b3, string b4);
void run(string TC);

int main()
{
    int TCNUM;
    string TC1 = "[R50T20H20],[S3000T10H5A2000D30],[X],[O40T0D100W100],[R150T30H50]";
    string TC2 = "";
    string TC3 = "";
    string TC4 = "";
    string TC5 = "";
    string TC6 = "";
    string TC7 = "";
    string TC8 = "";
    string TC9 = "";
    string TC10 = ""; //would the test cases be given?    

    do{

    TCNUM = initial();

    if(TCNUM == 1) run(TC1); //casesplit -> factotsplit -> getfactor -> selectmode
    else if(TCNUM == 2) run(TC2);
    else if(TCNUM == 3) run(TC3);
    else if(TCNUM == 4) run(TC4);
    else if(TCNUM == 5) run(TC5);
    else if(TCNUM == 6) run(TC6);
    else if(TCNUM == 7) run(TC7);
    else if(TCNUM == 8) run(TC8);
    else if(TCNUM == 9) run(TC9);
    else if(TCNUM == 10) run(TC10);

    }while(true);

    return 0;
}

int initial()
{   
    int testCaseNumber;
    cout << "PJ1.HSY.2017-18538" <<endl;
    cout << "Choose the number of the test case (1~10) :";
    cin >> testCaseNumber;
    if(testCaseNumber>0 && testCaseNumber<=10) cout << "Test case #" << testCaseNumber << endl << endl;
    else if(testCaseNumber == 0) exit(EXIT_FAILURE);
    return testCaseNumber;
}

void run(string TC)
{
    vector<string> cases = getCases(TC);
    Car currentCar;
    Airplane currentAir;
    Submarine currentSub;
    string blackBoxMode = "";
    string blackBoxEnergy = "";
    string blackBoxOxygen = "";
    string blackBoxSpeed = "";

    int nextMove;

    for(int i = 0 ; i < cases.size() ; i++)
    {
        if(i == 0)
        {
            currentCar = Car::getCar(cases.at(0));

            printf("Current Status: Car\n");
            printf("Distance: 0 km\n");
            printf("Speed: 0 km/hr\n");
            printf("Energy: 1000\n");
            cout << "Temperature: " << currentCar.getTemperature() << " C" << endl;
            cout << "Humidity: " << currentCar.getHumidity() << endl;
            do
            {
                if(currentCar.getEnergy() <= 0)
                {
                    cout << endl <<"!FINISHED : Energy failure" << endl;
                    blackBoxMode += "Car";
                    blackBoxEnergy += to_string(currentCar.getEnergy());
                    blackBoxOxygen += "100";
                    blackBoxSpeed += to_string(currentCar.getSpeed());
                    printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                    return;
                }
                else if(currentCar.getDistance() <= 0)
                {
                    blackBoxMode += "Car";
                    blackBoxEnergy += to_string(currentCar.getEnergy());
                    blackBoxOxygen += "100";
                    blackBoxSpeed += to_string(currentCar.getSpeed());
                    break;
                }

                printf("Next Move? (1,2)\n");
                printf("CP-2017-18538>");
                cin >> nextMove;

                if(nextMove == 1) currentCar.firstMoveMode();
                else
                {
                    bool flag = currentCar.secondMoveMode();
                    blackBoxMode += "Car";
                    blackBoxEnergy += to_string(currentCar.getEnergy());
                    blackBoxOxygen += "100";
                    blackBoxSpeed += to_string(currentCar.getSpeed());
                    if(flag) break;
                    else
                    {
                        printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                        return;
                    }
                    
                }

            } while (true);
            
        }
        
        else
        {
            if(cases.at(i-1).at(1) == 'R')
            {
                if(cases.at(i).at(1) == 'S')
                {
                    currentAir = Airplane::getAirplane(cases.at(i),currentCar);

                    do{
                        if(currentAir.getDistance() <= 0 && i == cases.size()-1)
                        {
                            cout << endl <<"!FINISHED : Arrived" << endl;
                            blackBoxMode += " < Airplane";
                            blackBoxEnergy += " < "+to_string(currentAir.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentAir.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentAir.getSpeed());
                            break;
                        }
                        else if(currentAir.getEnergy() <= 0)
                        {
                            cout << endl <<"!FINISHED : Energy failure" << endl;
                            blackBoxMode += " < Airplane";
                            blackBoxEnergy += " < "+to_string(currentAir.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentAir.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentAir.getSpeed());
                            printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                            return;
                        }
                        else if(currentAir.getOxygenRate() <= 0)
                        {
                            cout << endl <<"!FINISHED : Oxygen failure" << endl;
                            blackBoxMode += " < Airplane";
                            blackBoxEnergy += " < "+to_string(currentAir.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentAir.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentAir.getSpeed());
                            printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                            return;
                        }
                        else if(currentAir.getDistance() <= 0)
                        {
                            blackBoxMode += " < Airplane";
                            blackBoxEnergy += " < "+to_string(currentAir.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentAir.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentAir.getSpeed());
                            break;
                        }

                        printf("Next Move? (1,2)\n");
                        printf("CP-2017-18538>");
                        cin >> nextMove;

                        if(nextMove == 1) currentAir.firstMoveMode();
                        else
                        {
                            bool flag = currentAir.secondMoveMode();
                            blackBoxMode += " < Airplane";
                            blackBoxEnergy += " < "+to_string(currentAir.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentAir.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentAir.getSpeed());
                            
                            if(flag) break;
                            else
                            {
                                printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                                return;
                            }
                        }
                    }while(true);

                }
                else
                {
                    currentSub = Submarine::getSubmarine(cases.at(i),currentCar);

                    do{
                        if(currentSub.getDistance() <= 0 && i == cases.size()-1)
                        {
                            cout << endl <<"!FINISHED : Arrived" << endl;
                            blackBoxMode += " < Submarine";
                            blackBoxEnergy += " < "+to_string(currentSub.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentSub.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentSub.getSpeed());
                            break;
                        }
                        else if(currentSub.getEnergy() <= 0)
                        {
                            cout << endl <<"!FINISHED : Energy failure" << endl;
                            blackBoxMode += " < Submarine";
                            blackBoxEnergy += " < "+to_string(currentSub.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentSub.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentSub.getSpeed());
                            printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                            return;
                        }
                        
                        else if(currentSub.getOxygenRate() <= 0)
                        {
                            cout << endl <<"!FINISHED : Oxygen failure" << endl;
                            blackBoxMode += " < Submarine";
                            blackBoxEnergy += " < "+to_string(currentSub.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentSub.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentSub.getSpeed());
                            printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                            return;
                        }
                        else if(currentSub.getDistance() <= 0)
                        {
                            blackBoxMode += " < Submarine";
                            blackBoxEnergy += " < "+to_string(currentSub.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentSub.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentSub.getSpeed());
                            break;
                        }

                        printf("Next Move? (1,2)\n");
                        printf("CP-2017-18538>");
                        cin >> nextMove;

                        if(nextMove == 1) currentSub.firstMoveMode();
                        else
                        {
                            bool flag = currentSub.secondMoveMode();
                            blackBoxMode += " < Submarine";
                            blackBoxEnergy += " < "+to_string(currentSub.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentSub.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentSub.getSpeed());
                            if(flag) break;
                            else
                            {
                                printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                                return;
                            }
                            
                        }
                    }while(true);
                }
                
            }
            else if(cases.at(i-1).at(1) == 'S')
            {
                if(cases.at(i).at(1) == 'R')
                {

                    currentCar = Car::getCar(cases.at(i),currentAir);

                    do
                    {
                        if(currentCar.getDistance() <= 0 && i == cases.size()-1)
                        {
                            cout << endl <<"!FINISHED : Arrived" << endl;
                            blackBoxMode += " < Car";
                            blackBoxEnergy += " < "+to_string(currentCar.getEnergy());
                            blackBoxOxygen += " < "+to_string(100);
                            blackBoxSpeed += " < "+to_string(currentCar.getSpeed());
                            break;
                        }
                        else if(currentCar.getEnergy() <= 0)
                        {
                            cout << endl <<"!FINISHED : Energy failure" << endl;
                            blackBoxMode += " < Car";
                            blackBoxEnergy += " < "+to_string(currentCar.getEnergy());
                            blackBoxOxygen += " < "+to_string(100);
                            blackBoxSpeed += " < "+to_string(currentCar.getSpeed());
                            printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                            return;
                        }
                        else if(currentCar.getDistance() <= 0)
                        {
                            blackBoxMode += " < Car";
                            blackBoxEnergy += " < "+to_string(currentCar.getEnergy());
                            blackBoxOxygen += " < "+to_string(100);
                            blackBoxSpeed += " < "+to_string(currentCar.getSpeed());
                            break;
                        }

                        printf("Next Move? (1,2)\n");
                        printf("CP-2017-18538>");
                        cin >> nextMove;

                        if(nextMove == 1) currentCar.firstMoveMode();
                        else
                        {
                            bool flag = currentCar.secondMoveMode();
                            blackBoxMode += " < Car";
                            blackBoxEnergy += " < "+to_string(currentCar.getEnergy());
                            blackBoxOxygen += " < "+to_string(100);
                            blackBoxSpeed += " < "+to_string(currentCar.getSpeed());

                            if(flag) break;
                            else
                            {
                                printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                                return;
                            }

                        }

                    }while(true);

                }
                else
                {  
                    currentSub = Submarine::getSubmarine(cases.at(i),currentAir);

                    do{
                        if(currentSub.getDistance() <= 0 && i == cases.size()-1)
                        {
                            cout <<endl << "!FINISHED : Arrived" << endl;
                            blackBoxMode += " < Submarine";
                            blackBoxEnergy += " < "+to_string(currentSub.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentSub.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentSub.getSpeed());
                            break;
                        }
                        else if(currentSub.getEnergy() <= 0)
                        {
                            cout << endl <<"!FINISHED : Energy failure" << endl;
                            blackBoxMode += " < Submarine";
                            blackBoxEnergy += " < "+to_string(currentSub.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentSub.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentSub.getSpeed());
                            printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                            return;
                        }
                        else if(currentSub.getOxygenRate() <= 0)
                        {
                            cout << endl <<"!FINISHED : Oxygen failure" << endl;
                            blackBoxMode += " < Submarine";
                            blackBoxEnergy += " < "+to_string(currentSub.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentSub.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentSub.getSpeed());
                            printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                            return;
                        }
                        else if(currentSub.getDistance() <= 0)
                        {
                            blackBoxMode += " < Submarine";
                            blackBoxEnergy += " < "+to_string(currentSub.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentSub.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentSub.getSpeed());
                            break;
                        }

                        printf("Next Move? (1,2)\n");
                        printf("CP-2017-18538>");
                        cin >> nextMove;

                        if(nextMove == 1) currentSub.firstMoveMode();
                        else
                        {
                            bool flag = currentSub.secondMoveMode();
                            blackBoxMode += " < Submarine";
                            blackBoxEnergy += " < "+to_string(currentSub.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentSub.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentSub.getSpeed());
                            if(flag) break;
                            else
                            {
                                printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                                return;
                            }
                        }
                    }while(true);
                }
            }
            else if(cases.at(i-1).at(1) == 'O')
            {
                if(cases.at(i).at(1) == 'S')
                {
                    currentAir = Airplane::getAirplane(cases.at(i),currentSub);

                    do{
                        if(currentAir.getDistance() <= 0 && i == cases.size()-1)
                        {
                            cout << endl <<"!FINISHED : Arrived" << endl;
                            blackBoxMode += " < Airplane";
                            blackBoxEnergy += " < "+to_string(currentAir.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentAir.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentAir.getSpeed());
                            break;
                        }
                        else if(currentAir.getEnergy() <= 0)
                        {
                            cout << endl <<"!FINISHED : Energy failure" << endl;
                            blackBoxMode += " < Airplane";
                            blackBoxEnergy += " < "+to_string(currentAir.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentAir.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentAir.getSpeed());
                            printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                            return;
                        } 
                        else if(currentAir.getOxygenRate() <= 0)
                        {
                            blackBoxMode += " < Airplane";
                            blackBoxEnergy += " < "+to_string(currentAir.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentAir.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentAir.getSpeed());
                            printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                            return;
                        }
                        else if(currentAir.getDistance() <= 0)
                        {
                            blackBoxMode += " < Airplane";
                            blackBoxEnergy += " < "+to_string(currentAir.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentAir.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentAir.getSpeed());
                            break;
                        }

                        printf("Next Move? (1,2)\n");
                        printf("CP-2017-18538>");
                        cin >> nextMove;

                        if(nextMove == 1) currentAir.firstMoveMode();
                        else
                        {
                            bool flag = currentAir.secondMoveMode();
                            blackBoxMode += " < Airplane";
                            blackBoxEnergy += " < "+to_string(currentAir.getEnergy());
                            blackBoxOxygen += " < "+to_string(currentAir.getOxygenRate());
                            blackBoxSpeed += " < "+to_string(currentAir.getSpeed());
                            if(flag) break;
                            else
                            {
                                printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                                return;
                            }
                        }
                    }while(true);

                }
                else
                {
                    currentCar = Car::getCar(cases.at(i),currentSub);

                    do
                    {
                        if(currentCar.getEnergy() <= 0)
                        {
                            cout << endl <<"!FINISHED : Energy failure" << endl;
                            blackBoxMode += " < Car";
                            blackBoxEnergy += " < "+to_string(currentCar.getEnergy());
                            blackBoxOxygen += " < "+to_string(100);
                            blackBoxSpeed += " < "+to_string(currentCar.getSpeed());
                            printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                            return;
                        }
                        else if(currentCar.getDistance() <= 0)
                        {
                            blackBoxMode += " < Car";
                            blackBoxEnergy += " < "+to_string(currentCar.getEnergy());
                            blackBoxOxygen += " < "+to_string(100);
                            blackBoxSpeed += " < "+to_string(currentCar.getSpeed());
                            break;
                        }
                        
                        printf("Next Move? (1,2)\n");
                        printf("CP-2017-18538>");
                        cin >> nextMove;

                        if(nextMove == 1) currentCar.firstMoveMode();
                        else
                        {
                            bool flag = currentCar.secondMoveMode();
                            blackBoxMode += " < Car";
                            blackBoxEnergy += " < "+to_string(currentCar.getEnergy());
                            blackBoxOxygen += " < "+to_string(100);
                            blackBoxSpeed += " < "+to_string(currentCar.getSpeed());
                            if(flag) break;
                            else
                            {
                                printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
                                return;
                            }
                        }

                    }while(true);
                }
                
            }
        }
    }
    cout << endl << "!FINISHED : Arrived" << endl;
    printBlackBox(blackBoxMode,blackBoxEnergy,blackBoxOxygen,blackBoxSpeed);
}

void printBlackBox(string b1, string b2, string b3, string b4)
{
    cout << "Blackbox:" << endl;
    cout << "Mode: " << b1 << endl;
    cout << "Energy Level: " << b2 << endl;
    cout << "Oxygen Level: " << b3 << endl;
    cout << "Speed: " << b4 << endl;
    cout << "--------------------" << endl;
}

Car Car :: getCar(string cases)
{
    string factors = factorSplit(cases);
    int Distance = getFactor(factors,0);
    int Temperature = getFactor(factors,1);
    int Humidity = getFactor(factors,2);
    bool solarPanel = false;
    if(Humidity < 50) solarPanel = true;
    Car result(Distance,0,80,1000,Temperature,Humidity,100,solarPanel);

    return result;

}

Car Car :: getCar(string cases, Vehicle prev)
{
    string factors = factorSplit(cases);
    int Distance = getFactor(factors,0);
    int Temperature = getFactor(factors,1);
    int Humidity = getFactor(factors,2);
    bool solarPanel = false;
    if(Humidity < 50) solarPanel = true;
    Car result(Distance,0,80,prev.getEnergy(),Temperature,Humidity,100,solarPanel);

    return result;
}

Airplane Airplane :: getAirplane(string cases, Vehicle prev)
{
    string factors = factorSplit(cases);
    int Distance = getFactor(factors,0);
    int Temperature = getFactor(factors,1);
    int Humidity = getFactor(factors,2);
    int Altitude = getFactor(factors,3);
    int Density = getFactor(factors,4);
    Airplane result(Distance,0,900,prev.getEnergy(),Temperature,Humidity,Altitude,prev.getOxygenRate(),Density);

    return result;
}

Submarine Submarine :: getSubmarine(string cases, Vehicle prev)
{
    string factors = factorSplit(cases);
    int Distance = getFactor(factors,0);
    int Temperature = getFactor(factors,1);
    int Depth = getFactor(factors,2);
    int Water = getFactor(factors,3);
    Submarine result(Distance,0,20,prev.getEnergy(),Temperature,Depth,true,prev.getOxygenRate(),Water);

    return result;
}
