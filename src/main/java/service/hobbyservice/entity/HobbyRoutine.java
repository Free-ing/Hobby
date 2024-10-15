package service.hobbyservice.entity;

import jakarta.persistence.*;
import lombok.*;
import service.hobbyservice.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HobbyRoutine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name =  "hobby_name")
    private String hobbyName;
    private String explanation;
    private Long userId;
    private Boolean status;

    @OneToMany(mappedBy = "hobbyRoutine", cascade = CascadeType.ALL)
    private List<HobbyRecord> hobbyRecordList = new ArrayList<>();

    @Builder
    public void setHobbyRoutine(String hobbyName, String explanation, Long userId, Boolean status) {
        this.hobbyName = hobbyName;
        this.explanation = explanation;
        this.userId = userId;
        this.status = status;
    }
}