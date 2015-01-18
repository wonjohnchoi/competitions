package LinkedList;


public class TestLinkedList
{
	public static void main(String args[])
	{	
		System.out.println("Test starting...");
		
		System.out.println();

		LinkedList<Integer> list = new LinkedList<Integer>();
		
		for(int i=0;i<5;i++)
		{
			int newData=(int)(Math.random() * 1000);
			list.add_Front(newData);
			System.out.println("Added at front: " + newData);
		}
		
		System.out.println();
		
		for(int i=0;i<5;i++){
			int newData = (int)(Math.random() * 1000);
			list.add_End(newData);
			System.out.println("Added at end: " + newData);
		}
		
		System.out.println();
		
		int newData=(int)(Math.random() * 1000);
		list.add(newData, 0);
		list.add(newData, 5);
		list.add(newData, 10);
		System.out.println("Added at "+0+","+ 5+","+ 10+": " + newData);
		
		System.out.println();

		for(int i=0;i<list.size();i++)
		{
			System.out.println("Peeked "+i+": "+list.peek(i));
		}
		
		System.out.println();

		for(int i=0;i<5;i++)
		{
			System.out.println("Removed at front: " + list.remove_Front());
		}
		
		System.out.println();

		while(!list.isEmpty())
		{
			System.out.println("Remove at end: " + list.remove_End());
		}
		
		System.out.println();
	
		System.out.println("Test ending...");
		

	}
}
