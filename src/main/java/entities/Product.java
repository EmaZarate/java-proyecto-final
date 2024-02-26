package entities;

public class Product {
	private int productId;
	private String name;
	private int orderPoint;
	private double salePrice;
	private double purchasePrice;
	private Category category;
	private int categoryId;
	private boolean isActive;
	private int[] supplierIds;
	private int number;
	private int numberSale;
	
	public int getNumberSale() {
		return numberSale;
	}
	public void setNumberSale(int numberSale) {
		this.numberSale = numberSale;
	}
	public int[] getSupplierIds() {
		return supplierIds;
	}
	public void setSupplierIds(int[] supplierIds) {
		this.supplierIds = supplierIds;
	}
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrderPoint() {
		return orderPoint;
	}
	public void setOrderPoint(int orderPoint) {
		this.orderPoint = orderPoint;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		if (productId != other.productId) {
			return false;
		}
		return true;
	}
}
