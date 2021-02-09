package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BibliotecaTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private  BibliotecaApp biblioteca;

    @Before
    public void SetUp(){
        biblioteca = new BibliotecaApp();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void ShouldPrintWelcomeMessage() {


        biblioteca.showWelcomeMessage();
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!", outputStreamCaptor.toString().trim());

    }

    @After
    public void tearDown(){
        System.setOut(standardOut);
    }

    @Test
    public void ShouldShowAllBooksTitles(){
        biblioteca.showAllBooks();
        assertThat(outputStreamCaptor.toString(), containsString("Generic Book"));
        assertThat(outputStreamCaptor.toString(), containsString("Interesting Book"));
        assertThat(outputStreamCaptor.toString(), containsString("Boring Book"));
    }

    @Test
    public void ShouldShowBooksTitlesPublicationYearAndAuthor(){
        biblioteca.showAllBooks();
        assertThat(outputStreamCaptor.toString(), containsString("Generic Book - John Doe - 1997"));
        assertThat(outputStreamCaptor.toString(), containsString("Interesting Book - Cool Author - 1890"));
        assertThat(outputStreamCaptor.toString(), containsString("Boring Book - Boring Guy - 2020"));
    }

    @Test
    public void ShouldShowMainMenuAndListBooks(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream inOne = new ByteArrayInputStream("1\n0".getBytes());

        System.setIn(inOne);

        biblioteca.showMainMenu();


        assertThat(outputStreamCaptor.toString(), containsString("1 - List of books"));
        assertThat(outputStreamCaptor.toString(), containsString("Generic Book - John Doe - 1997"));
        assertThat(outputStreamCaptor.toString(), containsString("Interesting Book - Cool Author - 1890"));
        assertThat(outputStreamCaptor.toString(), containsString("Boring Book - Boring Guy - 2020"));
        System.setIn(sysInBackup);
    }
    @Test
    public void ShouldShowMainMenuInvalidOption(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("A\n0".getBytes());
        System.setIn(in);

        biblioteca.showMainMenu();

        assertThat(outputStreamCaptor.toString(), containsString("1 - List of books"));
        assertThat(outputStreamCaptor.toString(), containsString("Please select a valid option!"));

        System.setIn(sysInBackup);
    }

    @Test
    public void ShouldShowMainMenuAndQuit(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("A\nD\nO\n1\n0".getBytes());
        System.setIn(in);

        biblioteca.showMainMenu();

        assertThat(outputStreamCaptor.toString(), containsString("1 - List of books"));
        assertThat(outputStreamCaptor.toString(), containsString("Please select a valid option!"));

        System.setIn(sysInBackup);
    }

    @Test
    public void ShouldCheckOutBook(){
        BibliotecaApp tempBiblioteca = new BibliotecaApp();
        tempBiblioteca.checkOut("Boring Book");
        tempBiblioteca.showAllBooks();
        assertThat(outputStreamCaptor.toString(), not(containsString("Boring Book")));

    }

    @Test
    public void ShouldCheckOutBookSuccessMessage(){
        BibliotecaApp tempBiblioteca = new BibliotecaApp();
        tempBiblioteca.checkOut("Boring Book");
        assertThat(outputStreamCaptor.toString(), containsString("Thank you! Enjoy the book”"));

    }
}
