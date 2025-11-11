package com.taxist.JibMeakV2;

import com.taxist.JibMeakV2.model.Customer;
import com.taxist.JibMeakV2.model.Delivery;
import com.taxist.JibMeakV2.model.Vehicle;
import com.taxist.JibMeakV2.model.Warehouse;
import com.taxist.JibMeakV2.model.enums.VehicleType;
import com.taxist.JibMeakV2.repository.CustomerRepository;
import com.taxist.JibMeakV2.repository.DeliveryRepository;
import com.taxist.JibMeakV2.repository.VehicleRepository;
import com.taxist.JibMeakV2.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class TestDataFactory {
    @Autowired
    private WarehouseRepository warehouseRepo;
    @Autowired private VehicleRepository vehicleRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private DeliveryRepository deliveryRepo;

    public Warehouse createAndSaveWarehouse() {
        Warehouse w = new Warehouse();
        w.setAddress("123 Factory St");
        w.setLatitude(88.345);
        w.setLongitude(30.31);
        w.setOpenHour(LocalTime.of(8, 0));
        w.setCloseHour(LocalTime.of(17, 00));
        return warehouseRepo.save(w);
    }

    public Vehicle createAndSaveVehicle() {
        Vehicle v = new Vehicle();
        v.setType(VehicleType.VAN);
        v.setMaxWeightKg(123.33);
        v.setMaxVolumeM3(12);
        v.setMaxDeliveries(15);
        return vehicleRepo.save(v);
    }

    public Customer createAndSaveCustomer(String name, String address) {
        Customer c = new Customer();
        c.setName(name);
        c.setAddress(address);
        c.setLatitude(12.223);
        c.setLongitude(29.333);
        return customerRepo.save(c);
    }

    public Delivery createAndSaveDelivery(Customer customer, LocalDate date) {
        Delivery d = new Delivery();
        d.setCustomer(customer);
        d.setDeliveryDate(date);
        d.setWeightKg(10);
        d.setVolumeM3(1.2);
        d.setPreferredWindowStart(LocalTime.of(10, 30));
        d.setPreferredWindowEnd(LocalTime.of(12, 30));
        return deliveryRepo.save(d);
    }

    public void cleanDatabase() {
        deliveryRepo.deleteAll();
        customerRepo.deleteAll();
        vehicleRepo.deleteAll();
        warehouseRepo.deleteAll();
    }
}
