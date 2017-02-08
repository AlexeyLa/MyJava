
public class Perms {
	
static int counterPerm = 0;
static int [][] totalPerm = new int [720][6];

void printArray(int [] a) {
	for (int i = 0; i < a.length; i++) {
		System.out.print(a[i]+" ");
		}
		System.out.println("");
		}

void permute(int []a, int k) 
	{
	//	System.out.println(counterPerm);
		if (k==a.length){
			for (int j=0; j<a.length;j++) {
				totalPerm[counterPerm][j] = a[j];
//				System.out.println(a[j]);
			}
			counterPerm++;
//			System.out.println(counterPerm);
//			printArray(a);
			}
		else
			for (int i = k; i < a.length; i++) 
			 {
				int temp=a[k];
				a[k]=a[i];
				a[i]=temp;
				permute(a,k+1);
				temp=a[k];
				a[k]=a[i];
				a[i]=temp;
			 }
		}

public static void main(String[] args) 
{
	Perms p=new Perms();
	int [] vz;
	int a[]={1,2,3,4,5,6};
	p.permute(a, 0);
	
	for (int i=0; i<totalPerm.length; i++){
			System.out.println();
		for (int j=0; j<totalPerm[0].length; j++){
			System.out.print(totalPerm[i][j]);
		}
	}
}
}
