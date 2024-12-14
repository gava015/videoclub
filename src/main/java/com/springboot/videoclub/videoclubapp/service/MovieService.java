package com.springboot.videoclub.videoclubapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.videoclub.videoclubapp.model.Customer;
import com.springboot.videoclub.videoclubapp.model.Movie;

import lombok.EqualsAndHashCode;


@Service
public class MovieService {
    private final List<Movie> movies = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();

    public MovieService() {

        movies.add(new Movie("New Release 1", "new", "HORROR"));
        movies.add(new Movie("Normal Movie 1", "normal", "STANDAR"));

        customers.add(new Customer(1, "John Doe", 3));
        customers.add(new Customer(2, "Jane Smith", 2));
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public List<Movie> getMoviesByType(String type) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getType().equalsIgnoreCase(type)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public double calculateRentalPrice(List<String> movieTitles, int days, int customerId) {
        double totalPrice = 0;
        Customer customer = findCustomerById(customerId);

        for (String title : movieTitles) {
            Movie movie = movies.stream()
                                .filter(m -> m.getTitle().equalsIgnoreCase(title))
                                .findFirst()
                                .orElse(null);
            
           
            if (movie != null) {
                switch (movie.getType()) {
                    case "new":
                        totalPrice += days * 3; 
                        customer.addPoints(2); 
                        break;
                    case "normal":
                        if (days <= 3) {
                        } else {
                            totalPrice += 3 * 3 + (days - 3) * 4; 
                        }
                        customer.addPoints(1); 
                        break;
                    case "old":
                        if (days <= 5) {
                            totalPrice += 3 * days; 
                        } else {
                            totalPrice += 3 * 5 + (days - 5) * 4; 
                        }
                        customer.addPoints(1); 
                        throw new IllegalArgumentException("Unknown movie type");
                }
            } else {
                throw new IllegalArgumentException("Movie not found: " + title);
            }
        }return totalPrice;

    }


    private Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        throw new IllegalArgumentException("Customer not found");
    }

    public int getLoyaltyPoints(int customerId) {
        Customer customer = findCustomerById(customerId);
        return customer.getLoyaltyPoints();
    }
}