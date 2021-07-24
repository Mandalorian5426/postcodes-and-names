package dev.liam.postcodesandnames.models;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PostcodeNameTest {
    @Test (expected = IllegalArgumentException.class)
    public void givenANegativePostcode_whenCreatingAPostcodeName_thenThrowIllegalArgumentException() {
        new PostcodeName(1, -1, "Steve");
    }

    @Test (expected = IllegalArgumentException.class)
    public void givenAnEmptyNameString_whenCreatingAPostcodeName_thenThrowIllegalArgumentException() {
        new PostcodeName(2, 10, "");
    }

    @Test
    public void givenAPostcodeWithinARange_whenAssertingIfItIsBetweenTheRange_thenShouldReturnTrue() {
        PostcodeName testPostcodeName = new PostcodeName(3, 10, "Fred");
        assertTrue(testPostcodeName.withinPostCodeRange(0, 20), "A postcodeName within the specified postcode range should return true");
    }

    @Test
    public void givenAPostcodeEqualToTheMaxOfARange_whenAssertingIfItIsBetweenTheRange_thenShouldReturnTrue() {
        PostcodeName testPostcodeName = new PostcodeName(4, 10, "Bob");
        assertTrue(testPostcodeName.withinPostCodeRange(0, 10), "A postcodeName with a postcode equal to the max of the postcode range should return true");
    }

    @Test
    public void givenAPostcodeEqualToTheMinOfARange_whenAssertingIfItIsBetweenTheRange_thenShouldReturnTrue() {
        PostcodeName testPostcodeName = new PostcodeName(5, 0, "Anne");
        assertTrue(testPostcodeName.withinPostCodeRange(0, 10), "A postcodeName with a postcode equal to the max of the postcode range should return true");
    }

    @Test
    public void givenAPostcodeNotWithinARange_whenAssertingIfItIsBetweenTheRange_thenShouldReturnFalse() {
        PostcodeName testPostcodeName = new PostcodeName(6, 10, "Mary");
        assertFalse(testPostcodeName.withinPostCodeRange(0, 5), "A postcodeName not within the specified postcode range should return false");
    }
}
