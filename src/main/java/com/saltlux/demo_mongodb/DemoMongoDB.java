package com.saltlux.demo_mongodb;

import java.util.Iterator;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.saltlux.validate.Validate;

public class DemoMongoDB {
//	private static Scanner sc = new Scanner(System.in);
//	private static MongoClient mongoClient = new MongoClient("localhost", 27017);
//	
//	private static MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
//	private static MongoCollection<Document> collection = mongoDatabase.getCollection("demo_collection");

	public static void main(String[] args) {

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		Scanner sc = new Scanner(System.in);

		DemoMongoDB demoMongoDB = new DemoMongoDB();

		// print menu
		while (true) {
			System.out.println("----------DemoMongoDB----------\n" + "Chose option:\n" + "1. List All Connection\n"
					+ "2. Drop Database\n" + "3. Create Collection\n" + "4. Insert Document\n" + "5. Find All\n"
					+ "6. Find By ID\n" + "7. Find By Age And Name\n" + "8. Delete Document By ID\n"
					+ "9. Update Age By Name\n" + "10. Transfer money\n" + "11. Exit\n");
			// create option
			int option1 = 0;
			// input option
			option1 = Validate.checkOption(1, 11);
			// switch case
			switch (option1) {
			case 1:
				// call listAll function
				demoMongoDB.listAllConnection();
				break;
			case 2:
				// call dropDatabase function
				demoMongoDB.dropDatabase();
				break;
			case 3:
				// call createCollection function
				demoMongoDB.createCollection();
				break;
			case 4:
				// call insertDocument function
				demoMongoDB.insertDocument();
				break;
			case 5:
				// call findAll function
				demoMongoDB.findAll();
				break;
			case 6:
				// call findByID function
				demoMongoDB.findByID();
				break;
			case 7:
				// call findByAgeAndName function
				demoMongoDB.findByAgeAndName();
				break;
			case 8:
				// call deleteByID function
				demoMongoDB.deleteByID();
				break;
			case 9:
				// call updateAgeByName function
				demoMongoDB.updateAgeByName();
				break;
			case 10:
				demoMongoDB.transactionTransfer();
				break;
			case 11:
				System.out.println("Bye !");
				// exit program
				return;
			}
			// check continue program
			System.out.println("Press any key to continue, n to exit !");
			String confirmString = sc.nextLine();
			if (confirmString.equals("n")) {
				System.out.println("Bye !");
				mongoClient.close();
				// exit program
				return;
			}
		}
	}

	public void listAllConnection() {
		MongoOptions option = new MongoOptions();
		option.setConnectionsPerHost(20);
		option.setSocketTimeout(3000);
		option.setConnectTimeout(30000);

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("demo_collection");

		System.out.println("---------- List databases --------------------");
		for (String databaseNames : mongoClient.listDatabaseNames()) {
			System.out.println(databaseNames);
		}

		System.out.println("\n---------- List collectors --------------------");

		for (String collectionNames : mongoDatabase.listCollectionNames()) {
			System.out.println(collectionNames);
		}
	}

	public void dropDatabase() {
		MongoOptions option = new MongoOptions();
		option.setConnectionsPerHost(20);
		option.setSocketTimeout(3000);
		option.setConnectTimeout(30000);

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("demo_collection");
		String databaseName = Validate.checkStringNonSpace("Enter name database need drop: ",
				"Must be string without space. Input again !");

		// drop database
		mongoClient.dropDatabase(databaseName);
		System.out.println("Drop database name ' " + databaseName + " ' successful !");
	}

	public void createCollection() {
		MongoOptions option = new MongoOptions();
		option.setConnectionsPerHost(20);
		option.setSocketTimeout(3000);
		option.setConnectTimeout(30000);

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("demo_collection");
		String collectionName = Validate.checkStringNonSpace("Enter name collection need create: ",
				"Must be string without space. Input again !");

		// create collection
		try {
			mongoDatabase.createCollection(collectionName);
			System.out.println("Create susscessful !");
		} catch (Exception e) {
			System.out.println(collectionName + " is already exits !");
		}
	}

	public void insertDocument() {
		MongoOptions option = new MongoOptions();
		option.setConnectionsPerHost(20);
		option.setSocketTimeout(3000);
		option.setConnectTimeout(30000);

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("demo_collection");
		Document person = new Document().append("id", 11).append("name", "Nguyen Van A").append("age", 27)
				.append("address", "Nghe An").append("height", 180).append("weight", 89).append("gender", "male");

		// insert document
		collection.insertOne(person);
		System.out.println("Inserted!");
	}

