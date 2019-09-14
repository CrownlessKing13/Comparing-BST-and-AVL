// Java program for insertion in AVL Tree 
/**
* PowerAVLApp.java - class is for reading csv file and storing it in AVL format then with search method it finds specific date from data that is stored.
* @author ASLBIL001
* @version 1.0
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;


 public class PowerAVLApp{
  
public static class AVLTree { 
    int Count = 0;
    int Count2 = 0;
  
   public static class Node { 
    public String key; 
    public int height; 
    public Node left, right; 
  
  /*
  * Node default constructor 
  * @parameter d in type of String
  */
  
    public Node(String d) { 
        key = d; 
        height = 1; 
    } 
}
  
    Node root; 

  /*
  * Equavates Count Variables to Zero
  */
  
  public void zeroCount(){
  Count = 0;
  Count2 = 0;
   }
   
  /*
  * A utility function to get the height of the tree
  * @parameter N in type of Node
  * @return a int type
  */
   
    public int height(Node N) { 
        if (N == null) 
            return 0; 
  
        return N.height; 
    } 
  
  /*
  * A utility function to get maximum of two integers 
  * @parameter a, b in type of Integer
  * @return a int type
  */
    
    public int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 
   
  /*
  * A utility function to right rotate subtree rooted with y 
  * @parameter y in type of Node
  */
    
    Node rightRotate(Node y) { 
        Node x = y.left; 
        Node T2 = x.right; 
  
        // Perform rotation 
        x.right = y; 
        y.left = T2; 
  
        // Update heights 
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
  
        // Return new root 
        return x; 
    } 
    
   /*
  * A utility function to left rotate subtree rooted with x 
  * @parameter x in type of Node
  */
   
    Node leftRotate(Node x) { 
        Node y = x.right; 
        Node T2 = y.left; 
  
        // Perform rotation 
        y.left = x; 
        x.right = T2; 
  
        //  Update heights 
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 
  
        // Return new root 
        return y; 
    } 
   
    /*
  * Get Balance factor of node N 
  * @parameter N in type of Node
  * @return a int type
  */
  
    int getBalance(Node N) { 
        if (N == null) 
            return 0; 
  
        return height(N.left) - height(N.right); 
    } 
    
   /*
  * this method calls insert(root, key) mainly
  * @parameter key in type of String 
  */
  
  public  void insert(String key) {
      
      
      root =  insert(root, key); 
    } 
    
    /*
  * this method is inserts Data to tree and then balances the tree
  * @parameter root in type of Node
  * @parameter key in type of String
  */
  
         Node insert(Node root, String key) { 
         

        /* 1.  Perform the normal BST insertion */
         if (root == null) { 
            root = new Node(key); 
            return root; 
        } 
  
        /* Otherwise, recur down the tree */
        int k = key.compareTo(root.key);
        Count++;
        
        if (k < 0) 
            root.left = insert(root.left, key); 
        else if (k > 0) 
            root.right = insert(root.right, key); 
  
        /* return the (unchanged) node pointer */
        else
            return root; 
  
        /* 2. Update height of this ancestor node */
        root.height = 1 + max(height(root.left), height(root.right)); 
  
        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(root);
         
  
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1){
            Count++;
            if(key.compareTo(root.left.key)<0){ 
            return rightRotate(root); 
            }}
            
        // Right Right Case 
        if (balance < -1){Count++; if(key.compareTo(root.right.key)>0){ 
            return leftRotate(root); }}
            
            
        // Left Right Case 
        if (balance > 1){Count++; if(key.compareTo(root.left.key)>0) { 
            root.left = leftRotate(root.left); 
            return rightRotate(root); }}
        
            
            
        // Right Left Case 
        if (balance < -1){Count++; if(key.compareTo(root.right.key)<0) { 
            root.right = rightRotate(root.right); 
            return leftRotate(root); }
        }
 
  
        /* return the (unchanged) node pointer */
        return root; 
    } 
      // A utility function to search a given key in BST
     
 /*
  * Mainly calls search(Node root, String key) method 
  * @parameter key in type of String
  * @return a Node type
  */
  
     public  Node search(String key){
     Count2 = 0;     
      return search(root, key);

}

   /*
  * Searches through Tree and finds given String Parameter
  * @parameter root in type of Node
  * @parameter key in type of String
  * @return a Node type
  */
  
    public  Node search(Node root, String key)
{     

    // Base Cases: root is null or key is present at root
    if(root==null){
         return null;}
    int k = key.compareTo(root.key.split(",")[0]);
    Count2++;
    
    if (k==0)
        
        return root;  
    // val is greater than root's key
    if (k<0)
        return search(root.left, key);
  
    // val is less than root's key
    return search(root.right, key);
    
    
  
} 

     /*
  * mainly calls inorderRec(Node root);
  */
    
    void inOrder()  {
     inorderRec(root);
  }
   
    /*
  * A utility function to do inorder traversal of AVL
  * @parameter root in type of Node
  */
   
  void inorderRec(Node root) {
      if (root != null) {
          inorderRec(root.left);
         
          System.out.println(root.key);
           
          inorderRec(root.right);
          
      } 
  }
  
  /*
  * Writes number of comparisons made to a file
  */
  
  void writeToFile(){
   // writes number of comparisons made to a txt file 
      
  try{
  FileWriter fwrite = new FileWriter("countAVL.txt",true);
  fwrite.write(Count+" "+Count2+"\n");
  fwrite.close();
  
  
  }catch(Exception e){
  System.out.println(e.getMessage());
  }
  
  
  }
  
  
   /*
  * Adds insert and Search Count numbers
  * @return a int type
  */
  
    int getTotalCount(){
     return Count+Count2;
     
     }
    
    
     
    
    
 }
   /*
  * This is used for testing efficiency of the AVL Tree
  */ 
    static void testing(){
    String[] data = new String[500];
    int[][] countins = new int[500][500];
    int[][] countser = new int[500][500];
    String[][] need = new String[500][3];
    int f= 0;
    try {Scanner sc2 = new Scanner(new File("FileWithSearchKeys.txt"));
    
    while(sc2.hasNextLine()){
         String[]row = sc2.nextLine().split(",");
                 data[f] = row[0];
                 f++;
                                
      }
      sc2.close();

      }catch(Exception e){
  System.out.println(e.getMessage());
  }   
      
         AVLTree avl2 = new AVLTree();
      for(int i=0;i<500;i++){
       
        avl2.insert(data[i]);
             
      
      
      System.out.print("\n");
      for(int j=0;j<=i;j++){
             
             avl2.search(data[j]);   
             countins[i][j]=avl2.Count;
             countser[i][j]=avl2.Count2;
      }}
  
      int x = 0;
      for(int i=0;i<500;++i){
      int min = countser[i][0];
      int max = countser[i][0];
      int ave = 0;
      int z = 0;
      
         for(int j=0;j<=i;j++){
            
            if(countser[i][j]<min){min=countser[i][j];}
            if(countser[i][j]>max){max=countser[i][j];}
            ave+=countser[i][j];
            z++;
            
         }
         if(z==0){ave = ave/(1);}else{ave = ave/z;}
         need[x][0]= min+"";
         need[x][1]= ave+"";
         need[x][2]= max+"";
         x++;
      }
      try{
  FileWriter fwrite = new FileWriter("Data",true);
  for(int i=0;i<500;i++){
  fwrite.write(need[i][0]+", "+need[i][1]+", "+need[i][2]+", "+countins[i][0]+"\n");
  }
  
 
  
  fwrite.close();
  
  
  }catch(Exception e){
  System.out.println(e.getMessage());
  }
}


     /*
      * Main Method
      *@parameter args in type of String Array     
      */
   
    public static void main(String[] args){
     AVLTree avl = new AVLTree();
    
    //reads and stores file
     try{
      Scanner sc = new Scanner(new File("cleaned_data.csv"));
     sc.nextLine();
      
      
      while(sc.hasNextLine()){
        String[]row = sc.nextLine().split(",");
       
         avl.insert(row[0]+", "+row[1]+", "+row[3]);
       
      }
      sc.close();
 }catch(Exception e){
  System.out.println(e.getMessage());
  }
   //for args array
   if(args.length<1){
   avl.inOrder();
   }else if(args[0].equals("FileWithSearchKeys.txt")){
    try {Scanner sc2 = new Scanner(new File(args[0]));
    
    while(sc2.hasNextLine()){
         String[]row = sc2.nextLine().split(",");
                 avl.search(row[0]);
          avl.writeToFile();
      }
      sc2.close();

      }catch(Exception e){
  System.out.println(e.getMessage());
  }
   }else{
   AVLTree.Node nd = avl.search(args[0]);
   if(nd==null){
      System.out.println("Date/time not found");
   }else{
      System.out.println(nd.key);
      avl.writeToFile();
   }
     
      }
      
     
  
     //testing();
    
  

   
      
    
    
    }
    }
    