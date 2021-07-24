package dev.liam.postcodesandnames.models.dataTransferObjects;

import org.junit.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class NameGetResponseTest {
    @Test
    public void givenAListOfDuplicateNames_whenCreatingAResponseObject_thenDuplicatesRemoved() {
        String[] testNames = {"Dio", "Dio", "Dio"};
        NameGetResponse testNamesGetResponse = new NameGetResponse(() -> Arrays.stream(testNames));

        String[] expectedNames = {"Dio"};
        assertEquals(testNamesGetResponse.getNames().length, expectedNames.length, "List of names should contain all unique names provided");
        assertArrayEquals(testNamesGetResponse.getNames(), expectedNames, "List of names should contain duplicates");
    }

    @Test
    public void givenAListUnsortedNames_whenCreatingAResponseObject_thenListIsSortedAlphabetically() {
        String[] testNames = {"Jonathan", "Dio", "Speedwagon", "Zeppeli", "Straizo"};
        NameGetResponse testNamesGetResponse = new NameGetResponse(() -> Arrays.stream(testNames));

        String[] expectedNames = {"Dio", "Jonathan", "Speedwagon", "Straizo", "Zeppeli"};
        assertEquals(testNamesGetResponse.getNames().length, expectedNames.length, "List of names should contain all unique names provided");
        assertArrayEquals(testNamesGetResponse.getNames(), expectedNames, "List of names should be sorted Alphabetically");
    }
}
