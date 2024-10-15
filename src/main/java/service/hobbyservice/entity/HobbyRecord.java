package service.hobbyservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import service.hobbyservice.base.BaseEntity;

import java.sql.Time;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HobbyRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recordBody;

//    private Date hobbyDay;

    private String imageUrl;

//    private String hobbyName;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobby_routine_id")
    private HobbyRoutine hobbyRoutine;

//    private Time hobbyTime;

    @Builder
    public HobbyRecord(String recordBody, HobbyRoutine hobbyRoutine) {
        this.recordBody = recordBody;
        this.imageUrl = imageUrl;
        this.hobbyRoutine = hobbyRoutine;
    }

    public void setHobbyRoutine(HobbyRoutine hobbyRoutine){
        this.hobbyRoutine = hobbyRoutine;
    }
}
