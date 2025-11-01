package hotel_reservation_system_ddnh;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void updateCustomer(String customerId, String newName, String newEmail, String newPhone) {
        Customer c = findCustomerById(customerId);
        if (c != null) {
            if (!newName.isEmpty()) c.setFullName(newName);
            if (!newEmail.isEmpty()) c.setEmail(newEmail);
            if (!newPhone.isEmpty()) c.setPhoneNumber(newPhone);
            System.out.println("Updated Customer ID: " + customerId);
        }
    }

    public void deleteCustomer(String customerId) {
        Customer c = findCustomerById(customerId);
        if (c != null) {
            customers.remove(c);
            System.out.println("Deleted Customer ID: " + customerId); 
        } else {
            System.out.println("Error: Customer ID not found: " + customerId);
        }
    }

    public Customer findCustomerById(String customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(customerId)) return c;
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public String generateCustomerId() {
        int maxId = 0;
        for (Customer c : customers) {
            try {
                int idNum = Integer.parseInt(c.getCustomerId().substring(3));
                if (idNum > maxId) {
                    maxId = idNum;
                }
            } catch (Exception e) {
                
            }
        }
        return "CUS" + String.format("%03d", maxId + 1);
    }
}

