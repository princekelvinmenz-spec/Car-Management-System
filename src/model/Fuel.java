package model;

import java.util.Date;

    public class Fuel {
        private int fuelId;
        private int carId;
        private Date date;
        private double litres;
        private double cost;

        public Fuel(int fuelId, int carId, Date date, double litres, double cost) {
            this.fuelId = fuelId;
            this.carId = carId;
            this.date = date;
            this.litres = litres;
            this.cost = cost;
        }

        public int getFuelId() { return fuelId; }
        public int getCarId() { return carId; }
        public Date getDate() { return date; }
        public double getLitres() { return litres; }
        public double getCost() { return cost; }
    }

