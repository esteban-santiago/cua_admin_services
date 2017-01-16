package com.cua.admin.model.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@RequiredArgsConstructor
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
    private String passwd;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    
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

        ACTIVE("Activo"), 
        LOCKED("Bloqueado");
        
        Status(String description) {
            this.description = description;
        }        

        private final String description;

}
}
