package service.hobbyservice.entity;

import jakarta.persistence.*;
import lombok.*;
import service.hobbyservice.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "HobbyRoutine")
public class HobbyRoutine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_routine_id")
    private Long id;

    @Column(name =  "hobby_name")
    private String hobbyName;
//    private String explanation;
    private Long userId;
    private String imageUrl;

    @OneToMany(mappedBy = "hobbyRoutine", cascade = CascadeType.ALL)
    private List<HobbyRecord> hobbyRecordList = new ArrayList<>();

    @Builder
    public HobbyRoutine(String hobbyName, String explanation, Long userId, String imageUrl) {
        this.hobbyName = hobbyName;
//        this.explanation = explanation;
        this.userId = userId;
        this.imageUrl = imageUrl;
    }

    public void updateHobbyRoutine(String hobbyName, String imageUrl){
        this.hobbyName = hobbyName;
        this.imageUrl = imageUrl;
    }
}