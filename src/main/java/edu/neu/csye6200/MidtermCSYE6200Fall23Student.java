package edu.neu.csye6200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;



/**
 * class MidtermCSYE6200Fall23 (STUDENT PARTIALLY CODED VERSION)
 * 
 * 
 * STUDENT TODO: Complete the implementation of this class as follows (MUST
 * DOCUMENT WORK WITH DETAILED DEV_LOG Strings):
 * 
 * Create a generic object model inner class 20 POINTS class Model INCLUDING
 * method to add object to model method to sort all objects in model by supplied
 * Comparator method toString() to return String representation model state,
 * i.e. return toString() on all objects
 * 
 * 10 POINTS code inner Person class implementing PersonAPI code inner Student
 * class derived from Person implementing StudentAPI code inner Employee class
 * derived from Person implementing EmployeeAPI
 * 
 * 10 POINTS code inner Item class implementing ItemAPI code inner Bread class
 * derived from Item code inner Oj class derived from Item code inner Milk class
 * derived from Item
 * 
 * 15 Points complete code for demoIntegerModel 15 Points complete code for
 * demoStringModel
 * 
 * 15 Points complete code for demoPersonModel 15 Points complete code for
 * demoItemModel
 * 
 */
public class MidtermCSYE6200Fall23Student {
	public static final int MAJOR = 2;
	public static final int MINOR = 4;
	public static final String[] DEV_LOG = { "initial revision", "* END OF DETAILED DEV LOG Strings DO NOT DELETE *" };
	public static final String VERSION = MAJOR + "." + MINOR + DEV_LOG.length;

	/**
	 * // STUDENT TODO:
	 * 
	 * code class Model using generic programming
	 */
	private static class Model<T> { // STUDENT TODO:

		private String name = Object.class.getSimpleName();
		List<T> list = new ArrayList<T>();

		public Model() {

		}

		public Model(String name) {
			this.name = name;
		}

		public void add(T data) {
			list.add(data);
		}

		public void sort(T sort) {

		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();

			sb.append("Total number of ").append(name).append(" objects in model: ").append(list.size()).append("\n");
			for (T o : list) {
				sb.append(o).append("\n");
			}

			return sb.toString();
		}
	}

	/**
	 * GIVEN object APIs
	 */
	private interface PersonAPI {
		int getId();

		void setId(int id);

		int getAge();

		void setAge(int age);

		String getName();

		void setName(String name);
	}

	private interface StudentAPI extends PersonAPI {
		double getGpa();

		void setGpa(double gpa);
	}

	private interface EmployeeAPI extends PersonAPI {
		double getWage();

		void setWage(double wage);
	}

	private interface ItemAPI {
		int getId();

		void setId(int id);

		double getPrice();

		void setPrice(double price);

		String getName();

		void setName(String name);
	}

	/**
	 * // STUDENT TODO: object Factory APIs and Factory classes
	 */
	private interface PersonFactoryAPI {

		public PersonAPI getObject(String line, PersonFactoryEnumSingleton criteria);
	}

	private interface ItemFactoryAPI {
		// STUDENT TODO:
		public ItemAPI getObject(String line, PersonFactoryEnumSingleton criteria);
	}

	private enum PersonFactoryEnumSingleton {
		PERSON, EMPLOYEE, STUDENT,MILK,OJ,BREAD
	}

	private static class PersonFactoryEagerSingleton implements PersonFactoryAPI {
		PersonAPI person;

		@Override
		public PersonAPI getObject(String line, PersonFactoryEnumSingleton criteria) {
			
			if(criteria.equals("EMPLOYEE")) {
				
				person = new Employee();
				String[] array = line.split(",");
				person.setId(Integer.parseInt(array[0]));
				person.setName(array[1]);
				person.setAge(Integer.parseInt(array[2]));
				((EmployeeAPI) person).setWage(Double.parseDouble(array[3]));
				return person;
					
			}else {
				person = new Student();
				String[] array = line.split(",");
				person.setId(Integer.parseInt(array[0]));
				person.setName(array[1]);
				person.setAge(Integer.parseInt(array[2]));
				((StudentAPI) person).setGpa(Double.parseDouble(array[3]));
				return person;
			}
			
		}

	}

