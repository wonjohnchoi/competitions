package crazyObject;

// The "Student" class.
public class Student
{
    String number;
    String name;
    Locker myLocker;
    Jacket myJacket;
    Book books[];
    
    /**
     * Constructor
     * @param String myName - name of the student
     */
    public Student(String myName)
    {
      name = myName;
      myJacket=new Jacket(this);
      myLocker=new Locker(this);
      myLocker.putJacket(myJacket);
      books=new Book[2];
      books[0]=myLocker.getABook("ICS4M1");
      books[1]=myLocker.getABook("IDC4U1");
      
      
    }

    /**
     * This method gets called when the student is sent to the office
     */
    public void sentToOffice(String reason)
    {
      System.out.println("I was sent to office because: "+reason);
    }

    /** 
     * Display student information
     */
     public String toString()
     {
       return name;
     }
    
} // Student class
