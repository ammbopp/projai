package cs211.project.models.collections;
import cs211.project.models.Event;

import java.util.ArrayList;

public class EventCollection {
    private ArrayList<Event> events;

    public EventCollection() {
        events = new ArrayList<>();
    }

    public void addNewEvent(String eventName,String eventDetail,int seatFull,
                            String startDate, String dueDate, String creatorUsername, String eventPic) {
        eventName = eventName.trim();
        if (!eventName.equals("")) {
            Event exist = findEventByEventName(eventName);
            if (exist == null) {
                events.add(new Event(eventName.trim(), eventDetail.trim(), seatFull
                        ,startDate.trim(),dueDate.trim(),creatorUsername.trim(), eventPic.trim()));
            }
        }
    }

    public Event findEventByEventName(String eventName){
        for (Event event : events) {
            if (event.isEventName(eventName)) {
                return event;
                /* check student id คนนั้นใน students */
            }
        }
        return null;
    }

    public void updateEvent(Event updatedEvent) {
        // หาตำแหน่งของอีเวนต์ในรายการอีเวนต์และอัปเดต
        for (int i = 0; i < events.size(); i++) {
            Event existingEvent = events.get(i);
            if (existingEvent.getEventName().equals(updatedEvent.getEventName())) {
                events.set(i, updatedEvent); // อัปเดตอีเวนต์ที่ตำแหน่งนี้ด้วยข้อมูลใหม่
                break; // หยุดการวนลูปเมื่อพบอีเวนต์ที่ต้องการ
            }
        }
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
