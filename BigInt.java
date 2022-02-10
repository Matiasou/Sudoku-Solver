import java.util.*;

//

/* 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }
    // checks if the elements within the array are integers
    public boolean checker(int x){
        if(x <= 9 && x>=0){
            return true;
        }
        return false;

    }
    // task 1 uses contents of the array that is passed in as the basis of the new BigInt object 
    public BigInt(int[] arr){
        int count = 0;
        this.digits = new int[SIZE];
        if (arr == null){
            throw new IllegalArgumentException();
        }
        if (arr.length >20){
            throw new IllegalArgumentException();
        }
        else {
            for (int i=0; i < arr.length; i++){
                digits[SIZE - arr.length +i] = arr[i];
                if (arr[i] != 0){
                    count++;
                }
            }
        }
        this.numSigDigits = count;
    }

    // task 2 its an accesor method for the number of significant digits within the bigint
    public int getNumSigDigits(){
        return this.numSigDigits;
    }
    
    // it returns the string of the bigint object so it can be printed
    public String toString(){
        String print = "";
        for (int i = 0; i < digits.length; i++){
            if(digits[i] != 0){
                print+= digits[i];
            }
        } 
        return print;
    }

    // task 3 it creates a bigint like before but from the parameter interger. it will also return the amount of significant integers the bigint contains
    public BigInt(int n){
        int countDigits = 0;
        if (n < 0){
            throw new IllegalArgumentException();
        } else {
        int iterations = 0;
        int copy = n;
        while(copy > 0){
            iterations++;
            copy/=10;
        }
        this.digits = new int[SIZE];
        for(int i =0; i<iterations; i++){
            this.digits[digits.length-1-i] = n % 10;
            if(n%10 != 0){
                countDigits++;
            }
            n /=10;
        }
    }
    this.numSigDigits = countDigits; 
    }

    // Comapres another Bigint to the Digits and returns which one is larger
    public int compareTo(BigInt other){
        if(other == null){
            throw new IllegalArgumentException();
        }else{
            if( this.numSigDigits > other.numSigDigits){
                return 1;
            }else if (this.numSigDigits < other.numSigDigits){
                return -1;
            }else {
                for(int i = this.numSigDigits; i >=0; i--){
                    if(this.digits[digits.length-i-1] > other.digits[other.digits.length-i-1]){
                        return 1;
                    }else if(this.digits[digits.length-i-1]< other.digits[other.digits.length-i-1]){
                        return -1;
                    }
                }
            }
        }  
        return 0;
    }    
    
    // this method creates a new bigint and adds other to digits.
    public BigInt add(BigInt other){
        int counter = 0;
        if(other == null){
            throw new IllegalArgumentException();
        }
        if(this.numSigDigits +1 > SIZE || other.numSigDigits +1 > SIZE){
            throw new ArithmeticException();
        }
        BigInt x = new BigInt();
        int carry = 0;
        for(int i = SIZE -1; i>=0; --i){
            int r = (digits[i] + other.digits[i] + carry);
            if(r>9){
                carry = 1;
                r=r-10;
            } else {
                carry = 0;
            }
            x.digits[i] = r;
        }
        if(carry == 1){
            x.digits[0] = -1;
        }
        for(int i =0; i < x.digits.length; i++){
            if(x.digits[i] != 0){
                counter++;
            }
        }
        x.numSigDigits = counter;
        return x;
    }

    //this method creates a new BigInt by multiplying other and digits.
    public BigInt mul(BigInt other){
        int counterSigDigits= 0;
        if(other == null){
            throw new IllegalArgumentException();
        }
        if(this.numSigDigits +1 > SIZE || other.numSigDigits +1 > SIZE){
            throw new ArithmeticException();
        }
        BigInt counter = new BigInt(0);
        BigInt result = new BigInt(0);
        BigInt k = new BigInt(1);

        while(counter.compareTo(other) ==-1){
            result = result.add(this);
            counter = counter.add(k);
        }
        for(int i=0; i<result.digits.length; i++){
            if(result.digits[i] !=0){
                counterSigDigits++;
            }
        }
        result.numSigDigits = counterSigDigits;
        return result;
    }
    
        



    public static void main(String [] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();
        BigInt val1 = new BigInt(11111);
        BigInt val2 = new BigInt(23);
        BigInt product = val1.mul(val2);
        System.out.println(product);
        /* 
         * You should uncomment and run each test--one at a time--
         * after you build the corresponding methods of the class.
         */
        /*
        System.out.println("Test 1: result should be 7");
        int[] a1 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();
        
        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        */
    }
}
