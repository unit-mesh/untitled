package cc.unitmesh.untitled.demo.entity;

import cc.unitmesh.untitled.demo.framework.LogEntity;
import cc.unitmesh.untitled.demo.framework.LogKey;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName(value = "twc_meeting_room")
@LogEntity(tableName = "twc_meeting_room", entityName = "会议室")
public class MeetingRoom {
    @TableId
    @LogKey(keyName = "会议室编号")
    private String id;

    @LogKey(keyName = "会议室名称")
    private String roomName;

    @LogKey(keyName = "会议参与人")
    private List<String> attendees;

    @LogKey(keyName = "会议状态")
    private String status;

    @LogKey(keyName = "会议开始时间")
    private String startTime;

    @LogKey(keyName = "会议结束时间")
    private String endTime;

    @LogKey(keyName = "会议主题")
    private String subject;

    @LogKey(keyName = "会议描述")
    private String description;

    public MeetingRoom() {
    }

    public MeetingRoom(String roomName, List<String> attendees, String status, String startTime, String endTime, String subject, String description) {
        this.roomName = roomName;
        this.attendees = attendees;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.description = description;
    }
}
