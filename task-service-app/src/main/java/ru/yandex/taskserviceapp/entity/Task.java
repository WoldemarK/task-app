package ru.yandex.taskserviceapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy-HH:mm")
    private LocalDateTime remindAt; // когда напомнить

    @JsonFormat(pattern = "dd.MM.yyyy-HH:mm")
    private LocalDateTime dueAt; // дедлайн

    @JsonFormat(pattern = "dd.MM.yyyy-HH:mm")
    private LocalDateTime completedAt; // Завершено

    @Column(nullable = false)
    private boolean reminderSent = false; //Чтобы не отправлять напоминание повторно

    @CreationTimestamp
    @JsonFormat(pattern = "dd.MM.yyyy-HH:mm")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "dd.MM.yyyy-HH:mm")
    private LocalDateTime updatedAt;


}
