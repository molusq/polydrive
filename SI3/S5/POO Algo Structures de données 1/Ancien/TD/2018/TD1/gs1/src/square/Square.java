package square;

class Square{
	public static void main (String[] args){
		System.out.println(sumOfSquares(new int[]{1,2,3,4}));
		System.out.println(sumOfSquares());
		System.out.println(sumOfSquares(1,2,3,4));

	}

	public static int sumOfSquares(int... data)
		{
			int sum = 0;
			if(data.length==0)
				return 0;
			else {
				for(int i=0;i<data.length;i++)
				{
					sum += data[i]*data[i];
				}
			}
			return sum;
		}
}