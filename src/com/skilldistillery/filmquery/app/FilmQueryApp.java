package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
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
//  }

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		int userInput = 0;

		startupMenu();
		userInput = input.nextInt();
		while (userInput != 3) {

			if (userInput == 1) {
				filmIDSearch(input);
				startupMenu();
				userInput = input.nextInt();

			} else if (userInput == 2) {
				filmKeywordSearch(input);
				startupMenu();
				userInput = input.nextInt();

			} else if (userInput < 1 && userInput > 3) {
				System.out.println("Invalid selection");
				startupMenu();
				userInput = input.nextInt();
			}
		}
		System.out.println("Goodbye");
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
			displayLanguage(film);
			displayCast(film);
		} else {
			System.out.println("Film not found");
		}
		System.out.println("------------------------------------");
	}

	private void filmKeywordSearch(Scanner input) {
		int userInput = 0;
		System.out.println("Please enter a film keyword: ");
		System.out.println("------------------------------------");
		String filmKeyword = input.next();
		List<Film> films = db.findFilmByKeyword(filmKeyword);
		try {
			if (! films.isEmpty()) {
				for (Film film : films) {
					System.out.println("--------");
					System.out.println(film.getTitle());
					System.out.println(film.getReleaseYear());
					System.out.println(film.getRating());
					System.out.println(film.getDescription());
					System.out.println("Film ID: " + film.getId());
					displayLanguage(film);
//					displayCast(film);
					System.out.println("--------");
				}
			} else {
				System.out.println("--------");
				System.out.println("No films found with keyword: " + filmKeyword);
				System.out.println("--------");
				
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		System.out.println("------------------------------------");
	}

	private void displayLanguage(Film film) {
		int filmId = film.getLanguageID();
		Language language = db.findLanguageByFilmId(filmId);
		System.out.println("In language: " + language.getName() + " " + language.getId());	
	}
	
	private void displayCast(Film film) {
		System.out.println("Cast:");
		for (Actor a : film.getCast()) {
			System.out.println(a.getFirstName() + " " + a.getLastName());
		}
		
	}
}
