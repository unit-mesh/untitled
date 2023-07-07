package cc.unitmesh.untitled.demo.dto;

import java.util.List;

public class BookingDTO {
    private String roomId;
    private String startTime;
    private String endTime;
    private List<String> attendees;

    public BookingDTO(String roomId, String startTime, String endTime, List<String> attendees) {
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attendees = attendees;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }
}