	private static class StudentFactoryEagerSingleton implements PersonFactoryAPI {
		PersonAPI person = new Student();

		@Override
		public PersonAPI getObject(String line, PersonFactoryEnumSingleton criteria) {

			String[] array = line.split(",");
			person.setId(Integer.parseInt(array[0]));
			person.setName(array[1]);
			person.setAge(Integer.parseInt(array[2]));
			((StudentAPI) person).setGpa(Double.parseDouble(array[3]));
			return person;

		}

	}

	private static class EmployeeFactoryEagerSingleton implements PersonFactoryAPI {
		// STUDENT TODO:
		PersonAPI person = new Employee();

		@Override
		public PersonAPI getObject(String line, PersonFactoryEnumSingleton criteria) {

			String[] array = line.split(",");
			person.setId(Integer.parseInt(array[0]));
			person.setName(array[1]);
			person.setAge(Integer.parseInt(array[2]));
			((EmployeeAPI) person).setWage(Double.parseDouble(array[3]));
			return person;
		}

	}

	private static class EmployeeFactoryLazySingleton implements PersonFactoryAPI {
		PersonAPI person = null;

		@Override
		public PersonAPI getObject(String line, PersonFactoryEnumSingleton criteria) {

			if (person == null) {
				person = new Employee();
				String[] array = line.split(",");
				person.setId(Integer.parseInt(array[0]));
				person.setName(array[1]);
				person.setAge(Integer.parseInt(array[2]));
				((EmployeeAPI) person).setWage(Double.parseDouble(array[3]));
				return person;
			}

			else
				return person;
		}

	}

	private static class ItemFactoryEagerSingleton implements ItemFactoryAPI {
		// STUDENT TODO:
		ItemAPI item = null;
		
		@Override
		public ItemAPI getObject(String line, PersonFactoryEnumSingleton criteria) {
			
			if(criteria.equals(PersonFactoryEnumSingleton.BREAD)){
				
				item = new Bread();
				String[] array = line.split(",");
				item.setId(Integer.parseInt(array[0]));
				item.setName(array[1]);
				item.setPrice(Double.parseDouble(array[2]));
				
					
			}else if(criteria.equals(PersonFactoryEnumSingleton.MILK)){
				
				item = new Milk();
				String[] array = line.split(",");
				item.setId(Integer.parseInt(array[0]));
				item.setName(array[1]);
				item.setPrice(Double.parseDouble(array[2]));
				
			}else if(criteria.equals(PersonFactoryEnumSingleton.OJ)){
				
				item = new Oj();
				String[] array = line.split(",");
				item.setId(Integer.parseInt(array[0]));
				item.setName(array[1]);
				item.setPrice(Double.parseDouble(array[2]));
				
			}  
			return item;
		}
		
	}

	private static class BreadFactoryLazySingleton implements ItemFactoryAPI {
		// STUDENT TODO:
		ItemAPI item= null;
		

		@Override
		public ItemAPI getObject(String line, PersonFactoryEnumSingleton criteria) {
			// TODO Auto-generated method stub
			
			if(item == null) {
				item = new Bread();
				String[] array = line.split(",");
				item.setId(Integer.parseInt(array[0]));
				item.setName(array[1]);
				item.setPrice(Double.parseDouble(array[2]));
				return item;
			}
			return item;
		}
	}

	private static class OjFactoryEagerSingleton implements ItemFactoryAPI {
		ItemAPI item = new Oj();

