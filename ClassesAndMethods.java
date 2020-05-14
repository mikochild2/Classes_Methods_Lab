import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClassesAndMethods {
    public static void main(String args[]){
        Bookstore torBooks = new Bookstore("Tor Books", true);

        torBooks.address = "222 S. Elm St., Greensboro, NC 27401";
        torBooks.squareFeet = 1000;
        torBooks.storeOpen = true;
        torBooks.closeTime = "Never. We're open 24 hours!";

        torBooks.retrieveStoreName();
        torBooks.retrieveAddress();
        torBooks.retrieveUsed();
        torBooks.retrieveSize();
        torBooks.retrieveOpen();
        torBooks.retrieveClose();
        torBooks.findBooks("Programming in Java");
        torBooks.countBooks();
        torBooks.findWord("Learning");

    }

    

    

}