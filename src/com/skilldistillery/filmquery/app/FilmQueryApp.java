package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws InputMismatchException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

//  private void test() {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
////    Actor actor = db.findActorById(1);
////    System.out.println(actor);
////    List<Actor> actors = db.findActorsByFilmId(1);
////    for (Actor actor2 : actors) {
////    	System.out.println(actor2);
////	}
////    System.out.println(film.getCast());
//    for (Actor a : film.getCast()) {
//    	System.out.println(a.getFirstName() + " " + a.getLastName());
//	}
//  }

	private void launch() throws InputMismatchException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws InputMismatchException {
		int userInput = 0;

		startupMenu();
		userInput = Integer.parseInt(input.nextLine());
		while (userInput != 3) {
			try {

				if (userInput == 1) {
					filmIDSearch(input);
					startupMenu();
					userInput = input.nextInt();

				} else if (userInput == 2) {
					filmKeywordSearch(input);
					startupMenu();
					userInput = input.nextInt();

				} else {
					System.out.println("Invalid selection");
					startupMenu();
					userInput = input.nextInt();
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid selection");
				startupMenu();
				userInput = input.nextInt();
			}
		}
	}

	private void startupMenu() {
		System.out.println("Please select from the following options:");
		System.out.println("-----------------------------------------");
		System.out.println("Press 1 to look up a film by its ID");
		System.out.println("Press 2 to look up a film with a keyword search");
		System.out.println("Press 3 to exit");
	}

	private void filmIDSearch(Scanner input) {
		int userInput = 0;
		System.out.println("Please enter film ID: ");
		System.out.println("------------------------------------");
		userInput = input.nextInt();
		if (userInput >= 1 && userInput <= 1000) {
			Film film = db.findFilmById(userInput);
			System.out.println(film.getTitle());
			System.out.println(film.getReleaseYear());
			System.out.println(film.getRating());
			System.out.println(film.getDescription());
		} else {
			System.out.println("Film not found");
		}
		System.out.println("------------------------------------");
	}
	
	private void filmKeywordSearch(Scanner input) {
		String userInput;
		System.out.println("Please enter a film keyword: ");
		System.out.println("------------------------------------");
		userInput = input.nextLine();
		List<Film> films = db.findFilmByKeyword(userInput);
		for (Film film : films) {
			System.out.println("--------");
			System.out.println(film.getTitle());
			System.out.println(film.getReleaseYear());
			System.out.println(film.getRating());
			System.out.println(film.getDescription());
			System.out.println("--------");
		}
		
		System.out.println("------------------------------------");
////    List<Actor> actors = db.findActorsByFilmId(1);
////    for (Actor actor2 : actors) {
////    	System.out.println(actor2);
		
	}

}