		@Override
		public ItemAPI getObject(String line, PersonFactoryEnumSingleton criteria) {

			String[] array = line.split(",");
			item.setId(Integer.parseInt(array[0]));
			item.setName(array[1]);
			item.setPrice(Double.parseDouble(array[2]));
			return item;
		}
		
	}

	private static class MilkFactoryEagerSingleton implements ItemFactoryAPI {
		ItemAPI item = new Milk();

		@Override
		public ItemAPI getObject(String line, PersonFactoryEnumSingleton criteria) {

			String[] array = line.split(",");
			item.setId(Integer.parseInt(array[0]));
			item.setName(array[1]);
			item.setPrice(Double.parseDouble(array[2]));
			return item;
		}
	}

	/**
	 * // STUDENT TODO: Object classes
	 */
	private static class Person implements PersonAPI, Comparable<Person> {

		int id;
		String name;
		int age;

		public Person() {
			super();
		}

		public Person(String line) {
			super();
			String[] readLine = line.split(",");
			this.id = Integer.parseInt(readLine[0]);
			this.name = readLine[1];
			this.age = Integer.parseInt(readLine[2]);
			;
		}

		public Person(int id, String name, int age) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
		}

		@Override
		public int getId() {
			return id;
		}

		@Override
		public void setId(int id) {
			this.id = id;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void setName(String name) {
			this.name = name;
		}

		@Override
		public int getAge() {
			return age;
		}

		@Override
		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Person: ");

			sb.append(" # ").append(id);
			sb.append(" ").append(name);
			sb.append(", age ").append(age);

			return sb.toString();
		}

