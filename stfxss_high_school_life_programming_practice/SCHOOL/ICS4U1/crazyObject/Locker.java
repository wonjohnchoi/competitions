package crazyObject;

// The "Locker" class.
public class Locker {
	int number;
	Student owner;
	private Jacket studentJacket;
	private Book books[];

	/**
	 * Default constructor
	 */
	public Locker(Student me) {
		owner=me;
		putABook(new Book());
		putABook(new Book());
		books[0].setCourse("ICS4M1");
		books[1].setCourse("IDC4U1");
	}
	public Book getABook(String course)
	{
		int pos=-1;
		for(int i=0;i<books.length;i++)
		{
			if(books[i].course==course)
			{
				pos=i;
			}
		}
		if(pos==-1)
		{
			return null;
		}
		return books[pos];
	}
	public void putABook(Book book)
	{
		if(books==null)
		{
			books=new Book[2];
		}
		if(books[0]==null)
		{
			books[0]=book;
		}
		else if(books[1]==null)
		{
			books[1]=book;
		}
		else
		{
			System.err.println("Require additional book space");
		}
	}

/*
	public Jacket getJacket() {


		return studentJacket;
	}*/

	public Jacket checkJacket() {

		return studentJacket;
	}

	public void putJacket(Jacket myJacket) {
		studentJacket=myJacket;
	}

	public void assignOwner(Student me) {
		owner=me;
	}

	public String toString() {
		return owner+" owns this locker";
	}

} // Locker class
