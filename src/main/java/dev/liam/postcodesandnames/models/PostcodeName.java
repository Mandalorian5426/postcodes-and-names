package dev.liam.postcodesandnames.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PostcodeName {
    @Id
    @GeneratedValue
    private long id;
    private int postcode;
    private String name;

    public boolean withinPostCodeRange(int start, int end) {
        return this.postcode >= start && this.postcode <= end;
    }
}