	public void findAll() {
		MongoOptions option = new MongoOptions();
		option.setConnectionsPerHost(20);
		option.setSocketTimeout(3000);
		option.setConnectTimeout(30000);

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("demo_collection");
		// find all
		FindIterable<Document> iterDoc = collection.find();
		Iterator<Document> it = iterDoc.iterator();

		System.out.println("--------------- All People ------------------");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public void findByID() {
		MongoOptions option = new MongoOptions();
		option.setConnectionsPerHost(20);
		option.setSocketTimeout(3000);
		option.setConnectTimeout(30000);

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("demo_collection");
		int ID = Validate.checkNumber("Enter ID to find: ", "Must be number. In put again !");
		boolean check = false;

		// find by id
		FindIterable<Document> iterDoc = collection.find(Filters.eq("id", ID));
		Iterator<Document> it = iterDoc.iterator();

		System.out.println("--------------- All People By ID: " + ID + " ------------------");
		while (it.hasNext()) {
			System.out.println(it.next());
			check = true;
		}
		if (!check) {
			System.out.println("No result !");
		}
	}

	public void findByAgeAndName() {
		MongoOptions option = new MongoOptions();
		option.setConnectionsPerHost(20);
		option.setSocketTimeout(3000);
		option.setConnectTimeout(30000);

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("demo_collection");
		int age = Validate.checkNumber("Enter age to find: ", "Must be number. In put again !");
		String name = Validate.checkString("Enter name to find: ", "Must be string. Input again !");
		boolean check = false;

		// find by age and name
		FindIterable<Document> iterDoc = collection
				.find(Filters.and(Filters.lte("age", age), Filters.regex("name", name)));
		Iterator<Document> it = iterDoc.iterator();

		System.out.println("----------- List People with age <= " + age + " and name like " + name + " --------");
		while (it.hasNext()) {
			System.out.println(it.next());
			check = false;
		}
		if (!check) {
			System.out.println("No result !");
		}
	}

	public void deleteByID() {
		MongoOptions option = new MongoOptions();
		option.setConnectionsPerHost(20);
		option.setSocketTimeout(3000);
		option.setConnectTimeout(30000);

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("demo_collection");
		int ID = Validate.checkNumber("Enter ID to delete: ", "Must be number. In put again !");

		FindIterable<Document> iterDoc = collection.find(Filters.eq("id", ID));
		Iterator<Document> it = iterDoc.iterator();

		if (it.hasNext()) {
			System.out.println("Deleted the document: ");
			System.out.println(it.next());

			// delete
			collection.deleteOne(Filters.eq("id", ID));

			FindIterable<Document> iterDoc2 = collection.find();
			Iterator<Document> it2 = iterDoc2.iterator();
			System.out.println("--------------- List Person Updated ------------------");
			while (it2.hasNext()) {
				System.out.println(it2.next());
			}
		} else {
			System.out.println("No result !");
		}

	}

	public void updateAgeByName() {
		MongoOptions option = new MongoOptions();
		option.setConnectionsPerHost(20);
		option.setSocketTimeout(3000);
		option.setConnectTimeout(30000);

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("demo_collection");
		String name = Validate.checkString("Enter name to update: ", "Must be string. Input again !");
		int age = Validate.checkNumber("Enter update age: ", "Must be number. In put again !");
		boolean check = false;

		// update
		try {
			collection.updateOne(Filters.eq("name", name), Updates.set("age", age));
		} catch (Exception e) {
			check = false;
		}

		FindIterable<Document> iterDoc = collection.find(Filters.eq("name", name));
		Iterator<Document> it = iterDoc.iterator();

		System.out.println("--------------- Person Updated ------------------");
		while (it.hasNext()) {
			System.out.println(it.next());
			check = true;
		}
		if (!check) {
			System.out.println("No result !");
		}
	}

	public void transactionTransfer() {
		MongoOptions option = new MongoOptions();
		option.setConnectionsPerHost(20);
		option.setSocketTimeout(3000);
		option.setConnectTimeout(30000);

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

		MongoCollection<Document> collection = mongoDatabase.getCollection("bank");
		boolean check = false;
		String accountNumberTransferFrom = "";
		String accountNumberTransferTo = "";
		Document document = null;
		Document document2 = null;

		while (!check) {
			accountNumberTransferFrom = Validate.checkStringNumberNonSpace("Enter number of account transfer from: ",
					"Must be number without space !");

			try {
				document = collection.find(Filters.eq("accountNumber", accountNumberTransferFrom)).first();
				check = true;
			} catch (Exception e) {
				System.out.println("No result !");
				return;
			}

		}

		check = false;

		MongoCursor<Document> cursor2 = null;
		while (!check) {
			accountNumberTransferTo = Validate.checkStringNumberNonSpace("Enter number of account transfer to: ",
					"Must be number without space !");

			try {
				document2 = collection.find(Filters.eq("accountNumber", accountNumberTransferTo)).first();
				check = true;
			} catch (Exception e) {
				System.out.println("No result !");
				return;
			}
		}

		double valueTransferMoney = Validate.checkDouble("Enter value of money need to transfer: ",
				"Must be double. Input again !");
		Double balance = 0d;
		Double balance2 = 0d;
		if (check) {
			balance = document.get("balance", Double.class);
			balance2 = document2.get("balance", Double.class);
		}

		collection.updateOne(Filters.eq("accountNumber", accountNumberTransferFrom),
				Updates.set("balance", balance - valueTransferMoney));

		if (check) {
			document = collection.find(Filters.eq("accountNumber", accountNumberTransferFrom)).first();

			balance = document.get("balance", Double.class);

			if (balance < 0) {
				System.out.println("Can't transfer. The balance of " + accountNumberTransferFrom + " is not enough !");

				collection.updateOne(Filters.eq("accountNumber", accountNumberTransferFrom),
						Updates.set("balance", balance + valueTransferMoney));

				document = collection.find(Filters.eq("accountNumber", accountNumberTransferFrom)).first();

			} else {
				System.out.println("Transfer " + valueTransferMoney + " from " + accountNumberTransferFrom + " to "
						+ accountNumberTransferTo + " susscessful !");

				collection.updateOne(Filters.eq("accountNumber", accountNumberTransferTo),
						Updates.set("balance", balance2 + valueTransferMoney));

				document2 = collection.find(Filters.eq("accountNumber", accountNumberTransferTo)).first();

			}

		}
		System.out.println(document);
		System.out.println(document2);
	}
}