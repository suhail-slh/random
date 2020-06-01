import java.io.*;
import java.lang.*;
import java.util.*;
public class num_to_words_int_v1
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String number = scan.nextLine();
        while(number.charAt(0)=='0' && Integer.parseInt(number)!=0)
        {
            number = number.substring(1,number.length());
        }
        String word = convert_this(number);
        //to strip unnecessary whitespace, useless otherwise
        int space=0;
        String final_word="";
        for(int i=0;i<word.length();i++)
        {
            if(Character.isWhitespace(word.charAt(i))==true)
            {
                if(space==1)
                {
                    final_word+=" ";
                    space=0;
                }
                continue;
            }
            final_word+=word.substring(i,i+1);
            space=1;
        }
        System.out.println(final_word);
    }
    public static String convert_this(String s)
    {
        int n = s.length();
        String word="";
        int i,j,k=n;
        if(Integer.parseInt(s)==0)
            word = "ZERO";
        else
        {
            for(i=0;i<n;i=i+j)
            {
                if(k%3 == 0)
                    j = 3;
                else if(k%3 == 1)
                    j = 1;
                else
                    j = 2;
                word = word + get_word(s.substring(i,i+j),k);
                k-=j;
            }
        }
        return word;
    }
    public static String get_word(String s, int m)
    {
        int n = s.length();
        String word = "";
        Hashtable<String, String> u = new Hashtable<String,String>();
            u.put("0","");
            u.put("1"," ONE ");
            u.put("2"," TWO ");
            u.put("3"," THREE ");
            u.put("4"," FOUR ");
            u.put("5"," FIVE ");
            u.put("6"," SIX ");
            u.put("7"," SEVEN ");
            u.put("8"," EIGHT ");
            u.put("9"," NINE ");
            
        Hashtable<String, String> t = new Hashtable<String,String>();
            t.put("0","");
            t.put("1"," TEN ");
            t.put("11"," ELEVEN ");
            t.put("12"," TWELVE ");
            t.put("13"," THIRTEEN ");
            t.put("14"," FOURTEEN ");
            t.put("15"," FIFTEEN ");
            t.put("16"," SIXTEEN ");
            t.put("17"," SEVENTEEN ");
            t.put("18"," EIGHTEEN ");
            t.put("19"," NINETEEN ");
            t.put("2"," TWENTY ");
            t.put("3"," THIRTY ");
            t.put("4"," FORTY ");
            t.put("5"," FIFTY ");
            t.put("6"," SIXTY ");
            t.put("7"," SEVENTY ");
            t.put("8"," EIGHTY ");
            t.put("9"," NINETY ");
         
        //System.out.println(n+" "+m+" "+s);   
        
        while(n!=0)
        {
            switch (m) {
                case 1:
                    word += u.get(s.substring(0,1));
                    m--;
                    break;
                case 2:
                case 5:
                case 8:
                case 11:
                    if(s.charAt(1)=='0')
                    {
                        word += t.get(s.substring(0,1));
                        s = s.substring(1,n);
                    }
                    else if(s.charAt(0)=='1' && s.charAt(1)!='0')
                    {
                        word += t.get(s.substring(0,2));
                        s = s.replace(s.charAt(1), '0');
                        s = s.substring(1,n);
                    }
                    else
                    {
                        word += t.get(s.substring(0,1));
                        s = s.substring(1,n);
                    }
                    m--;
                    break;
                case 3:
                case 6:
                case 9:
                case 12:
                    word += u.get(s.substring(0,1)); //gives null if not used
                    if(s.charAt(0)!='0')
                        word+= "HUNDRED ";
                    s = s.substring(1,n);
                    m--;
                    break;
                case 4:
                    word += u.get(s.substring(0,1)) +"THOUSAND";
                    s = s.substring(1,n);
                    m--;
                    break;
                case 7:
                    word += u.get(s.substring(0,1)) +"MILLION";
                    s = s.substring(1,n);
                    m--;
                    break;
                case 10:
                    word += u.get(s.substring(0,1)) +"BILLION";
                    s = s.substring(1,n);
                    m--;
                    break;
                default:
                    break;
            }
            n--;
        }
        return word;
    }   
}
