package edu.uark.spARK.entities;

import java.util.*;

import android.location.Location;

@SuppressWarnings("serial")
public class Group extends Content {
	
	private List<User> users;
	
	public Group(int id, String title, String description) {
		super(id, title, description);
		this.users = new ArrayList<User>();
	}
	
	public Group(int id, String title, String description, User creator) {
		super(id, title, description, creator);
		this.users = new ArrayList<User>();
		users.add(creator);
	}
	
	public Group(int id, String title, String description, User creator, Location location) {
		super(id, title, description, creator, location);
		this.users = new ArrayList<User>();
		users.add(creator);
	}
	
	public Group(int id, String title, String description, User creator, Location location, List<User> users) {
		super(id, title, description, creator, location);
		this.users = users;
	}
	
	public Group(int id, String title, String description, User creator, Date creationDate) {
		super(id, title, description, creator, creationDate);
		this.users = new ArrayList<User>();
		users.add(creator);
	}
	
	public Group(int id, String title, String description, User creator, Date creationDate, List<User> users) {
		super(id, title, description, creator, creationDate);
		this.users = users;
	}
	
	public Group(int id, String title, String description, User creator, Date creationDate, Location location) {
		super(id, title, description, creator, creationDate, location);
		this.users = new ArrayList<User>();
		users.add(creator);
	}
	
	public Group(int id, String title, String description, User creator, Date creationDate, Location location, List<User> users) {
		super(id, title, description, creator, creationDate, location);
		this.users = users;
	}
	
	public String getDescription() {
		return getText();
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setDescription(String description) {
		setText(description);
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public boolean addUser(User user) {
		return users.add(user);
	}
	
	public boolean removeUser(User user) {
		return users.remove(user);
	}
	
}
