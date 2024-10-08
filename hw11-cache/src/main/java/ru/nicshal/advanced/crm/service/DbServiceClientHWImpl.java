package ru.nicshal.advanced.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.core.repository.DataTemplateHW;
import ru.nicshal.advanced.core.sessionmanager.TransactionRunner;
import ru.nicshal.advanced.crm.model.Client;

import java.util.List;
import java.util.Optional;

public class DbServiceClientHWImpl implements DBServiceClient {

    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

    private final DataTemplateHW<Client> dataTemplate;
    private final TransactionRunner transactionRunner;

    public DbServiceClientHWImpl(TransactionRunner transactionRunner, DataTemplateHW<Client> dataTemplate) {
        this.transactionRunner = transactionRunner;
        this.dataTemplate = dataTemplate;
    }

    @Override
    public Client saveClient(Client client) {
        return transactionRunner.doInTransaction(connection -> {
            if (client.getId() == null) {
                var clientId = dataTemplate.insert(connection, client);
                var createdClient = new Client(clientId, client.getName());
                log.info("created client: {}", createdClient);
                return createdClient;
            }
            dataTemplate.update(connection, client);
            log.info("updated client: {}", client);
            return client;
        });
    }

    @Override
    public Optional<Client> getClient(long id) {
        return transactionRunner.doInTransaction(connection -> {
            var clientOptional = dataTemplate.findById(connection, id);
            log.info("client: {}", clientOptional);
            return clientOptional;
        });
    }

    @Override
    public List<Client> findAll() {
        return transactionRunner.doInTransaction(connection -> {
            var clientList = dataTemplate.findAll(connection);
            log.info("clientList:{}", clientList);
            return clientList;
        });
    }
}
