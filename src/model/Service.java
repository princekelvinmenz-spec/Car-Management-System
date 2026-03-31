package model;

import java.util.Date;

    public class Service {
        private int serviceId;
        private int carId;
        private Date serviceDate;
        private String description;
        private String status;

        public Service(int serviceId, int carId, Date serviceDate, String description, String status) {
            this.serviceId = serviceId;
            this.carId = carId;
            this.serviceDate = serviceDate;
            this.description = description;
            this.status = status;
        }

        public int getServiceId() { return serviceId; }
        public int getCarId() { return carId; }
        public Date getServiceDate() { return serviceDate; }
        public String getDescription() { return description; }
        public String getStatus() { return status; }

        public void setStatus(String status) { this.status = status; }
    }

