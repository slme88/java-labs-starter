package edu.course.lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataSampleTest {

    @Test
    void testValidSampleCreation() {
        double[] features = {1.0, 2.0, 3.0};
        DataSample sample = new DataSample("sample-01", "cat", SampleStatus.RAW, features);

        assertEquals("sample-01", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.RAW, sample.getStatus());
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, sample.getFeatures());
    }

    @Test
    void testInvalidIdThrowsException() {
        double[] features = {1.0, 2.0};
        assertThrows(IllegalArgumentException.class, () -> {
            new DataSample("", "cat", SampleStatus.RAW, features);
        });
    }

    @Test
    void testCalculateMean() {
        double[] features = {2.0, 4.0, 6.0};
        DataSample sample = new DataSample("sample-02", "dog", SampleStatus.PROCESSED, features);
        
        assertEquals(4.0, sample.calculateMean(), 0.001);
    }

    @Test
    void testEncapsulationOfFeatures() {
        double[] features = {1.0, 2.0};
        DataSample sample = new DataSample("sample-03", "bird", SampleStatus.RAW, features);

        double[] retrieved = sample.getFeatures();
        retrieved[0] = 99.9;

        assertEquals(1.0, sample.getFeatures()[0], 0.001);
    }
}