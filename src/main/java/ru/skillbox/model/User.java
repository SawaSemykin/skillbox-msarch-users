package ru.skillbox.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "sex")
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
            column = @Column(name = "file_name")
    )
    private Set<Avatar> avatars = new HashSet<>();

    @Column(name = "nick_name")
    private String nickName;

    @ManyToMany
    @JoinTable(
            name = ("users_hard_skills"),
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hard_skill_id")
    )
    private Set<HardSkill> hardSkills = new HashSet<>();

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "subscriptions",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id")
    )
    private Set<User> subscriptions = new HashSet<>();

    @ManyToMany(mappedBy = "subscriptions")
    private Set<User> subscribers = new HashSet<>();

    @Column(name = "deleted")
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
