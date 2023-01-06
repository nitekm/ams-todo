package ncode.dev.bffwife.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleRequestDTO {
    private String id;
    private LocalDate scheduleDate;
}
