
public class StringRecursion {

    // task 1
    public static void printLetters(String str){
        if (str == null || str.isEmpty())
            return;
        if(str.length() ==1){
            System.out.print(str);
            return;
        }

    String print = "";
    print += str.charAt(0);
    print += ", ";
    System.out.print(print);    
    printLetters(str.substring(1));    
    }


    // task 2
    public static String replace(String str, char oldChar, char newChar){
        String print = "";
        if(str == null){
            return null;
        }
        else if (str.isEmpty())
            return "";
    
        if(str.charAt(0) == oldChar){
            print += newChar;
        }
        else {
            print += str.charAt(0);  
        }
        return print + replace(str.substring(1),oldChar, newChar);
    }
    

    // task 3
    public static int findLast(char ch, String str){
        if(str == null || str.isEmpty()){
            return -1;
        }
        if(str.charAt(str.length() -1) == ch){
            return str.length() -1;
        }
        return findLast(ch, str.substring(0, str.length()-1));
            
    }


    public static void main(String[] args) {
        // printLetters("Rabbit"); 
        // printLetters("I like to recurse!");
        // System.out.print(replace("base case", 'r', 'y')); 
        // System.out.print(replace("base case", 'e', 'y'));
        // System.out.print(findLast('r', "recurse"));
        // System.out.print(findLast('p', "recurse"));
}
    
}

