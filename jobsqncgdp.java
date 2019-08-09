package dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Time complexity is O (n^2) , n is no of Jobs 
// include job id it will make getsequence of jobs easy.
class Jobbs
{
	int start,end,profit;
    public Jobbs(int start, int end, int profit) {
		super();
		this.start = start;
		this.end = end;
		this.profit = profit;
	}
	
}
class sortend implements Comparator<Jobbs>
{
	public int compare(Jobbs j1 , Jobbs j2)
	{
		return j1.end -j2.end; // sort in ascending order of the deadline,
	}
}



public class jobsqncgdp {
	
		
public  static boolean checkconflict( ArrayList<Jobbs> arr , int i, int j)
	{
		if(arr.get(i).start < arr.get(j).end)
			return true;  // true here means jobs are overlapping. 
		return false;
	}
	
public  static int maxprofit(ArrayList<Jobbs> arr) {
		
		Collections.sort(arr, new sortend());
	
//		System.out.println(" sorted jobs according to finsihing time in ascending order");
//		for (int i = 0 ; i < arr.size() ; i++)
//		{
//			System.out.println(" " + i +"   " + arr.get(i).start + "  " + arr.get(i).end + "  " + arr.get( i).profit);
//		}
		int n = arr.size();
		int[] p = new int[n];
		
		
		
		for(int i = 0 ; i < n ; i++)
		{
			p[i] = arr.get(i).profit;
		}
		int maxprofit = Integer.MIN_VALUE; int box = 0;
		for ( int i = 1 ; i < n ; i++)
		{
			
				for( int  j = 0 ; j < i ; j++)
				{
					if(checkconflict(arr,i,j))
					{
						//when jobs overlap profit dont change
					}
					else
					{
						int temp = arr.get(i).profit + arr.get(j).profit;
						p[i] = Math.max(p[i],temp);
						
						if(p[i] > maxprofit)
						{
							maxprofit = p[i];
							box = i;
							
						}
					}
				}
				
		}  getseqofjobs(arr,box);
		return maxprofit;
	}


	public static void  getseqofjobs(ArrayList<Jobbs> arr , int box)
	{   //System.out.println("max profit occurs at index : " + box); 
		ArrayList<Integer> seq = new ArrayList<Integer>();
		//System.out.println("proft of jobs non conflicting with index : "+ box);
		for (int i = 0 ; i <= box ; i++)
		{
			if ( !checkconflict(arr, box, i))
			{
				//System.out.println("adding job with start , end and profit as  "+ arr.get(i).start +" " +  arr.get(i).end + " " + arr.get(i).profit );
				seq.add(i);  // if jobs don't conflict we add them in sequence
			}
		}seq.add(box);
		//System.out.println(Arrays.deepToString(seq.toArray()) );
		
		
		int n = seq.size() - 1;
		for(int i = 0 ; i < n-1 ; i++)  // n-1 as job at index box is definite to get added.
		{
			if(checkconflict(arr, seq.get(i), seq.get(i+1)) )
			{
			  // if jobs are not compatible we keep max profit 
				//System.out.println(" job are non compatible " +  i + "  " + (i+1));
				int p1 = arr.get(seq.get(i)).profit;
				int p2 = arr.get(seq.get(i+1)).profit;
				//System.out.println(" comparing :" + p1 + "< " + p2) ;
				if(p1>p2)
				{	
					seq.set(i+1, -1) ; // means not adding this job for final computation
				}else
				{
					seq.set(i, -1) ;
				}
			}
			else
			{   // if jobs are compatible we keep both
				
			}
		}
		
		//System.out.println("seq is " + Arrays.deepToString(seq.toArray()));
		//System.out.println(seq.size());
		System.out.println("Jobs in Sequence are: " + "\n");
		System.out.println("start    end     profit");
		for(int i = 0 ; i < seq.size() ; i++)
		{
			int x = seq.get(i);
			//System.out.println( " i = "+ i + " " + "  x is " +  x + " ");
			
			if( x != -1)
			{
			 System.out.println(arr.get(x).start + "         " +  arr.get(x).end + "        " + arr.get(x).profit);	
			}
		}
		
		System.out.println("\n ");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Jobbs> arr = new ArrayList<Jobbs>();
		arr.add(new Jobbs(1,4,3));
		arr.add(new Jobbs(2,6,5));
		arr.add(new Jobbs(7,10,8));
		arr.add(new Jobbs(4,7,2));
		arr.add(new Jobbs(6,8,6));
		arr.add(new Jobbs(5,9,4));
		
		
		ArrayList<Jobbs> arr2 = new ArrayList<Jobbs>();
		arr2.add(new Jobbs(3,5,400));
		arr2.add(new Jobbs(5,6,20));
		arr2.add(new Jobbs(4,7,50));
		
		
		int result = maxprofit(arr);
		System.out.println("MAX PROFIT for arr :" + result);
		
		
		int result2 = maxprofit(arr2);
		System.out.println("max profit for arr2 : "+ result2);
		

	}
		
}
