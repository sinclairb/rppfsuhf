import java.util.*;
import java.io.*;

public class Hashcode
{
    
    // method to hash the password
    public static int hashFunction(String password, int prime)
    {
        // total of added ASCII characters = 0
        int total=0;
        // convert any capital letters in password to lowercase
        password = password.toLowerCase();
        // eliminate special characters
        for(int i=0; i<password.length(); i++)
        {
            char sym = password.charAt(i);
            // checks if each character is a letter
            if(Character.isLetter(sym))
            {
                // if the character is a letter, convert it to ASCII value and add the value to the total
                total += (int)sym;
            }
        }
        
        // print total added values (debugging)
        System.out.println("The total ASCII value of the password is: " + total);
        
        // HASHFUNCTION: hash = remainder of total / prime
        int hash = total % prime;
        
        // return hashed password
        return hash;
    }
    
    // method to start dehashing
    public static ArrayList<Integer> totals(int hash, int prime) throws IOException
    {
        // create arraylist of possible totals
        ArrayList <Integer> possibleTotals = new ArrayList<Integer>(0);
        // calculates possible totals
        for (int i=0; i<100; i++)
        {
            // passwords can only be lowercase letters, therefore
            // totals can only be between 388(aaaa) or 488(zzzz)
            // therefore, any possible totals outside of those values can be disregarded
            if (i * prime + hash >= 388 && i * prime + hash <= 488)
            {
                // calculates possible totals 
                int tempTotal = i * prime + hash;
                // & saves to array
                possibleTotals.add(tempTotal);
            }
        }
        
        // return all possible totals of password hash
        return possibleTotals;
    }
    
    // creates list of posible passwords that are words
    public static ArrayList<String> dictionary(ArrayList<Integer> possibleTotals) throws IOException
    {
        // initializes arraylist of words read from txt file
        ArrayList <String> fourLetterWords = new ArrayList(0);
        String line;
        FileReader inFile = new FileReader("dictionary.txt");
        BufferedReader in = new BufferedReader(inFile);
        line = in.readLine();
        
        // while the line being read isn't blank,
        while(line != null)
        {
            // check to see if the word on the line is four letters long
            if(line.length() == 4)
            {
                // if it is, add it to the fourLetterWord arraylist
                fourLetterWords.add(line);
            }
            line = in.readLine();
        }
        
        // creates new arraylist to be filled with words that add to any of the possible totals
        ArrayList <String> possiblePasswords = new ArrayList(0);
        String temp;
        // check to see if each 4 letter word adds to each possible total
        for(int aFourLetterWord=0; aFourLetterWord<fourLetterWords.size(); aFourLetterWord++)
        {
            temp = fourLetterWords.get(aFourLetterWord);
            int total = 0;
            // adds the ASCII values of the four letter word
            for(int charNumber=0; charNumber<temp.length(); charNumber++)
            {
                char letter = temp.charAt(charNumber);
                total += (int)letter;
            }
            // checks to see if the total of the 4 letter word is equal to any of the possible totals
            // looks at each entry in possibleTotals
            for (int aPossibleTotal=0; aPossibleTotal<possibleTotals.size(); aPossibleTotal++)
            {
                // compares the possible total to the total of the 4 letter word
                if (total == possibleTotals.get(aPossibleTotal))
                {
                    // if they are equal, add the word to an arraylist of possible passwords
                    possiblePasswords.add(temp);
                }
            }
        }
        
        // return all possible english words of password hash
        return possiblePasswords;
    }
    
    // calculates possible letter permutations that add to one of the possible totals
    public static ArrayList<String> permutations(ArrayList<Integer> possibleTotals, ArrayList<String> possiblePasswords)
    {
        int dict = 0;
        // adds each permutation of letters, counting: { aaaa, aaab, aaac ... aaba, aabb, aabc ... zzzz }
        for(int letterOne=97; letterOne<=122; letterOne++)
        {
            for(int letterTwo=97; letterTwo<=122; letterTwo++)
            {
                for(int letterThree=97; letterThree<=122; letterThree++)
                {
                    for(int letterFour=97; letterFour<=122; letterFour++)
                    {
                        // adds separate characters to total
                        int total = (letterOne)+(letterTwo)+(letterThree)+(letterFour);
                        // check if the permu total is equal to any of the possible totals
                        // looks at each entry in possibleTotals
                        for (int i=0; i<possibleTotals.size(); i++)
                        {
                            // compares the possible total to the total of the permu
                            if (total == possibleTotals.get(i))
                            {
                                // if they are equal, convert each letter's ASCII value into an actual letter
                                // and add them together to be a string
                                String posspw = new StringBuilder().append((char)(letterOne)).append((char)(letterTwo)).append((char)(letterThree)).append((char)(letterFour)).toString();
                                
                                // eliminate any repetition of passwords already found in dictionary method
                                if (possiblePasswords.size() != 0 && posspw.equals(possiblePasswords.get(dict))) // automaticaly add the posspw to the arraylist of possible passwords, if there are no dictionary words on the list, to eliminate crashes
                                {
                                    dict++;
                                }
                                else
                                {
                                    // if posspw wasn't already found in the dictionary, adds posspw to an arraylist of possible passwords
                                    possiblePasswords.add(posspw);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // returns the arraylist of possible passwords, starting with any words from the dictionary, and ending with letter permus
        return possiblePasswords;
    }
    
    // method returns the first prime number greater than or equal to the input number
    // input a number to be used as the prime
    public static int toPrime(int num)
    {
        // a natural number is prime when it has no divisors other than 1 and itself
        // therefore, the program can start counting at two
        int i=2;
        // checks if the input number can be evenly divided by any number greater than 1
        // checks all numbers less than half of the input number;
        // because once half of the numbers are used, the pattern will repeat
        // i.e. input=10 10/2 = 5 and 10/5 = 2. therefore, it would be repetitive to check if input is divisible by any number greater than 1/2 of it
        while(num % i != 0 && i<= num/2)
        {
            i++;
        }
        
        // if the number is not prime, increase the number by 1 and check if That number is prime
        // repeat until a prime is reached
        // this is to make sure that the number used in the hashfunction is prime, which decreases collisions
        if(num % i == 0)
        {
            num++;
            num = toPrime(num);
        }
        
        return num;
    }
}

