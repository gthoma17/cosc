public class BooksCustomers
{
    //*************************Inner Classes*************************\\
    private class CustomerNode{
        //************************Class Variables*************************\\
        private String name;
        private int copies;
        private CustomerNode next;
        
        //*************************Constructors **************************\\
        private CustomerNode(String name, int copies, CustomerNode next){
            this.name = name;
            this.copies = copies;
            this.next = next;
        }
        
        //************************Private Methods*************************\\
        public String toString(){
            return String.format("Name: %-20s  Copies: %-4d", name, copies);
        }
    }
    
    private class BookNode{
        //************************Class Variables*************************\\
        private String title;
        private double price;
        private int sold;
        private CustomerNode list;
        private BookNode next;
        
        //*************************Constructors **************************\\
        private BookNode(String title, 
						 double price, 
						 int sold, 
						 CustomerNode list, 
						 BookNode next){
            this.title = title;
            this.price = price;
            this.sold = sold;
            this.list = list;
            this.next = next;
        }
        //************************Public Methods *************************\\
        public String toString(){
            String toReturn = String.format("Book: %-20s \n" +
											 "--Price: %-6.2f \n" +
											 "--Sold : %-8d \n" +
											 "--Waiting List:\n"
											 , title, price, sold);
            if(this.list == null){ //Special case, no one is waiting
                toReturn = toReturn + "----<empty>";
            }
            else{
                CustomerNode tmp = list;
                while(tmp != null){
                    toReturn = toReturn + String.format("----" + tmp.toString() + "\n");
                    tmp = tmp.next;
                }
            }
            toReturn = String.format(toReturn + "\n");
            return toReturn;
        }
        //************************Private Methods*************************\\
        private void addCustomer(String name, int copies){
            CustomerNode tmp = list;
            if(tmp == null){ // special case, customer #1
                list = new CustomerNode(name, copies, null);
            }
            else{
                while (tmp.next != null){ //traverse the list
                    tmp = tmp.next;
                }
                tmp.next = new CustomerNode(name, copies, null);
            }
        }
        
        private int removeCustomer(){
            int toReturn = -1;
            if(list != null){ //special case, empty list
                toReturn = list.copies;
                list = list.next;
            }
            return toReturn;
        }
        
        private void cancelCustomer(String name){
            int index = this.find(name);
            CustomerNode tmp = list;
            if (index == 0){
                list = list.next;
            }
            else if(index != -1){
                for(int i = -1; i < index; i++){ //go to the node before       
                    tmp = tmp.next;
                }
                tmp.next = tmp.next.next;
            }
            else{
                System.out.println("Error: Customer not in waiting list");
            }
        }
        
        private int find(String name){
            int index = -1;
            int counter = 0;
            for(CustomerNode tmp = list; tmp != null; tmp = tmp.next){
                if(tmp.name.equals(name)){
                    index = counter;
                    break;
                }
                counter++;
            }
            return index;
        }
    }
    
    //************************Class Variables*************************\\
    
    private BookNode head;
    
    //*************************Constructors **************************\\
    
    public BooksCustomers(){
        head = null;
    }
    
    //************************Public Methods *************************\\
    public void addBook(String title, double price){
        if (this.find(title) == -1){
            head = new BookNode(title, price, 0, null, head);
        }
        else{
            System.out.println("Error: Book already exists in the book list");
        }
    }
    
    public void orderBook(String title, String name, int copies){
        int index = this.find(title);
        if (index != -1){
            BookNode tmp = head;
            for (int i=0;i<index;i++){
                tmp = tmp.next;
            }
            tmp.addCustomer(name, copies);
        }
        else{
            System.out.println("Error: Book does not exist in the book list");
        }
    }
    public void sellBook(String title){
        for(BookNode tmp = head; tmp != null; tmp = tmp.next){
            if (tmp.title.equals(title)){
                tmp.sold = tmp.sold + tmp.removeCustomer();

                return;
            }
        }
        System.out.println("Error: Book not in list");
    }
    public void cancelBook(String title, String name){
        for(BookNode tmpBook = head; tmpBook != null; tmpBook = tmpBook.next){
            if (tmpBook.title.equals(title)){
                int custIndex = tmpBook.find(name);
                if(custIndex != -1){
                    CustomerNode tmpCust = tmpBook.list;
                    if (custIndex == 0){ //special case, customer is next in line
                        tmpBook.list = tmpBook.list.next;
                    }
                    else{
                        
                        for(int i=0;i<custIndex-1;i++){
                            tmpCust = tmpCust.next;
                        }
                        tmpCust.next = tmpCust.next.next;
                        return;
                    }
                }
                else{
                    System.out.println("Error: Customer not in waiting list");
                    return;
                }
            }
        }
        System.out.println("Error: Book not in list");
    }
    public void bookInfo(String title){
        for(BookNode tmp = head; tmp != null; tmp = tmp.next){
            if (tmp.title.equals(title)){
                System.out.print(tmp.toString());
                return;
            }
        }
        System.out.println("Error: Book not in list");
    }
    public void allInfo(){
        System.out.print(this.toString());
    }
    
    public String toString(){
        String toReturn = "";
        if(head == null){ //special case, empty store
            return toReturn;
        }
        else{
            BookNode tmp = head;
            while(tmp.next != null){
                toReturn = toReturn + tmp.toString();
                tmp = tmp.next;
            }
            toReturn = toReturn + tmp.toString();
        }
        return toReturn;
    }
    
    
    //************************Private Methods*************************\\
    private int find(String title){
        int index = -1;
        int counter = 0;
        for(BookNode tmp = head; tmp != null; tmp = tmp.next){
            if (tmp.title.equals(title)){
                index = counter;
                break;
            }
            counter++;
        }
        return index;
    }
 }
