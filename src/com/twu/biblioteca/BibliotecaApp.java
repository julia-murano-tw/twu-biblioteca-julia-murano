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

        for (Book book : this.BooksList){
            if (!book.isCheckedOut()){
                bookInfo = book.getTitle().concat(" - " + book.getAuthor()  +  " - " + book.getPublicationYear());
                System.out.println(bookInfo);
            }


        }
    }

    public void showMainMenu() {
        boolean exitOption = false;

        Scanner menuScanner = new Scanner(System.in);
        while (!exitOption){
            System.out.println("\nChoose an option: \n   1 - List of books\n   2 - Check out Book\n\n   0 - exit");
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
}
