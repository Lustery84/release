public class DigitalVideoDisc {
	 // Class member/static variable - Đếm tổng số DVD được tạo
    private static int nbDigitalVideoDiscs = 0;
    
    // Instance member - ID riêng cho mỗi DVD
    private int id;
	
 // Thuộc tính (fields)
    private String title;       // Tiêu đề DVD
    private String category;    // Thể loại (ví dụ: Animation, Sci-Fi)
    private String director;    // Đạo diễn
    private int length;         // Độ dài (phút)
    private float cost;         // Giá thành (USD)
	public String getTitle() {
		return title;
	}
	public String getCategory() {
		return category;
	}
	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}
	public float getCost() {
		return cost;
	}
	// Constructor chỉ với title
	public DigitalVideoDisc(String title) {
		this.id = ++nbDigitalVideoDiscs;
		this.title = title;
	}

	// Constructor với title, category và cost
	public DigitalVideoDisc(String title, String category, float cost) {
		this.id = ++nbDigitalVideoDiscs;
		this.title = title;
	    this.category = category;
	    this.cost = cost;
	}

	// Constructor với director, category, title và cost
	public DigitalVideoDisc(String title, String category, String director, float cost) {
		this.id = ++nbDigitalVideoDiscs;
		this.title = title;
	    this.category = category;
	    this.director = director;
	    this.cost = cost;
	}

	// Constructor đầy đủ
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		this.id = ++nbDigitalVideoDiscs;
		this.title = title;
	    this.category = category;
	    this.director = director;
	    this.length = length;
	    this.cost = cost;
	}
	public void setTitle(String title) {
        this.title = title;
    }
	public int getId() {
        return id;
    }
	public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }
	
}

