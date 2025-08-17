import java.util.*;

class Customer{
    int id;
    String name;
    List<Cart> cart;
    public Customer(int id, String name){
        this.id=id;
        this.name=name;
        this.cart = new ArrayList<>();
    }
}

class Cart{
    int id;
    List<Product> products;
    public Cart(int id, List<Product> products){
        this.id=id;
        this.products=products;
    }
}
class Product{
    int id;
    String name;
    double prize;
    int quantity;
    public Product(int id, String name, double prize, int quantity){
        this.id=id;
        this.name=name;
        this.prize=prize;
        this.quantity=quantity;
    }
    @Override
    public String toString() {
        return "Product Details [id=" + id + ", name=" + name + ", prize=" + prize + ", quantity=" + quantity + "]";
    }
    
}

public class Ecomerce {
    public static Map<String, Product> map; 
    public void productInit(){
        System.out.println("Products are listed");
        map=new HashMap<>();
        List<Product> products = Arrays.asList(
                new Product(1, "Laptop", 100000, 3),
                new Product(2, "Mobile", 20000, 2),
                new Product(3, "AC", 30000, 1),
                new Product(4, "Fridge", 40000, 4)
        );
        for(Product p:products){
            map.put(p.name, p);
        }
    }
    public void createUser(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Your name : ");
        String name = sc.nextLine();
        new Customer(1, name);
    }
    public Ecomerce(){
        productInit();
        createUser();
    }
    public static int disPlayAllActions(Scanner scanner){
        System.out.println("Enter 1 for display products details");
        System.out.println("Enter 2 for add to cart for products");
        System.out.println("Enter 3 for show all products in your cart");
        System.out.println("Enter 4 for terminate project");
        System.out.print("Select number: ");
        int n = scanner.nextInt();
        return n;
    }
    public static void displayProducts(){
        for(String str:map.keySet()){
            System.out.println(str+" "+map.get(str).toString());
        }
         System.out.println();
    }
    public static void main(String[] args) {
        new Ecomerce();
        boolean isFirst = true;
        Scanner scanner = new Scanner(System.in);
        while(true){
            if(isFirst){
                displayProducts();
                isFirst=false;
            }
            int option = disPlayAllActions(scanner);
            switch (option) {
                case 1: displayProducts();
                        break;
                case 2:
                    break;

                case 3:
                    
                    break;
                case 4:
                    System.out.println("Terminated the project");
                    return;
                default:
                    System.out.println("Please Enter correct option");
                    break;
            }
        }
    }
}
