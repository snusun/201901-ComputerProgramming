import java.util.ArrayList;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.*;
import java.util.Scanner;

public class PhoneBook
{
    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();
        int count = 0;
        initial();

        while(true)
        {
            String input = selecting();

            if(input.equals("1"))
            {
                while (true)
                {
                System.out.println("Select Type\n1.Person\n2.Work\n3.Family\n4.Friend");
                String select = selecting();

                if(select.equals("1")) people.add(getPerson());
                else if(select.equals("2")) people.add(getWork());
                else if(select.equals("3")) people.add(getFamily());
                else if(select.equals("4")) people.add(getFriend());
                break;
                // else printError();
                }
                count++;
            }
            else if(input.equals("2"))
            {
                System.out.print("Enter index of person: ");
                int index = sc.nextInt();

                if(index <= count && index >= 0)
                {
                    people.remove(index-1);
                    System.out.println("A person is successfully deleted from the Phone Book!");
                }
                else
                {
                    System.out.println("Person does not exist!");
                }
                count--;
            }
            else if(input.equals("3"))
            {
                int i = 1;
                for(Person one : people)
                {
                    System.out.print((i++)+". ");
                    one.print();
                }
            }
            else if(input.equals("exit"))
            {
                break;
            }
            else if(input.equals(""))
            {
                System.out.println("Phone Book\n1.Add person\n2.Remove person\n3.Print phone book");
                continue;
            }
        }
    }

    public static int putPhoneNum() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();

        Pattern p = Pattern.compile("^\\d{2,3}-\\d{4}-\\d{4}");
        Matcher m = p.matcher(input);

        while (!m.matches() || !(input.substring(0, 2).equals("02") || input.substring(0, 3).equals("010"))) {
            System.out.print("Phone_number: ");
            input = bf.readLine();
            m = p.matcher(input);
        }

        int[] pnums = new int[3];
        pnums[0] = Integer.parseInt(input.split("-")[0]);
        pnums[1] = Integer.parseInt(input.split("-")[1]);
        pnums[2] = Integer.parseInt(input.split("-")[2]);

        int phoneNumber = pnums[0] * 100000000 + pnums[1] * 10000 + pnums[2];
        return phoneNumber;
    }

    public static void initial()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("CP-2017-18538>(input enter)");
        String init = sc.nextLine();
        if(init.equals("")) System.out.println("Phone Book\n1.Add person\n2.Remove person\n3.Print phone book");
        else if(init.equals("exit"))  System.exit(0);
        else;
    }

    public static String selecting() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("CP-2017-18538>");
        String sel = reader.readLine();
        return sel;
    }

    public static String getName() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name;
        boolean valName;
        boolean valType;

        do
        {
            System.out.print("Name: ");
            name = reader.readLine();
            valName = true;
            valType = false;

            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) >= '0' && name.charAt(i) <= '9')
                {
                    valName = false;
                }
            }


            name = name.trim();

            if(name.indexOf(" ") != -1)
            {
                valType = true;
            }

        }while(!valName || !valType);

        return name;
    }

    public static Person getPerson() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Name: ");
        String name = getName();
        String first = name.split(" ")[0];
        String second = name.split(" ")[1];
        System.out.print("Phone_number: ");
        int pn = putPhoneNum();

        System.out.println("Successfully added new person.");
        Person result = new Person(first, second, pn);
        return result;
    }

    public static Work getWork() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Name: ");
        String name = getName();
        String first = name.split(" ")[0];
        String second = name.split(" ")[1];
        System.out.print("Phone_number: ");
        int pn = putPhoneNum();
        System.out.print("Team: ");
        String tm = br.readLine();

        System.out.println("Successfully added new person.");
        Work result = new Work(first, second, pn, tm);
        return result;
    }

    public static Family getFamily() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Name: ");
        String name = getName();
        String first = name.split(" ")[0];
        String second = name.split(" ")[1];
        System.out.print("Phone_number: ");
        int pn = putPhoneNum();
        System.out.print("Birthday: ");
        String bD = br.readLine();

        Pattern p = Pattern.compile("^\\d{6}");
        Matcher m = p.matcher(bD);

        while(!m.matches())
        {
            System.out.print("Birthday: ");
            bD = br.readLine();
            m = p.matcher(bD);
        }

        System.out.println("Successfully added new person.");
        Family result = new Family(first, second, pn, bD);
        return result;
    }

    public static Friend getFriend() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Name: ");
        String name = getName();
        String first = name.split(" ")[0];
        String second = name.split(" ")[1];
        System.out.print("Phone_number: ");
        int pn = putPhoneNum();
        System.out.print("Age: ");
        int age = Integer.parseInt(br.readLine());
        while(age<=0)
        {
            System.out.print("Age: ");
            age = Integer.parseInt(br.readLine());
        }
        System.out.println("Successfully added new person.");
        Friend result = new Friend(first, second, pn, age);
        return result;
    }
}

