package crazyObject;

//Decompiled by DJ v3.11.11.95 Copyright 2009 Atanas Neshkov  Date: 2010-04-02 ¿ÀÀü 9:03:49
//Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   ClassRoom.java


public class ClassRoom
{

 public ClassRoom(String thisCourse, String thisTeacher)
 {
     course = thisCourse;
     teacher = thisTeacher;
 }

 public boolean enterClass(Student me)
 {
     boolean result = false;
     System.out.println((new StringBuilder()).append("Student ").append(me).append(" came to class ").append(course).append(" taught by ").append(teacher).toString());
     if(me.myLocker == null)
         me.sentToOffice("Get a locker");
     else
     if(me.myLocker.owner != me)
     {
         me.sentToOffice("Get your own locker");
         if(me.myJacket != null)
             me.sentToOffice("Put your jacket in your locker");
     } else
     {
         Jacket studentJacket = me.myLocker.checkJacket();
         if(studentJacket == null || studentJacket.owner != me)
             me.sentToOffice("Where is YOUR jacket?  Put it in your locker");
         else
         if(teacher.equals("Mr. Strict"))
         {
             Book books[] = me.books;
             if(books == null || books[0] == null)
                 me.sentToOffice("Where is your textbook?");
             else
             if(books.length > 2)
             {
                 me.sentToOffice("You should bring at most two books to class");
             } else
             {
                 boolean foundBook = false;
                 for(int i = 0; i < books.length; i++)
                     if(books[i] != null && books[i].course.equalsIgnoreCase(course))
                         foundBook = true;

                 if(!foundBook)
                     me.sentToOffice("Did not bring the correct textbook");
                 else
                     result = true;
             }
         } else
         {
             result = true;
         }
     }
     return result;
 }

 String course;
 String teacher;
}

