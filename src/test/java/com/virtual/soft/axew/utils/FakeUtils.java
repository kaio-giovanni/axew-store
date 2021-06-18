package com.virtual.soft.axew.utils;

import com.virtual.soft.axew.entity.Address;
import com.virtual.soft.axew.entity.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeUtils {

    public static List<Client> makeClients () {
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
                    .setClient(c);
            c.setAddress(addressFake);

            clients.add(c);
        }

        return clients;
    }

    public static Client makeClient (Long id) {
        Client client = new Client("Fake User " + id, "000.000.000-" + id, "fake.user@email.com" + id)
                .setId(id)
                .setBirthDate(LocalDate.of(2021, 1, 1))
                .setPhone("Cell phone " + id)
                .setPassword("fake pass");
        Address addressFake = new Address("street" + id, "number", "district" + id, "1239879")
                .setClient(client);
        client.setAddress(addressFake);

        return client;
    }
}
