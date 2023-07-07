package cc.unitmesh.untitled.demo.controller;

import cc.unitmesh.untitled.demo.WebResponse;
import cc.unitmesh.untitled.demo.dto.BookingDTO;
import cc.unitmesh.untitled.demo.dto.BookingResponse;
import cc.unitmesh.untitled.demo.framework.Acl;
import cc.unitmesh.untitled.demo.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/meeting-rooms")
@Api(value = "会议室接口", tags = {"会议室"})
public class MeetingRoomController {
    @Autowired
    MeetingService meetingService;

    // POST /meeting-rooms/bookings
    // BookingDTO: { "roomId": string, "startTime": string, "endTime": string, "attendees": [string] }
    // WebResponse<BookingResponse>: { "id": string, "roomId": string, "startTime": string, "endTime": string, "attendees": [string] }
    @Acl
    @PostMapping("/bookings")
    @ApiOperation(value = "会议室初始化", notes = "初始化会议室")
    public WebResponse<BookingResponse> bookMeetingRoom(@RequestBody BookingDTO bookingDTO) {
        return null; // TODO
    }
}