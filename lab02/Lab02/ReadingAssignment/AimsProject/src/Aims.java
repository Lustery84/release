public class Aims {
	public static void main(String[] args) {
	    // Tạo giỏ hàng mới
	    Cart anOrder = new Cart();
	    
	    // Tạo các đối tượng DVD và thêm vào giỏ hàng
	    DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", 
	        "Animation", "Roger Allers", 87, 19.95f);
	    anOrder.addDigitalVideoDisc(dvd1);
	    
	    DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", 
	        "Science Fiction", "George Lucas", 87, 24.95f);
	    anOrder.addDigitalVideoDisc(dvd2);
	    
	    DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
	        "Animation", 18.99f);
	    anOrder.addDigitalVideoDisc(dvd3);
	    
	    // Hiển thị tổng chi phí
	    System.out.println("Total Cost is: " + anOrder.totalCost());
	    
	 // Test xóa DVD
        System.out.println("\nTesting removal:");
        anOrder.removeDigitalVideoDisc(dvd2); // Xóa Star Wars
        System.out.println("Updated total cost: " + anOrder.totalCost());
 
        // Test nạp chồng phương thức
        System.out.println("--- Test thêm từng DVD ---");
        anOrder.addDigitalVideoDisc(dvd1); // Gọi phương thức gốc
        
        System.out.println("\n--- Test thêm 2 DVD cùng lúc ---");
        anOrder.addDigitalVideoDisc(dvd1, dvd2); // Gọi phương thức 14.2
        
        System.out.println("\n--- Test thêm mảng DVD ---");
        DigitalVideoDisc[] dvdArray = {
            new DigitalVideoDisc("Aladin", "Animation", 18.99f),
            new DigitalVideoDisc("Inception", "Sci-Fi", 20.50f)
        };
        anOrder.addDigitalVideoDisc(dvdArray); // Gọi phương thức 14.1
	}

}
