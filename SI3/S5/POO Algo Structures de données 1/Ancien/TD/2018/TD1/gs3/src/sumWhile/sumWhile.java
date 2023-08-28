package sumWhile;

class SumWhile {
	public static void main(String [] args)
	{
		System.out.println(sumWhile(5));
	}

	public static int sumWhile(int n)
	{
		int sum=0;
		int stop = 0;
		while(stop<=n){
			sum+=stop;
			stop++;
		}
		return sum;
	}
}
