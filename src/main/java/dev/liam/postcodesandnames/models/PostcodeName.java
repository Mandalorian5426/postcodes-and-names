package dev.liam.postcodesandnames.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class PostcodeName {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long id;
    @Getter
    private int postcode;
    @Getter
    private String name;

    public PostcodeName(long id, int postcode, String name) {
        this.id = id;
        this.setPostcode(postcode);
        this.setName(name);
    }

    public void setPostcode(int postcode) {
        if(postcode < 0) {
            throw new IllegalArgumentException("Postcodes are integers greater than or equal to 0");
        }
        this.postcode = postcode;
    }

    public void setName(String name) {
        if(name.trim().equals("")) {
            throw new IllegalArgumentException("Names must contain at least one character");
        }
        this.name = name;
    }

    /**
     * Returns true if the PostcodeName's postcode is within the range provided, else false
     * @param start minimum postcode
     * @param end maximum postcode
     * @return boolean
     */
    public boolean withinPostCodeRange(int start, int end) {
        return this.postcode >= start && this.postcode <= end;
    }
}