package cc.unitmesh.untitled.demo.dto;

import java.util.List;

public class BookingResponse {
    private String id;
    private String roomId;
    private String startTime;
    private String endTime;
    private List<String> attendees;

    public BookingResponse(String id, String roomId, String startTime, String endTime, List<String> attendees) {
        this.id = id;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attendees = attendees;
    }

    public String getId() {
        return id;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public List<String> getAttendees() {
        return attendees;
    }
}