package com.springboot.videoclub.videoclubapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.videoclub.videoclubapp.model.Customer;
import com.springboot.videoclub.videoclubapp.model.Movie;
import com.springboot.videoclub.videoclubapp.model.RentItem;
import com.springboot.videoclub.videoclubapp.model.RentRequest;
import com.springboot.videoclub.videoclubapp.model.RentResponse;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final List<Movie> inventory = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final int UNIT_PRICE = 3;

    public MovieController() {
        inventory.add(new Movie("1", "Inception", "new_release"));
        inventory.add(new Movie("2", "The Matrix", "regular"));
        inventory.add(new Movie("3", "Casablanca", "old"));

        customers.add(new Customer("c1", "Juan Perez",2));
        customers.add(new Customer("c2", "Maria Lopez",4));
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return inventory;
    }

    @GetMapping("/type/{type}")
    public List<Movie> getMoviesByType(@PathVariable String type) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : inventory) {
            if (movie.getType().equalsIgnoreCase(type)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    @PostMapping("/rent")
    public RentResponse rentMovies(@RequestBody RentRequest request) {
        Customer customer = customers.stream()
                .filter(c -> c.getId().equals(request.getCustomerId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        int totalCost = 0;
        int points = 0;

        for (RentItem item : request.getRentItems()) {
            Movie movie = inventory.stream()
                    .filter(m -> m.getId().equals(item.getMovieId()))
                    .findFirst()
                    .orElse(null);

            if (movie != null) {
                switch (movie.getType()) {
                    case "new_release":
                        totalCost += item.getDays() * UNIT_PRICE;
                        points += 2;
                        break;
                    case "regular":
                        totalCost += UNIT_PRICE * Math.min(3, item.getDays()) +
                                (item.getDays() > 3 ? (item.getDays() - 3) * UNIT_PRICE : 0);
                        points += 1;
                        break;
                    case "old":
                        totalCost += UNIT_PRICE * Math.min(5, item.getDays()) +
                                (item.getDays() > 5 ? (item.getDays() - 5) * UNIT_PRICE : 0);
                        points += 1;
                        break;
                }
            }
        }

        customer.addPoints(points);
        return new RentResponse(totalCost, points);
    }
}