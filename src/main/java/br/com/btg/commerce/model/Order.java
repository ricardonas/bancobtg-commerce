package br.com.btg.commerce.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "\"order\"")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 2366401275317594189L;

    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private Long partyId;

    @Getter(AccessLevel.NONE)
    private State state;

    @Getter(AccessLevel.NONE)
    @Column(columnDefinition = "jsonb")
    @Type(JsonType.class)
    private List<Item> items;

    public BigDecimal getTotalAmount() {
        return this.getItems().stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Item> getItems() {
        return this.items == null ? new ArrayList<>() : this.items;
    }

    public State getState() {
        return this.state == null ? State.PENDING : this.state;
    }

}