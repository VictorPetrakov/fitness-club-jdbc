package com.victorp.service.view;


import com.victorp.db.dao.impl.JdbcClientDaoImpl;
import com.victorp.model.Client;
import com.victorp.services.ViewService;
import com.victorp.services.impl.ViewServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import java.util.List;


public class ServiceView {
    private static final Logger LOGGER = LogManager.getLogger(ServiceView.class);

    @Test
    public void viewClients() throws Exception {
        final ViewService viewService = ViewServiceImpl.getInstance();
        final List<Client> clientList = viewService.viewClients();

        LOGGER.info("Clients: {} {} {}",
                clientList.get(1),
                clientList.get(2),
                clientList.get(3));
    }

}
