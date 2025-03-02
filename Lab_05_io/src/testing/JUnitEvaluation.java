package testing;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.ase.csie.java.Guest;
import com.ase.csie.java.Transaction;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Test;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitEvaluation {

	@Test
	public void _310testInfoClassTranscation_mark3() throws Exception {
		Class<?> t = Class.forName("com.ase.csie.java.Transaction");
		if(t.getDeclaredFields().length < 5)
			fail("Not proper number of the fields");
		for (Field f : t.getDeclaredFields()) {
			try {
	            //assertNotNull(f);
				System.out.println("Transaction field: " + f.toString());
	            assertTrue("The field " + f.toString() + " is private",  Modifier.isPrivate(f.getModifiers()));
	            if (f.getName().compareTo("id") == 0)
	            	assertEquals("The 'id' is type int", int.class, f.getType());
	            if (f.getName().compareTo("guest") == 0)
	            	assertEquals("The 'guest' is type Guest", Guest.class, f.getType());
	            if (f.getName().compareTo("date") == 0)
	            	assertEquals("The 'date' is type LocalDateTime", LocalDateTime.class, f.getType());
	            if (f.getName().compareTo("items") == 0)
	            	assertEquals("The 'items' is type String[]", String[].class, f.getType());
	            if (f.getName().compareTo("prices") == 0)
	            	assertEquals("The 'prices' is type float[]", float[].class, f.getType());
	        } catch (Exception nsfe) {
	            fail("The field "+f.toString()+" has problems in class Invoice.");
	        }
		}
	}
	
	@Test
	public void _313testTransactionWrite2File_mark3() throws Exception {
		String[] items = new String[] {"Single Room", "Suite", "Suite"};
		float[] prices = new float[] {125.5f, 199, 234};
		
		LocalDateTime now = LocalDateTime.now();
		int id = 123;
		Guest guest = new Guest("Bob");
		Transaction transaction = new Transaction(id, guest, now, items, prices);
		
		
		System.out.println("Transaction test313 display = " + transaction.toString());
		
		transaction.saveTransaction2File("test3.txt");
		
		double total = 0.0;
		DataInputStream in = null;
		
			in = new DataInputStream(new BufferedInputStream(new FileInputStream("test3.txt")));
			int rid;
			Guest rguest;
			LocalDateTime rdate;
			
			
			
			try {
				rid = in.readInt();
				assertEquals(id, rid);
				rguest = new Guest(in.readUTF());
				assertEquals(guest.getName(), rguest.getName());
				rdate = LocalDateTime.parse(in.readUTF(), DateTimeFormatter.ISO_DATE_TIME);
				assertEquals(now.toEpochSecond(ZoneOffset.UTC), rdate.toEpochSecond(ZoneOffset.UTC));
//				System.out.printf("Read record - id = %d, guest = %s, date = %s \nwith transaction items: ", id, guest.getName(), date.toString());
				while(true) {
					in.readUTF();
					float price = in.readFloat();
					total += price;
				}
			} catch(EOFException eofe) {
				try {
					assertEquals(558.5, total, 0.5);
					in.close();
					File f = new File("test3.txt");
					f.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
	@Test
	public void _313testTransactionReadFromFile_mark3() throws Exception {
		String[] items = new String[] {"Single Room", "Suite", "Suite"};
		float[] prices = new float[] {125.5f, 199, 234};
		
		LocalDateTime now = LocalDateTime.now();
		int id = 123;
		Guest guest = new Guest("Bob");
		Transaction transaction = new Transaction(123, new Guest("Bob"), LocalDateTime.now(), items, prices);

		
		DataOutputStream out = null;
		
		try {
			FileOutputStream fos = new FileOutputStream("test4.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			out = new DataOutputStream(bos);
			
			out.writeInt(id);
			out.writeUTF(guest.getName());
			out.writeUTF(now.format(DateTimeFormatter.ISO_DATE_TIME));
			
			for(int i = 0; i < items.length; i++) {
				out.writeUTF(items[i]);
				out.writeFloat(prices[i]);
			}
			
			out.close();
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		double total = transaction.readTransactionFromFileAndCalcTotal("test4.txt");
		
		try {
			File f = new File("test4.txt");
			f.delete();
			assertEquals(558.5, total, 0.5);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void _315testInvoiceClone_mark3() throws Exception {
		String[] items = new String[] {"Single Room", "Suite", "Suite"};
		float[] prices = new float[] {125.5f, 199, 234};
		
		Transaction transaction1 = new Transaction(123, new Guest("Bob"), LocalDateTime.now(), items, prices);
		Transaction transaction2 = (Transaction) transaction1.clone();

		assertNotSame(transaction1, transaction2);
		
		if(transaction1.getId() != transaction2.getId()) {
			fail("clone not correct implemented (ID)");
		}
		//Immutable
//		if(transaction1.getDate() == transaction2.getDate()) {
//			fail("clone not correct implemented (DATE)");
//		}
		if(transaction1.getGuest() == transaction2.getGuest()) {
			fail("clone not correct implemented (GUEST)");
		}
		if (transaction1.getItems() == transaction2.getItems()) {
			fail("clone not correct implemented (ITEMS)");
		}
		if (transaction1.getPrices() == transaction2.getPrices()) {
			fail("clone not correct implemented (PRICES)");
		}
		if (! Arrays.equals(transaction1.getPrices(), transaction2.getPrices()) ) {
			fail("clone not correct implemented (ITEMS ARR)");
		}
		if (! Arrays.equals(transaction1.getItems(), transaction2.getItems()) ) {
			fail("clone not correct implemented (PRICES ARR)");
		}
		
	}

}
