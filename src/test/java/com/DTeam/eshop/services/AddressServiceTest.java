package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.entities.Customer;

import java.util.List;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.hamcrest.Matchers;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class AddressServiceTest {

    @Test
    public void listAll() {
        //given
        AddressService addressService = mock(AddressService.class);

        //when
        when(addressService.listAll()).thenReturn(prepareMocData());

        //then
        Assert.assertEquals(addressService.listAll().get(0).getCity(), "Rzeszow");
        Assert.assertThat(addressService.listAll(), Matchers.hasSize(2));

    }

    private List<Address> prepareMocData() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1L, "Rzeszow", 110, "38-111"));
        addresses.add(new Address(2L, "Strzyzow", 380, "41-350"));

        return addresses;
    }

    @Test
    public void save() {
        //given

        AddressService addressService = mock(AddressService.class);

        //when
        when(addressService.save(Mockito.any(Address.class))).thenReturn(new Address("Miasto", 32, "31-523"));
        Address address = addressService.save(new Address());

        //then
        Assert.assertEquals(address.getCity(), "Miasto");
        Assert.assertEquals(address.getHouseNumber().toString(), "" + 32);
        Assert.assertEquals(address.getPostcode(), "31-523");

    }

    @Test
    public void get() {

        Long id = 1L;
        //given
        AddressService addressService = mock(AddressService.class);

        //when
        when(addressService.get(id)).thenReturn(new Address(1L, "Miasto", 997, "36-156"));

        //then
        Assert.assertEquals(addressService.get(id).getAddressId().longValue(), 1L);
        Assert.assertEquals(addressService.get(id).getCity(), "Miasto");
    }

    @Test
    public void delete() {

        Long id = 1L;
        final Address address = new Address(id, "Miasto", 997, "36-156");

        //given
        AddressService addressService = mock(AddressService.class);

        //when
        addressService.delete(address.getAddressId());

        //then
        verify(addressService, times(1)).delete(id);

    }

    @Test
    public void isAddressExist() {

        //given
        AddressService addressService = mock(AddressService.class);

        //when
        when(addressService.isAddressExist(1L)).thenReturn(true);

        //then
        final boolean result = addressService.isAddressExist(1L);
        assertEquals(result, true);
    }

    @Test
    public void getByCustomerId() {

        Long id = 1L;
        final Address address = new Address(123L, "Miasto", 997, "36-156");
        final Customer customer = new Customer(70L, "Imie", "Nazwisko", "732737999");

        //given
        AddressService addressService = mock(AddressService.class);
        CustomerService customerService = mock(CustomerService.class);

        //when
        when(addressService.getByCustomerId(id)).thenReturn(address);
        when(customerService.get(id)).thenReturn(customer);
        when(addressService.get(id)).thenReturn(address);

        customer.setAddress(address);

        //then
        assertEquals(customer.getAddress().getAddressId(), address.getAddressId());
        assertEquals(customer.getAddress().getAddressId().longValue(), 123L);

        assertEquals(addressService.getByCustomerId(id).getAddressId().longValue(), 123L);
        assertEquals(customerService.get(id).getCustomerId().longValue(), 70L);

        assertEquals(customerService.get(id).getName(), "Imie");
        assertEquals(addressService.get(id).getCity(), "Miasto");
    }

    @Test
    public void getByEmployeeId() {
        Long id = 1L;
        final Address address = new Address(id, "Miasto", 997, "36-156");

        //given
        AddressService addressService = mock(AddressService.class);
        EmployeeService employeeService = mock(EmployeeService.class);

        //when
        when(addressService.getByCustomerId(id)).thenReturn(address);

        //then
        Assert.assertEquals(addressService.getByCustomerId(id).getAddressId().longValue(), 1L);

    }
}
