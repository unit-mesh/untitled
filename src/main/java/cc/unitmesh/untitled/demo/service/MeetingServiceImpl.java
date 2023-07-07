package cc.unitmesh.untitled.demo.service;

import cc.unitmesh.untitled.demo.dto.BookingDTO;
import cc.unitmesh.untitled.demo.dto.BookingResponse;
import cc.unitmesh.untitled.demo.entity.MeetingRoom;
import cc.unitmesh.untitled.demo.mapper.MeetingRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private ClientService clientService;

    @Resource
    private MeetingRoomMapper meetingRoomMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookingResponse bookMeetingRoom(MeetingRoom meetingRoom) {
        // 1. 检查会议室是否可用，如果不可用，返回错误信息；2. 如果可用，预定会议室；3. 返回预定成功信息
        return null; // TODO
    }
}
