package com.CRMLogistic.transportservice.Service;

import com.CRMLogistic.transportservice.Model.Transport;
import com.CRMLogistic.transportservice.Repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportImpl implements TransportService {

    @Autowired
    TransportRepository transportRepository;

    @Override
    public void create(Transport transport) {
        transportRepository.save(transport);
    }

    @Override
    public void delete(int id) {
        transportRepository.deleteById(id);
    }

    @Override
    public Transport getTransport(int id) {
        return transportRepository.getReferenceById(id);
    }

    @Override
    public List<Transport> all() {
        return transportRepository.findAll();
    }

    @Override
    public void edit(Transport transport) {
        transportRepository.save(transport);
    }

    @Override
    public List<Transport> showTransportWithStatus(String status) {
        return transportRepository.findByStatus(status);
    }

    @Override
    public void changeStatus(int id, String newStatus) {
        Transport transport = transportRepository.getReferenceById(id);
        transport.setStatus(newStatus);
        transportRepository.save(transport);
    }

    @Override
    public Transport searchForNumber(String number) {
        return transportRepository.findByNumber(number);
    }
}
