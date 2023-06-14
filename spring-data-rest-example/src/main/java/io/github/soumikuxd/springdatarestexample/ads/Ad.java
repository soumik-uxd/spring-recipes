package io.github.soumikuxd.springdatarestexample.ads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull(message = "Who is the owner of this ad?")
    @NotBlank(message = "Who is the owner of this ad?")
    public String owner;

    @NotNull(message = "Title for the ad cannot be null.")
    @NotBlank(message = "Title for the ad cannot be blank.")
    @Size(min = 5, max= 500, message = "Titles must be between {min} and {max} characters long.")
    public String title;

    @NotNull(message = "Description of the ad cannot be null.")
    @NotBlank(message = "Description of the ad cannot be blank.")
    @Size(min = 5, max= 500, message = "Descriptions must be between {min} and {max} characters long.")
    public String description;

    @Min(message = "Price cannot be negative", value = 0)
    public BigDecimal price;

    public Ad(String owner, String title, String description, BigDecimal price) {
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ad ad = (Ad) o;
        return id != null && Objects.equals(id, ad.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
