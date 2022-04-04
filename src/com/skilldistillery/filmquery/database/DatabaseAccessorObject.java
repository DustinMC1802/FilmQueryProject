package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Driver not found");
			throw new RuntimeException("Unable to load MySQL driver class");
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt = "SELECT * FROM film WHERE id = ?";

			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageID(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setCast(findActorsByFilmId(filmId));

			}
			rs.close();
			ps.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
	
	@Override
	public List<Film> findFilmByKeyword(String filmKeyword) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";
			
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, "%" + filmKeyword + "%");
			ps.setString(2, "%" + filmKeyword + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageID(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				films.add(film);
				
			}
			rs.close();
			ps.close();
			conn.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}


	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt = "SELECT * FROM actor WHERE id = ?";

			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, actorId);
			ResultSet rs = ps.executeQuery();
			actor = new Actor();
			if (rs.next()) {
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));

			}
			rs.close();
			ps.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}
	
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt = "SELECT a.* FROM actor a"
									+ " JOIN film_actor fa ON a.id = fa.actor_id"
									+ " WHERE fa.film_id = ?";
//			debug sysout
//			System.out.println(sqltxt);
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);
			}
			rs.close();
			ps.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		// Instantiate new Actor for each rs row
		// add to actors List
		return actors;
	}
	@Override
	public Language findLanguageByFilmId(int filmId) {
		Language language = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt = "SELECT l.* FROM language l"
					+ " JOIN film f ON l.id = f.language_id"
					+ " WHERE l.id = ?";
//			debug sysout
//			System.out.println(sqltxt);
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				language = new Language();
				language.setId(rs.getInt("id"));
				language.setName(rs.getString("name"));
			}
			rs.close();
			ps.close();
			conn.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}

}
