package sg.nus.iss.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FibonacciResponse {
    
    private List<String> fibonacciList;
    private List<String> sortedFibonacciList;

    @JsonProperty
    public List<String> getFibonacciList() {
        return fibonacciList;
    }
    
    public void setFibonacciList(List<String> fibonacciList) {
        this.fibonacciList = fibonacciList;
    }

    @JsonProperty
    public List<String> getSortedFibonacciList() {
        return sortedFibonacciList;
    }

    public void setSortedFibonacciList(List<String> sortedFibonacciList) {
        this.sortedFibonacciList = sortedFibonacciList;
    }

    public FibonacciResponse(List<String> fibonacciList, List<String> sortedFibonacciList) {
        this.fibonacciList = fibonacciList;
        this.sortedFibonacciList = sortedFibonacciList;
    }
}
