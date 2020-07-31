package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import VIA.VIATalks.Database.data.Event;

import java.util.List;

public interface IEventCategory {
    public List<String> getAllEventCategories();
    public List<String> getEventCategoriesById(List<Integer> categoryIds);
    public int getEventCategoryIdByName(String category);
    public boolean updateEventCategory(Event event, String category);

}
