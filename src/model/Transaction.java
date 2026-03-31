package model;

public class Transaction {


        private int id;
        private int carId;
        private int customerId;
        private double amount;

        public Transaction(int carId, int customerId, double amount) {
            this.carId = carId;
            this.customerId = customerId;
            this.amount = amount;
        }
    }


