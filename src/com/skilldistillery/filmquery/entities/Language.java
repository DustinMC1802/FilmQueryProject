package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Language {
	private int id;
	private String name;
	
	//constructors
	public Language() {
		super();
	}

	public Language(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	//getters and setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Language [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	//hascode and equals
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	
}


