package sg.nus.iss.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FibonacciRequest {
    @Min(1)
    @Max(100)
    private int elements;

    @JsonProperty
    public int getElements() {
        return elements;
    }

    public void setElements(int elements) {
        this.elements = elements;
    }

    public FibonacciRequest(int elements) {
        this.elements = elements;
    }
}
