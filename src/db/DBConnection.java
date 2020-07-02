package db;

import java.util.List;
import java.util.Map;
import java.util.Set;
import entity.Item;
import entity.Itinerary;
import entity.Manager;
import entity.PointsOfInterests;
import entity.Reservation;
import entity.Room;
import entity.User;

public interface DBConnection {
    /**
     * Close the connection.
     */
    public void close();

    public Set<User> managerGetAllUsers();

    void managerDeleteIti(String iId);

    List<PointsOfInterests> managerGetPIs();
    public Set<Room> managerGetAllRooms();
    public Set<Manager> managerGetAllManagers();

    /**
     * Insert the favorite items for a user.
     *
     * @param userId
     * @param itemIds
     */
    public void setFavoriteItems(String userId, List<String> itemIds);
    /**
     * Delete the favorite items for a user.
     *
     * @param userId
     * @param itemIds
     */
    public void unsetFavoriteItems(String userId, List<String> itemIds);





    /**
     * Get the favorite item id for a user.
     *
     * @param userId
     * @return itemIds
     */
    public Set<String> getFavoriteItemIds(String userId);

    /**
     * Get the favorite items for a user.
     *
     * @param userId
     * @return items
     */
    public Set<Item> getFavoriteItems(String userId);

    /**
     * Gets categories based on item id
     *
     * @param itemId
     * @return set of categories
     */
    public Set<String> getCategories(String itemId);

    /**
     * Search items near a geolocation and a term (optional).
     *
     * @param userId
     * @param lat
     * @param lon
     * @param term
     *            (Nullable)
     * @return list of items
     */
    public List<Item> searchItems(double lat, double lon, String term);

    /**
     * Save item into db.
     *
     * @param item
     */
    public void saveItem(Item item);

    /**
     * Get full name of a user.
     *
     * @param userId
     * @return full name of the user
     */
    public String getFullname(String userId);

    /**
     * Return whether the credential is correct.
     *
     * @param userId
     * @param password
     * @return boolean
     */
    public boolean verifyLogin(String userId, String password);

    public boolean register(String userId, String password, String firstname, String lastname);

    boolean changePassword(String userId, String newPassword, String oldPassword);

    Set<Itinerary> managerGetAllIti();

    List<PointsOfInterests> managerGetPIs(Itinerary iT);

    void managerInsertCombo(String iid, String pids);

    void managerInsertIti(String iid, String itype, String isize);

    void userAddReservation(String userID, String roomID, String checkInDate, String checkOutDate);

    Set<Room> userGetAllRooms();

    Set<String> managerGetUsersBookAll();

    List<Room> userGetRoomsPriceAsc();

    List<Room> managerGetRoomsPriceAsc();

    Set<Reservation> userGetAllReservations(String userID);

    List<Itinerary> userGetAllItineraries();

    List<Itinerary> userSortItinerariesBySize();

    int managerGetNumOfBookedRoom();

    Map<String, Integer> managerGetNumOfUserForIt();
}
