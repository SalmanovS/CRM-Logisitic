package com.CRMLogistic.transportservice.Service;

import com.CRMLogistic.transportservice.Model.Transport;

import javax.swing.tree.TreeNode;
import java.util.List;

public interface TransportService {

    void create(Transport transport);

    void delete(int id);

    Transport getTransport(int id);

    List<Transport> all();

    void edit(Transport transport);

    List<Transport> showTransportWithStatus(String status);

    void changeStatus(int id, String newStatus);

    Transport searchForNumber(String number);
}
