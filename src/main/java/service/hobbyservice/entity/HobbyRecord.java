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

    private String photoUrl;

//    private String hobbyName;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobby_routine_id")
    private HobbyRoutine hobbyRoutine;

//    private Time hobbyTime;

    @Builder
    public HobbyRecord(String recordBody, Long userId, String photoUrl) {
        this.recordBody = recordBody;
        this.photoUrl = photoUrl;
        this.userId = userId;
    }

    public void setHobbyRoutine(HobbyRoutine hobbyRoutine){
        this.hobbyRoutine = hobbyRoutine;
    }
}
