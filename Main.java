import java.util.*;
public class Main
{
    public static int add(String str)
    {
        if(str.length() == 0)  // empty string
            return 0;

        String numbers[];   // , seperator
        if(str.charAt(0) != '/')
        {
            numbers = str.split(",|\n");
        }

        else if(str.length() > 2 && str.charAt(2) != '[')// single or mulitple delimiters other than ","
        {
                String delimiter = str.substring(2,str.indexOf('\n'));  // finding the delimiters

                try {                                      // if the delimiter is not a meta character
                    numbers = str.split(String.format("[%s]",delimiter));
                }
                catch(Exception e)  // if the delimiter is a meta character
                {
                    numbers = str.substring(2).split(String.format("\\%s", delimiter));
                }
        }

        else  // delimiter with multiple characters
        {
            String delimiter;
            int start = 3;
            int end = str.indexOf("]\n");
            delimiter=str.substring(start, end);  // finding the delimiter  format: //[delimiter]
            str = str.replaceAll("\n",delimiter);  //replacing \n with delimiters for seperating
            numbers = str.split(String.format("[%s]",delimiter));
        }

        return sol(numbers);   // adding all the numbers
    }

    public static int sol(String numbers[])
    {
        int res=0;  // stores the result
        boolean flag = false;  // to check if negative values are present or not
        ArrayList<Integer> negative = new ArrayList<>();  // storing all the -ve numbers to print at the end

        for(int i=0; i<numbers.length; i++)
        {
            try{
                int num = Integer.parseInt(numbers[i].trim());  // if the numbers[i] is an Integer
                if(num < 0)  // for negative numbers, add in the arrayList
                {
                    negative.add(num);
                    flag = true;
                }

                if(num > 1000)  // skipping numbers >1000
                    continue;

                res+=num;
            }
            catch(Exception e)   // if parsed String is not an Integer, skip
            {
                continue;
            }
        }

        if(flag)  // negatives present
        {
            System.out.print(negative+" negatives not allowed ");
            return 0;
        }

        return res;
    }
    public static void main(String[] args)
    {

        System.out.println(add("")); // case1 empty
        System.out.println(add("1"));
        System.out.println(add("100,2,70,34,23")); // case2 unknown amount of numbers

        System.out.println(add("1\n2,3")); // case3 \n & comma separated

        System.out.println(add("//;\n1;2")); //case4 delimiter other than comma

        System.out.println(add("//;\n-1;2;4"));  // case5 negatives

        System.out.println(add("//.\n1002.2"));  // case6 number greater than 1000

        System.out.println(add("//[***]\n1***2***3")); // case7 delimiter with multiple characters

        System.out.println(add("//;.\n100;2.34"));  //case8 multiple delimiters of single character

        //ONLY case 9 i.e handling multiple delimiters of multiple characters not done

    }
}