		@Override
		public int compareTo(Person o) {

			return this.getName().compareTo(o.getName());
		}

	}

	private static class Student extends Person implements StudentAPI {

		double gpa;

		public Student() {
			super();
		}

		public Student(int id, String name, int age, double gpa) {
			super(id, name, age);
			this.gpa = gpa;
		}

		public double getGpa() {
			return gpa;
		}

		public void setGpa(double gpa) {
			this.gpa = gpa;
		}

		@Override
		public int getId() {
			return super.id;
		}

		@Override
		public void setId(int id) {
			super.id = id;
		}

		@Override
		public String getName() {
			return super.name;
		}

		@Override
		public void setName(String name) {
			super.name = name;
		}

		@Override
		public String toString() {
			return "Student [gpa=" + gpa + ", id=" + id + ", name=" + name + ", age=" + age + "]";
		}

		@Override
		public int getAge() {

			return super.age;
		}

		@Override
		public void setAge(int age) {
			super.age = age;

		}

	}

	public static class Employee extends Person implements EmployeeAPI {

		double wage;

		public Employee() {
			super();
		}

		public Employee(int id, String name, int age, double wage) {
			super(id, name, age);
			this.wage = wage;
		}

		@Override
		public String toString() {
			return "Employee [wage=" + wage + ", id=" + id + ", name=" + name + ", age=" + age + "]";
		}

		@Override
		public double getWage() {

			return wage;
		}

		@Override
		public void setWage(double wage) {
			this.wage = wage;

		}

	}

	public static class Item implements ItemAPI, Comparable<Item>{
		// STUDENT TODO:
		int id;
		double price;
		String name;
		
		public Item() {
			
		}

		public Item(int id, double price, String name) {
			super();
			this.id = id;
			this.price = price;
			this.name = name;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Item: ");

			sb.append(" # ").append(id);
			sb.append(" $ ").append(price);
			sb.append(" ").append(name);

			return sb.toString();
		}

		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return this.id;
		}

		@Override
		public void setId(int id) {
			this.id = id;
			// TODO Auto-generated method stub
			
		}

		@Override
		public double getPrice() {
			
			return this.price;
		}

		@Override
		public void setPrice(double price) {
			this.price = price;
			
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return this.name;
		}

		@Override
		public void setName(String name) {
			this.name = name;
		}

		@Override
		public int compareTo(Item o) {
			
			return this.getName().compareTo(o.getName());
		}

	} // end class Item

	private static class Bread extends Item implements ItemAPI{

		public Bread(int id, double price, String name) {
			super(id, price, name);
			
		}
		
		public Bread() {
			super();
		}
		
		@Override
		public String toString() {
			return "Bread [id=" + id + ", price=" + price + ", name=" + name + "]";
		}

	}

	private static class Oj extends Item {

		public Oj(int id, double price, String name) {
			super(id, price, name);

		}
		
		public Oj() {
			super();
		}

		@Override
		public String toString() {
			return "Oj [id=" + id + ", price=" + price + ", name=" + name + "]";
		}

	}

	private static class Milk extends Item {

		public Milk(int id, double price, String name) {
			super(id, price, name);
			// TODO Auto-generated constructor stub
		}
		
		public Milk() {
			super();
		}

		@Override
		public String toString() {
			return "Milk [id=" + id + ", price=" + price + ", name=" + name + "]";
		}

	}

	/**
	 * STUDENT TODO: Complete coding of this method
	 */
	public static void demoIntegerModel() {
		// STUDENT TODO:
		int[] a = { 3, 1, 4, 2 }; // supplied data to add to model IN THIS ORDER
		Model<Integer> model = new Model<>();
		for(Integer b: a) {
			model.list.add(b);
		}
		 System.out.println(model);

		System.out.println("sort in Default Natural (numerical) order...");
		// STUDENT TODO:
		model.list.sort(null);
		 System.out.println(model);

		System.out.println("sort in REVERSE (numerical) order...");
		// STUDENT TODO:
		model.list.sort((o1,o2) -> (o2.compareTo(o1)));
		 System.out.println(model);
	}

	/**
	 * STUDENT TODO: Complete coding of this method
	 */
	public static void demoStringModel() {
		// STUDENT TODO:
		String[] a = { "C", "A", "D", "B" }; // supplied data to add to model IN THIS ORDER
		// STUDENT TODO:
		Model<String> model = new Model<>();
		for(String b: a) {
			model.list.add(b);
		}
		 System.out.println(model);

		System.out.println("sort in Default Natural (alphabetical) order...");
		// STUDENT TODO:
		model.list.sort(null);
		 System.out.println(model);

		System.out.println("sort in REVERSE alphabetical order...");
		// STUDENT TODO:
		model.list.sort((o1,o2) -> (o2.compareTo(o1)));
		 System.out.println(model);
	}

	/**
	 * STUDENT TODO: Complete coding of this method
	 */
	public static void demoItemModel() {
		Model<ItemAPI> model = new Model<>(Item.class.getSimpleName());
		/**
		 * STUDENT TODO:
		 * 
		 * IN THE FOLLOWING ORDER add Item object to model using a Eager Singleton
		 * factory AND ITEM_CSV_DATA add Bread object to model using a Lazy Singleton
		 * factory AND ITEM_CSV_DATA add Oj object to model using a Eager Singleton
		 * factory AND ITEM_CSV_DATA add Milk object to model using a Eager Singleton
		 * factory AND ITEM_CSV_DATA
		 */
		// STUDENT TODO:
		
		ItemFactoryAPI item = new ItemFactoryEagerSingleton();
		model.list.add(item.getObject("10,Bread,3.56",PersonFactoryEnumSingleton.BREAD));
		
		ItemFactoryAPI item1 = new BreadFactoryLazySingleton();
		model.list.add(item1.getObject("15,Bread,6.78",PersonFactoryEnumSingleton.BREAD));

		ItemFactoryAPI item2 = new OjFactoryEagerSingleton();
		model.list.add(item2.getObject("16,OJ,7.68",PersonFactoryEnumSingleton.OJ));
		
		ItemFactoryAPI item3 = new MilkFactoryEagerSingleton();
		model.list.add(item3.getObject("6,MILK,4.5",PersonFactoryEnumSingleton.MILK));
		
		System.out.println(model);

		System.out.println("sort by ID...");
		model.list.sort((o1,o2) -> ((Integer) o1.getId()).compareTo((Integer) o2.getId()));
		System.out.println(model);

		System.out.println("sort by ASCENDING PRICE (e.g. $1,$2,$3,$4,$5...");
		model.list.sort((o1,o2) -> ((Double) o1.getPrice()).compareTo((Double) o2.getPrice()));
		System.out.println(model);

		System.out.println("sort in Default Natural (alphabetical) order...");
		// STUDENT TODO:
		model.list.sort(null);
		System.out.println(model);

		System.out.println("sort in REVERSE alphabetical order...");
		// STUDENT TODO:
		model.list.sort((o1,o2) ->  o2.getName().compareTo( o1.getName()));
		System.out.println(model);
	}

	/**
	 * STUDENT TODO: Complete coding of this method
	 */
	public static void demoPersonModel() {
		Model<PersonAPI> model = new Model<>(Person.class.getSimpleName());
		/**
		 * STUDENT TODO:
		 * 
		 * IN THE FOLLOWING ORDER add Person object to model using an Enum Singleton
		 * factory AND PERSON_CSV_DATA add Student object to model using a Eager
		 * Singleton factory AND PERSON_CSV_DATA add Employee object to model using a
		 * Lazy Singleton factory AND PERSON_CSV_DATA
		 */
		// STUDENT TODO:

		PersonFactoryAPI person = new PersonFactoryEagerSingleton();
		PersonAPI object = (PersonAPI) person.getObject("12,Akshay Datir,26,98", PersonFactoryEnumSingleton.EMPLOYEE);
		model.list.add(object);

		PersonFactoryAPI student = new StudentFactoryEagerSingleton();
		PersonAPI object1 = (PersonAPI) student.getObject("45,Pranav Kapse,24,3.5", PersonFactoryEnumSingleton.STUDENT);
		model.list.add(object1);

		PersonFactoryAPI employee = new EmployeeFactoryEagerSingleton();
		String employeCSV = "19,Kaustubh Uchgaonkar,29,67";
		PersonAPI object2 = employee.getObject(employeCSV, PersonFactoryEnumSingleton.EMPLOYEE);
		model.list.add(object2);

		PersonFactoryAPI employee1 = new EmployeeFactoryLazySingleton();
		String employeCSV1 = "36,Anuj Pawar,22,58";
		PersonAPI object3 = employee1.getObject(employeCSV1, PersonFactoryEnumSingleton.EMPLOYEE);
		model.list.add(object3);

		System.out.println(model);

		System.out.println("sort by ID...");
		model.list.sort((o1, o2) -> ((Integer) o1.getId()).compareTo((Integer) o2.getId()));
		System.out.println(model);

		System.out.println("sort by ASCENDING Age (e.g. 1,2,3,4,5)...");

		model.list.sort((o1, o2) -> ((Integer) o1.getAge()).compareTo((Integer) o2.getAge()));
		System.out.println(model);

		System.out.println("sort in Default Natural (alphabetical) order...");

		model.list.sort(null);
		System.out.println(model);

		System.out.println("sort in REVERSE alphabetical order...");
		model.list.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
		System.out.println(model);
	}

	public static void demo() {
		System.out.println(
				"\n\t" + MidtermCSYE6200Fall23Student.class.getName() + ".demo() [ version " + VERSION + " ] ...");

		 MidtermCSYE6200Fall23Student.demoIntegerModel();
		 MidtermCSYE6200Fall23Student.demoStringModel();
		 MidtermCSYE6200Fall23Student.demoItemModel();
		 MidtermCSYE6200Fall23Student.demoPersonModel();

		System.out.println("\n\t" + MidtermCSYE6200Fall23Student.class.getName() + ".demo() ... done!");
	}

}
