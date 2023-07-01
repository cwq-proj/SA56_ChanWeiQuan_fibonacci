package sg.nus.iss.resources;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sg.nus.iss.api.FibonacciResponse;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FibonacciResource {

    @GET
    @Path("/fibonacci")
    public Response calculateFibonacciResponse(@QueryParam("elements") int numElements) {
        try {
            if (numElements > 100 || numElements < 0) {
                throw new IllegalArgumentException("Invalid number provided.");
            }

            List<String> fibList = fibonacciList(numElements);
            List<String> sortedList = sortedFibonacci(fibList);

            FibonacciResponse response = new FibonacciResponse(fibList, sortedList);
            return Response.ok(response).build();
        } catch (Exception e) {
            String errorMessage = "Invalid number provided.";
            return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
        }
    }

    // Returns a list of Fibonacci numbers
    public static List<String> fibonacciList(int elements) {
        List<String> fibonacciStrings = new ArrayList<>(elements);

        BigInteger num1 = BigInteger.ZERO;
        BigInteger num2 = BigInteger.ONE;

        fibonacciStrings.add(num1.toString());
        if (elements > 1) {
            fibonacciStrings.add(num2.toString());
        }

        for (int i = 2; i < elements; i++) {
            BigInteger nextNum = num1.add(num2);
            fibonacciStrings.add(nextNum.toString());
            num1 = num2;
            num2 = nextNum;
        }

        return fibonacciStrings;
    }

    // Returns a list of sorted Fibonacci numbers with even numbers sorted in
    // descending order,
    // followed by odd numbers in descending order.
    //
    // Note: Due to the limitation of representing extremely large Fibonacci numbers
    // as strings,
    // the last few digits of the Fibonacci numbers might be rounded up in this list
    // of strings.
    public static List<String> sortedFibonacci(List<String> fibList) {

        // Convert the list to BigInteger and sort in descending order
        List<BigInteger> bigIntList = fibList.stream()
                .map(BigInteger::new)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        // get lists of even and odd numbers
        List<BigInteger> evenList = bigIntList.stream()
                .filter(x -> x.mod(BigInteger.TWO).equals(BigInteger.ZERO))
                .collect(Collectors.toList());

        List<BigInteger> oddList = bigIntList.stream()
                .filter(x -> x.mod(BigInteger.TWO).equals(BigInteger.ONE))
                .collect(Collectors.toList());

        List<BigInteger> sortedFibonacci = Stream.concat(evenList.stream(), oddList.stream())
                .collect(Collectors.toList());

        // return list of strings to avoid rounding errors for large numbers
        return sortedFibonacci.stream()
                .map(BigInteger::toString)
                .collect(Collectors.toList());
    }
}