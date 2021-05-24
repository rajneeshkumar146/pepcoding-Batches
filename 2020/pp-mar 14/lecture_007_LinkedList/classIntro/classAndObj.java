public class classAndObj {

    public static class phone {
        String brand;
        String model;
        String color;
        int ram;
        int storage;
        int battery;

        phone(String brand, String model, String color, int ram, int storage, int battery) {
            this.brand = brand;
            this.model = model;
            this.color = color;
            this.ram = ram;
            this.storage = storage;
            this.battery = battery;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Brand: " + this.brand + "\n");
            sb.append("Model: " + this.model + "\n");
            sb.append("Color: " + this.color + "\n");
            sb.append("Ram: " + this.ram + "GB\n");
            sb.append("Storage: " + this.storage + "GB\n");
            sb.append("Battery: " + this.battery + "mAH\n");
            sb.append("\n");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        phone ph1 = new phone("one Plus", "9r 5G", "Lake Blue", 8, 128, 4500);
        phone ph2 = new phone("one Plus", "9 Pro", "Lake Blue", 12, 256, 4500);

        // phone[] arr = new phone[10000000];
        // for (int i = 0; i < 10000000; i++) {
        //     String brand;
        //     String model;
        //     String color;
        //     int ram;
        //     int storage;
        //     int battery;
        //     arr[i] = new phone(brand, model, color, ram, storage, battery);
        // }

        System.out.println(ph2);
    }
}