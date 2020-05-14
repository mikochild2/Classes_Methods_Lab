import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Bookstore {
    String storeName;
    String address;
    int squareFeet;
    Boolean usedBooks;
    Boolean storeOpen;
    String closeTime;
    private ArrayList<String> titles;

    public Bookstore(String storeName, Boolean storeOpen){
        this.storeName = storeName;
        this.storeOpen = storeOpen;

        titles = new ArrayList<String>();
        loadTitles();
    }




    //Method to retrieve store name

    void retrieveStoreName() {
        System.out.println("Store name: " + storeName);
    }
    
    //Method to retrieve store address
    void retrieveAddress() {
        System.out.println("Address: " + address);
    }

    //Method to retrieve store size
    void retrieveSize(){
        System.out.println("Store size (sqft): " + squareFeet);
    }

    //Method to retrieve used book status
    void retrieveUsed(){
        System.out.println("This store offers used books: " + usedBooks);
    }

    //Method to retrieve store open status
    void retrieveOpen(){
        System.out.println("This store is currently open: " + storeOpen);
    }

    //Method to retrieve store close time
    void retrieveClose(){
        System.out.println("Store close time: " + closeTime);
    }

    //Method to set store name
    public void setStoreName(String storeName){
        this.storeName = storeName;
    }

    //Method to get the store name
    public String getStoreName(){
        return this.storeName;
    }

    //Find Books
    public void findBooks(String book){
        //loadTitles();
        if(titles.contains(book)){
            System.out.printf("\nYes, we carry \'%s\'.", book);
        } else {
            System.out.printf("\nNo, we do not carry \'%s\'.", book);
        }
    }

    //Count books
    public void countBooks(){
        System.out.println("\nTor Books offers a selection of " + titles.size()+" books.");
    }

    //Do any of the titles contain this word?
    public void findWord(String word){
        System.out.printf("\nYes, we have a title that contains \'%s\'.", word);
    }

    //Book titles

    private void loadTitles()
    {
      try
      {
        Utils.loadStringsToArray(this.titles);
      }
      catch (IOException e)
      {
        // for now simply init the array to zero
        System.out.println("Could not initilize the titles");
        // make sure it is empty
        this.titles = new ArrayList<String>();
        
      }
    }    
}