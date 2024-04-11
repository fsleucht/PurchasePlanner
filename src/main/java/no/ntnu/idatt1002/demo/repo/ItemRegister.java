package no.ntnu.idatt1002.demo.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import no.ntnu.idatt1002.demo.dao.DAO;
import no.ntnu.idatt1002.demo.data.Item;

/**
 * This class represents a register for items. Allowing for communication
 * between the database and the user interface.
 */
public class ItemRegister {

  private List<Item> items;
  private final DAO dao;

  /**
   * Constructor for the ItemRegister class.
   *
   * @param dao the data access object
   */
  public ItemRegister(DAO dao) {
    this.dao = dao;
    this.items = new ArrayList<>();
  }

  /**
   * This method returns the items in the register in the form of lists.
   *
   * @return the items in the register as lists of strings
   */
  public Map<Integer, Item> getItems() {
    return items.stream().collect(Collectors.toMap(Item::getId, item -> item));
  }

  /**
   * This method retrieves filtered items by category from the database.
   *
   * @param category the category to filter by
   */
  public void filterItemsByCategory(String category) {
    items = new ArrayList<>();
    List<List<String>> items = dao.filterFromTable("Item", "category", category, null, null);
    packageToItem(items);
  }

  /**
   * This method searches for items by name and retrieves them from the database.
   *
   * @param name the name to search by
   */
  public void searchItemsByName(String name) {
    items = new ArrayList<>();
    List<List<String>> items = dao.searchFromTable("Item", name, null, null);
    packageToItem(items);
  }

  /**
   * This method retrieves all items from the database.
   */
  public void getAllItems() {
    items = new ArrayList<>();
    List<List<String>> items = dao.getAllFromTable("Item", null, null);
    packageToItem(items);
  }

  /**
   * This method packages the lists of strings into items.
   */
  private void packageToItem(List<List<String>> items) {
    for (List<String> item : items) {
      this.items.add(
          new Item(Integer.parseInt(item.get(0)), item.get(1), item.get(2), item.get(3)));
    }
  }

  /**
   * This method adds a new item to the database.
   *
   * @param name     the name of the item
   * @param category the category of the item
   * @param allergy  the allergy of the item
   */
  public void addItem(String name, String category, String allergy) {
    dao.addToDatabase(new Item(name, category, allergy));
  }

  /**
   * This method deletes an item from the database.
   *
   * @param id the id of the item to delete
   */
  public void deleteItem(int id) {
    int index = getItemFromId(id);
    dao.deleteFromDatabase(items.get(index));
  }

  public Item getItemById(int id) {
    return items.get(id);
  }

  /**
   * This method returns the index of the item with the given id.
   *
   * @param id the id of the item
   * @return the index of the item with the given id
   */
  private int getItemFromId(int id) {
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i).getId() == id) {
        return i;
      }
    }
    return -1;
  }



  /**
   * This method updates an item in the database.
   *
   * @param id       the id of the item to update
   * @param name     the name of the item
   * @param category the category of the item
   * @param allergy  the allergy of the item
   */
  public void updateItem(int id, String name, String category, String allergy) {
    Item item = new Item(id, name, category, allergy);
    dao.updateDatabase(item);
  }

  /**
   * This method returns a string representation of the item register.
   *
   * @return a string representation of the item register
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Item item : items) {
      sb.append(item.toString()).append("\n");
    }
    return sb.toString();
  }
}
