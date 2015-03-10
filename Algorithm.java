import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;


public class Algorithm 
{	
	static long minimum = 1000;//set random number minimum size
	static long maximum = 9999;//set random number max size
	static long temp;//temp value for testing prime
	static long gen_num;//stores random generated value
	static boolean answer;//stores prime test result
	static boolean gen_prime;//stores result of test prime
	Random rnd = new Random();//for coprime
	
	public static long ran_gen()//method to generate random number and returns if prime
	{
        gen_num = ((long) (Math.random()*(maximum - minimum))) + minimum;//generate number between min and max values
        gen_prime = test_prime(gen_num);//call test prime to see if its prime
        while(gen_prime == false)//loop to test for prime re-generate if not
        {
        	gen_num = ((long) (Math.random()*(maximum - minimum))) + minimum;//generate number between min and max values
        	gen_prime = test_prime(gen_num);//call test prime to see if its prime
        }
        
        return gen_num;//return prime number
	}

	public static boolean test_prime(long num1)//method to test if a number is prime
	{
		temp = num1/2;//temp value is half of number passed through parameters
		
		while(temp > 2)//loop while temp divisor is greater than 2
		{
			temp--;//decrement divisor temp
			if(num1 % temp == 0)//check if number modulus is 0
			{
				answer = false;//set bool value to false
				break;//break from loop
			}
			else
			{
				answer = true;//set bool value to true
			}
		}
		return answer;//return bool value
	}
	
	public static long product(long p_1, long p_2)//method to get product of two longs
	{
		long prod = p_1*p_2;//multiply numbers passed to get product
		return prod;//return value
	}
	
	public BigInteger generate_E()
	{
		BigInteger e;
		e = BigInteger.probablePrime(1000 / 2, rnd);
		
		return e;
	}
	
	public BigInteger RSA(long p, long q, BigInteger e)//method to calculate value of d to work out part of private key
	{
		long t = (q-1)*(p-1);//totient is equal to p-1 multiplied by q-1
		BigInteger mod = BigInteger.valueOf(t);//parse totient to BigInteger
		BigInteger d;
		
		 while (mod.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(mod) < 0)//euclidian algorithm loop
	        {
	            e.add(BigInteger.ONE);//add 1 to e
	        }
	        d = e.modInverse(mod);//d is equal to modular inverse of e mod mod
		return e;//return value
	}
	
	public static String bytesToString(byte[] encrypted)//method to parse byte array to string
    {
        String test = "";//empty string
        for (byte b : encrypted)//loop for every element in array
        {
            test += Byte.toString(b);//add element to string
        }
        return test;//return string
    }
	
	public byte[] encrypt(byte[] message, BigInteger e, long nn)//encrypt message
    {
		BigInteger n = BigInteger.valueOf(nn);//parse long to biginteger
        return (new BigInteger(message)).modPow(e,n).toByteArray();//return byte array value of exponent e mod n or public key
    }
	 
    public byte[] decrypt(byte[] message, BigInteger d, long nn)//decrypt message
    {
    	BigInteger n = BigInteger.valueOf(nn);//parse long to biginteger
        return (new BigInteger(message)).modPow(d,n).toByteArray();//return byte array value of exponent d mod n or private key
    }


}