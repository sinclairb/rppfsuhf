// "A password must be four letters long. Passwords are not case-sensitive. Numerical digits and special characters will be discounted."
// 
// This indicates that:
// all passwords are exactly four characters long
// upper and lowercase letters will resolve to the same hash
// any non alphabetical characters will not contribute to the hash
// 
// These password regulations will directly influence how the hashes will be cracked, and will be explained further as the dehashing code is demonstrated 
// 

import java.util.*;
import java.io.*;

public class Driver
{
    public static void main() throws IOException
    {
        // input password string
        String password = Inout.inputString("Enter Password: ");
        
        // input prime number to be used in hashfunction
        int num = Inout.inputInt("Enter number to be used in hashfunction: ");
        
        int prime = Hashcode.toPrime(num);
        
        // call Hash class and pass password & prime number
        int hash = Hashcode.hashFunction(password, prime);
        
        // then print the hashed password
        System.out.println("The hashed password is: " + hash);
        
        String response = Inout.inputString("Call Dehash? y/n: ");
        if (response.equals("y"))
        {
            Driver.Dehash(hash, prime);
        }
    }
    
    public static void Dehash() throws IOException
    {
        // input the hash number of the original password
        int hash = Inout.inputInt("Enter hash: ");
        
        // input prime number that was used to hash the password
        int num = Inout.inputInt("Enter prime number used in hashfunction: ");
        
        int prime = Hashcode.toPrime(num);
        
        // call totals method and get the possibleTotals arraylist
        ArrayList <Integer> possibleTotals = (Hashcode.totals(hash, prime));
        // print number of possible ASCII totals
        System.out.println("Number of possible totals: "+possibleTotals.size());
        // print all possible ASCII totals
        System.out.println("Possible Totals: "+possibleTotals);
        
        // call dictionary method and get the possiblePasswords arraylist
        // get all possible english passwords
        ArrayList <String> possiblePasswords = (Hashcode.dictionary(possibleTotals));
        // print number of possible dictionary passwords
        int pdpw = possiblePasswords.size();
        System.out.println("Number of possible dictionary passwords: "+pdpw);
        
        // call permutations method and get the possiblePasswords arraylist, now with dictionary and permu passwords
        possiblePasswords = (Hashcode.permutations(possibleTotals, possiblePasswords));
        // print number of possible permu passwords
        int pcpw = (possiblePasswords.size() - pdpw);
        System.out.println("Number of possible non-dictionary passwords: "+ pcpw);
        
        // print number of all possible passwords
        System.out.println("Number of possible passwords: "+possiblePasswords.size());
        
        System.out.println();
        
        // decide to print passwords horizontally or vertically 
        String response = Inout.inputString("Print passwords vertically? y/n: ");
        if (response.equals("y"))
        {
            Driver.printVert(possiblePasswords);
        }
        else
        {
            System.out.println("\nPossible passwords: "+possiblePasswords);
        }
    }
    
    public static void Dehash(int h, int p) throws IOException
    {
        // input the hash number of the original password
        int hash = h;
        
        // input prime number that was used to hash the password
        int prime = p;
        
        // call totals method and get the possibleTotals arraylist
        ArrayList <Integer> possibleTotals = (Hashcode.totals(hash, prime));
        // print number of possible ASCII totals
        System.out.println("Number of possible totals: "+possibleTotals.size());
        // print all possible ASCII totals
        System.out.println("Possible Totals: "+possibleTotals);
        
        // call dictionary method and get the possiblePasswords arraylist
        // get all possible english passwords
        ArrayList <String> possiblePasswords = (Hashcode.dictionary(possibleTotals));
        // print number of possible dictionary passwords
        int pdpw = possiblePasswords.size();
        System.out.println("Number of possible dictionary passwords: "+pdpw);
        
        // call permutations method and get the possiblePasswords arraylist, now with dictionary and permu passwords
        possiblePasswords = (Hashcode.permutations(possibleTotals, possiblePasswords));
        // print number of possible permu passwords
        int pcpw = (possiblePasswords.size() - pdpw);
        System.out.println("Number of possible non-dictionary passwords: "+ pcpw);
        
        // print number of all possible passwords
        System.out.println("Number of possible passwords: "+possiblePasswords.size());
        
        System.out.println();
        
        // decide to print passwords horizontally or vertically 
        String response = Inout.inputString("Print passwords vertically? y/n: ");
        if (response.equals("y"))
        {
            Driver.printVert(possiblePasswords);
        }
        else
        {
            System.out.println("\nPossible passwords: "+possiblePasswords);
        }
    }
    
    public static void printVert(ArrayList<String> possiblePasswords)
    {
        System.out.println();
        
        // print all possible english passwords and letter permutations
        System.out.println("All possible passwords:\n");
        for (int i=0; i<possiblePasswords.size(); i++)
        {
            System.out.println(possiblePasswords.get(i));
        }
    }
    
    public static void forcePrime()
    {
        int num = Inout.inputInt("Enter a number: ");
        while(num != -1)
        {
            System.out.println(Hashcode.toPrime(num));
            num = Inout.inputInt("Enter a number: ");
        }
    }
    
    public static void howMany()
    {
        double num = Inout.inputInt("Enter max length of password: ");
        double total = 0;
        for (int i=0; i<num; i++)
        {
            total += Math.pow(26, (i+1));
        }
        System.out.println("Number of permutations: "+total);
        System.out.println("Number of pages: "+total / 56);
        double pgdistin = (total / 56)*11;
        System.out.println("Distance of pages: "+pgdistin +" inches");
        double pgdistmi = pgdistin/63360;
        System.out.println("Distance of pages: "+pgdistmi +" miles");
        System.out.println("Distance to moon: "+pgdistmi/238900 +" trips to the moon");
    }
}