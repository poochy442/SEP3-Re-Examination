package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import VIA.VIATalks.Database.data.Event;

import java.util.List;

public interface IEventCategoryHandler {
    public List<String> getAllEventCategories();
    public List<String> getEventCategoriesById(List<Integer> categoryIds);
    public boolean attachCategoryToEvent(String eventCategory, int eventID);
    public boolean attachCategoryToPendingEvent(String eventCategory, int eventID);
    public boolean updateEventCategory(Event event, String category);



}
