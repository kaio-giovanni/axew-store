package com.virtual.soft.axew.utils;

import com.virtual.soft.axew.entity.Address;
import com.virtual.soft.axew.entity.Category;
import com.virtual.soft.axew.entity.Client;
import com.virtual.soft.axew.entity.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeUtils {

    public static List<Client> makeClients() {
        List<Client> clients = new ArrayList<>();

        final String userName = "userFake";
        final String userPass = "1234567890";
        LocalDate dateFake = LocalDate.of(2021, 1, 1);

        for (long id = 0; id < 10; id++) {
            Client c = new Client(userName + id, "000.000.000-" + id, "fake.user@email.com" + id)
                    .setBirthDate(dateFake)
                    .setPhone("69 90000-0000" + id)
                    .setPassword(userPass);
            Address addressFake = new Address("street" + id, "number", "district" + id, "1239879")
                    .addClient(c);
            c.setAddress(addressFake);

            clients.add(c);
        }

        return clients;
    }

    public static Client makeClient(Long id) {
        Client client = new Client("Fake User " + id, "000.000.000-" + id, "fake.user@email.com" + id)
                .setId(id)
                .setBirthDate(LocalDate.of(2021, 1, 1))
                .setPhone("Cell phone " + id)
                .setPassword("fake pass");
        Address addressFake = new Address("street" + id, "number", "district" + id, "1239879")
                .addClient(client);
        client.setAddress(addressFake);

        return client;
    }

    public static Address makeAddress() {
        return new Address("Street One", "Number one", "District one", "zipCode one");
    }

    public static List<Category> makeCategories() {
        List<Category> categories = new ArrayList<>();
        for (long i=0; i<5; i++) {
            categories.add(new Category(i, "Name " + i));
        }

        return categories;
    }

    public static List<Product> makeProducts() {
        List<Product> products = new ArrayList<>();
        for (int i =0; i < 5; i++) {
            products.add(new Product("Product " + i,
                    "Description of product " + i,
                    i * 10.0)
                    .setImgUrl("https://img/url"));
        }
        return products;
    }
}
