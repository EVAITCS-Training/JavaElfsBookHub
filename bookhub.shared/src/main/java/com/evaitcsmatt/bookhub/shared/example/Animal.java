package com.evaitcsmatt.bookhub.shared.example;

public abstract class Animal {
	private String name;
	private char gender;
	private float height;
	private float weight;
	
	public Animal(String name, char gender, float height, float weight) {
		this.name = name;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
	}
	
	public abstract void speak();
	public abstract void eat();
	
	public void displayAnimal() {
		String blockString = """
				Animal Information:
				Name: %s
				Gender: %s
				Height: %f
				Weight: %f
				""";
		
		System.out.println(String.format(blockString, name, gender, height, weight));
	}

}
