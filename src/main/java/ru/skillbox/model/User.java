package ru.skillbox.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
//@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "birthday")
    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @ElementCollection
    @CollectionTable(name = "avatars")
    @AttributeOverride(
            name = "fileName",
            column = @Column(name = "file_name", nullable = false)
    )
    private Set<Avatar> avatars = new HashSet<>();

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @ManyToMany
    @JoinTable(
            name = ("users_hard_skills"),
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hard_skill_id")
    )
    private Set<HardSkill> hardSkills = new HashSet<>();

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subscriptions",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id")
    )
    private List<User> subscriptions = new ArrayList<>();

    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.LAZY)
    private List<User> subscribers = new ArrayList<>();

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    public void subscribe(User subscription) {
        this.subscriptions.add(subscription);
        subscription.subscribers.add(this);
    }

    public void unsubscribe(User subscription) {
        this.subscriptions.remove(subscription);
        subscription.subscribers.remove(this);
    }

}
