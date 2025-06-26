import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Book{

	int id;
	String title;
	boolean isIssued;

	Book(int id, String title){
		this.id = id;
		this.title = title;
		this.isIssued = false;
	}

	public void display(){
		System.out.println("ID : " + id + " | Title : " + title + " | Status : " + (isIssued ? "Issued" : "Available"));
	}
}

class User{
	
	int userId;
	String name;
	
	User(int userId, String name){
		this.userId = userId;
		this.name = name;
	}
}

class Library{
	
	ArrayList<Book> books = new ArrayList<>();
	Map<Integer , Integer> issuedBooks = new HashMap<>();

	public void addBook(Book book){
		books.add(book);
		System.out.println("\nBook added : " + book.title);
	}

	public void viewBooks(){

		if(books.isEmpty()){
			System.out.println("No books available.");
			return;
		}
		System.out.println("\n--- Book List ---");
		for(Book b : books){
			b.display();
		}
	}

	public void issueBook(int bookId, int userId){
        	for(Book b : books){
			if(b.id == bookId && !b.isIssued){
				b.isIssued = true;
				issuedBooks.put(bookId, userId);
				System.out.println("\nBook issued successfully to User ID : " + userId);
				return;
			}
		}
		System.out.println("\nBook not available or already issued.");
	}

	public void returnBook(int bookId, int userId) {
        if(issuedBooks.containsKey(bookId) && issuedBooks.get(bookId) == userId) {
            for(Book b : books){
                if(b.id == bookId){
			b.isIssued = false;
                    	issuedBooks.remove(bookId);
                    	System.out.println("\nBook returned successfully.");
                    	return;
               	}
            }
        } 
	else{
		System.out.println("\nInvalid return attempt. Book was not issued to this user.");
        }
    }	
}

public class LibraryManagementSystem{
	
	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		Library library = new Library();

		System.out.println("\n*** Library Management System ***");

		int choice;
		do{
			System.out.println("\n1. Add Book");
			System.out.println("2. View Books");
			System.out.println("3. Issue Book");
			System.out.println("4. Return Book");
			System.out.println("5. Exit");
				
			System.out.print("\nEnter your choice : ");
			choice = sc.nextInt();
		
			switch(choice){
				case 1: 
					System.out.print("Enter Book ID: ");
                    			int id = sc.nextInt();

                    			sc.nextLine();
                    			System.out.print("Enter Book Title: ");
                    			String title = sc.nextLine();

                    			Book book = new Book(id, title);
                    			library.addBook(book);
                   		 	break;
			
				case 2: 	
					library.viewBooks();
					break;
		
				case 3:
					System.out.print("Enter Book ID to issue: ");
                    			int bookId = sc.nextInt();

                    			System.out.print("Enter User ID: ");
                    			int userId = sc.nextInt();

                    			library.issueBook(bookId, userId);
                    			break;
			
				case 4:
					System.out.print("Enter Book ID to return: ");
                    			int returnId = sc.nextInt();

                   	 		System.out.print("Enter Your User ID: ");
                    			int returnUser = sc.nextInt();

                    			library.returnBook(returnId, returnUser);
                    			break;
	
        		        case 5:
                    			System.out.println("Exiting Library System...");
                    			break;

                		default:
                    			System.out.println("Invalid choice.");	
			}
		}while(choice != 5);
		sc.close();
	}
}

