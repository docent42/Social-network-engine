package com.skillbox.sw.domain;

import com.skillbox.sw.api.response.MessageApi;
import com.skillbox.sw.domain.enums.ReadStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Data
@Entity
@Table(name = "dialog")
@AllArgsConstructor
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = false)
    private Integer id;

    @Column(name = "unread_count")
    private Integer unreadCount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Person owner;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "invite_code")
    private String inviteCode;

    @OneToMany(mappedBy = "dialog", fetch = FetchType.LAZY)
    private List<Message> messages;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "person2dialog",
            joinColumns = {@JoinColumn(name = "dialog_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")})
    private List<Person> recipients;

    @OneToOne
    @JoinColumn(name = "last_message")
    private Message lastMessage;

    public Dialog() {
        byte[] array = new byte[20];
        new Random().nextBytes(array);
        this.inviteCode = UUID.nameUUIDFromBytes(array).toString();
     }
}
