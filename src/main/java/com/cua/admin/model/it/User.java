package com.cua.admin.model.it;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "password")
@JsonIgnoreProperties("password")
public class User implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "users_id_seq"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    //@JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Enumerated(EnumType.STRING)
    private final Profile profile = Profile.USER;

    
    public void lock() {
        this.status = Status.LOCKED;
    }
    
    public void unLock() {
        this.status = Status.ACTIVE;
    }

    public Boolean isLocked() {
        return status == Status.LOCKED;
    }
    
    public enum Status {
        ACTIVE(), 
        LOCKED();
    }
    
    public enum Profile {
        USER(),
        ADMINISTRATOR(),
        SUPER_USER();
    }
}
