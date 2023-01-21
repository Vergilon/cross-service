package org.cross.model;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cross.api.enums.ShoesType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Data
@Builder
@Entity
@Table(name = "shoes")
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(
    name = "pgsql_enum",
    typeClass = PostgreSQLEnumType.class
)
public class Shoes {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Column(name = "s_type")
    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    private ShoesType shoesType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id", nullable=false)
    Client client;
}
