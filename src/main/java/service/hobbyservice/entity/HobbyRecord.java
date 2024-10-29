package service.hobbyservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import service.hobbyservice.base.BaseEntity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HobbyRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_record_id")
    private Long id;

    private String recordBody;

//    private Date hobbyDay;

    private LocalDate routineDate;
    private String photoUrl;

//    private String hobbyName;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobby_routine_id")
    private HobbyRoutine hobbyRoutine;

//    private Time hobbyTime;

    @Builder
    public HobbyRecord(String recordBody, Long userId, String photoUrl, LocalDate routineDate) {
        this.recordBody = recordBody;
        this.photoUrl = photoUrl;
        this.userId = userId;
        this.routineDate = routineDate;
    }

    public void setHobbyRoutine(HobbyRoutine hobbyRoutine){
        this.hobbyRoutine = hobbyRoutine;
    }


    //취미 업데이트
    public void updateRecord(HobbyRoutine hobbyRoutine, String recordBody, String photoUrl) {
        this.hobbyRoutine = hobbyRoutine;
        this.recordBody = recordBody;
        this.photoUrl = photoUrl;
    }
}
