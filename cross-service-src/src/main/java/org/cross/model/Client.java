package org.cross.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "full_name")
    private String fullName;
    private String email;
    private Integer age;
    private String country;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Shoes> shoes;
}