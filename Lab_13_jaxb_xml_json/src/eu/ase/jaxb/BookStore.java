package eu.ase.jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

//This statement means that class "BookStore.java" is the root-element of our example
@XmlRootElement(namespace = "eu.ase.jaxb")
public class BookStore {

  // XmLElementWrapper generates a wrapper element around XML representation, name is optional
  @XmlElementWrapper(name = "bookList")
  // XmlElement sets the name of the entities
  @XmlElement(name = "book")
  private ArrayList<Book> bookList;
  private String name;
  private String location;

  public void setBookList(ArrayList<Book> bookList) {
    this.bookList = bookList;
  }

  public ArrayList<Book> getBooksList() {
    return bookList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
} 