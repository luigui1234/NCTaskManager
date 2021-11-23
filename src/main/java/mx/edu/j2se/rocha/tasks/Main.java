package mx.edu.j2se.rocha.tasks;

import mx.edu.j2se.rocha.tasks.Task;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Hola mundo");
		System.out.println("Ahhh perrooo");
		Task nonRepetitive = new Task("loquesea", 18);
		Task repetitive = new Task("loquesea", 2, 220, 24);
		System.out.println(nonRepetitive.getTime());
		System.out.println(nonRepetitive.getTitle());
		System.out.println(nonRepetitive.isRepeated());
		System.out.println(nonRepetitive.isActive());
		System.out.println(repetitive.getStartTime());
		System.out.println(repetitive.getEndTime());
		System.out.println(repetitive.getRepeatInterval());
		System.out.println(repetitive.getTitle());
		System.out.println(repetitive.isRepeated());
		System.out.println(repetitive.isActive());

		repetitive.setActive(true);
		nonRepetitive.setActive(true);

		System.out.println(repetitive.isActive());
		System.out.println(nonRepetitive.isActive());

		System.out.println(repetitive.nextTimeAfter(26));
		System.out.println(nonRepetitive.nextTimeAfter(10));


		ArrayTaskList tasks = new ArrayTaskList();
		Task nonRepetitive1 = new Task("loquesea", 18);

		tasks.add(repetitive);
		tasks.add(nonRepetitive);
		tasks.add(nonRepetitive1);
		System.out.println(tasks.size());
		System.out.println(tasks.remove(repetitive));
		System.out.println(tasks.size());
	}
	
}
