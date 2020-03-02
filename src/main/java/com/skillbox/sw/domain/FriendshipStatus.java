package com.skillbox.sw.domain;

import com.skillbox.sw.domain.enums.FriendshipCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate time;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('REQUEST','FRIEND','BLOCKED','DECLINED','SUBSCRIBED','UNBLOCK')")
    private FriendshipCode code;
}
