package com.twu.biblioteca;
import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


public class BibliotecaApp {
    private ArrayList<Book> BooksList = new ArrayList<>();


    public static void main(String[] args) {

        BibliotecaApp Biblioteca = new BibliotecaApp();
        Biblioteca.showWelcomeMessage();
        Biblioteca.showMainMenu();
    }

    public BibliotecaApp(){
        this.BooksList.add(new Book("Generic Book", "John Doe", 1997));
        this.BooksList.add(new Book("Interesting Book", "Cool Author",  1890));
        this.BooksList.add(new Book("Boring Book", "Boring Guy", 2020));

    }


    public void showWelcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void showAllBooks() {
        String bookInfo = "";
        System.out.println("--------\nList Of Available Books\n\n");
        for (Book book : this.BooksList){
            if (!book.isCheckedOut()){
                bookInfo = book.getTitle().concat(" - " + book.getAuthor()  +  " - " + book.getPublicationYear());
                System.out.println(bookInfo);
            }


        }
        System.out.println("--------\n") ;
    }

    public void showMainMenu() {
        boolean exitOption = false;

        Scanner menuScanner = new Scanner(System.in);
        while (!exitOption){
            System.out.println("\n--------\nMENU\n\nChoose an option: \n   1 - List of books\n   2 - Check out Book\n   3 - Return book\n\n   0 - exit\n--------\n");
            String userInput = menuScanner.nextLine();
            switch (userInput) {
                case "1":
                    this.showAllBooks();
                    break;
                case "2":
                    System.out.println("Please type the title of the book you want to check out");
                    String bookNameCheckout = menuScanner.nextLine();
                    this.checkOut(bookNameCheckout);
                    break;
                case "3":
                    System.out.println("Please type title of Book to return");
                    String bookNameReturn = menuScanner.nextLine();
                    this.returnBook(bookNameReturn);
                    break;
                case "0":
                    exitOption = true;
                    break;
                default:
                    System.out.println("Please select a valid option!");
                    break;
            }
        }

    }

    public void checkOut(String title) {
        for (Book book: this.BooksList){
            if (book.getTitle().equals(title)){
                book.checkOut();
                System.out.println("Thank you! Enjoy the book");
                return;
            }
        }
        System.out.println("Sorry, that book is not available");
    }

    public void returnBook(String title) {
        for (Book book: this.BooksList){
            if(book.getTitle().equals(title) && book.isCheckedOut() ){
                book.returnBook();
                System.out.println("Thank you for returning the book");
                return;
            }
        }
        System.out.println("That is not a valid book to return.");

    }
}
