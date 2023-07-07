package cc.unitmesh.untitled.demo.service;

import cc.unitmesh.untitled.demo.dto.BookingResponse;
import cc.unitmesh.untitled.demo.entity.MeetingRoom;

public interface MeetingService {
    BookingResponse bookMeetingRoom(MeetingRoom meetingRoom);
}