class Person
{
    public Person(){}
    private String firstName;
    private String lastName;
    private int phoneNumber;
    //객체생성
    public Person(String first, String last, int phoneNum)
    {
        setFirstName(first);
        setLastName(last);
        setPhoneNumber(phoneNum);
    }
    public void setFirstName(String first)
    {
        this.firstName = first;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setLastName(String last)
    {
        this.lastName = last;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setPhoneNumber(int phoneNum)
    {
        this.phoneNumber = phoneNum;
    }
    public String constructPhone()
    {
        String pNum = "";
        int tmp = this.phoneNumber;
        int [] phoneNum = new int [3];
        phoneNum[2] = tmp%10000;
        tmp/=10000;
        phoneNum[1] = tmp%10000;
        tmp/=10000;
        phoneNum[0] = tmp;

        pNum += "0" + phoneNum[0];
        for(int i = 1 ; i<3 ; i++)
        {
            if (phoneNum[i] >= 1000)
            {
                pNum += "-" + phoneNum[i];
            }
            else if (phoneNum[i]<1000 && phoneNum[i]>=100)
            {
                pNum += "-0" + phoneNum[i];
            }
            else if (phoneNum[i]<100 && phoneNum[i]>=10)
            {
                pNum += "-00" + phoneNum[i];
            }
            else
            {
                pNum += "-000" + phoneNum[i];
            }
        }

        return pNum;
    }
    public void print()
    {
        System.out.println(firstName+" "+lastName+"_"+constructPhone());
    }
}

class Work extends Person
{
    private String team;
    public Work(String first, String last, int phoneNum, String tm)
    {
        setFirstName(first);
        setLastName(last);
        setPhoneNumber(phoneNum);
        setTeam(tm);
    }
    public void setTeam(String tm)
    {
        this.team = tm;
    }
    public void print()
    {
        System.out.println(getFirstName()+" "+getLastName()+"_"+constructPhone()+"_"+team);
    }

}

class Family extends Person
{
    private String birthday;
    public Family(String first, String last, int phoneNum, String bd)
    {
        setFirstName(first);
        setLastName(last);
        setPhoneNumber(phoneNum);
        setBirthday(bd);
    }
    public void setBirthday(String bd)
    {
        this.birthday = bd;
    }
    public int dDay()
    {
        Calendar cal = Calendar.getInstance();
        int [] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int birthmonth = Integer.parseInt(birthday.substring(2,4));
        int birthdays = Integer.parseInt(birthday.substring(4,6));
        int cmonth = cal.get(Calendar.MONTH) + 1;
        int cdays = cal.get(Calendar.DAY_OF_MONTH);

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
    public void print()
    {
        System.out.println(getFirstName()+" "+getLastName()+"_"+constructPhone()+"_"+birthday+"_"+dDay());
    }
}

class Friend extends Person
{
    private int age;
    public Friend(String first, String last, int phoneNum, int Age)
    {
        setFirstName(first);
        setLastName(last);
        setPhoneNumber(phoneNum);
        setAge(Age);
    }
    public void setAge(int Age)
    {
        this.age = Age;
    }
    public void print()
    {
        System.out.println(getFirstName()+" "+getLastName()+"_"+constructPhone()+"_"+age);
    }
}