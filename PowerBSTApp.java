/**
* PowerBstApp.java - class is for reading csv file and storing it in BST format then with search method it finds specific date from data that is stored.
* @author ASLBIL001
* @version 1.0
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;



public class PowerBSTApp { 

    static class BinarySearchTree {
  int Count=0;
  int Count2=0;
  
    /* Class containing left and right child of current node and key value*/
    public class Node {
        String key;
        Node left, right;
   
  /*
  * Node default constructor 
  * @parameter item in type of String
  */
  
        public Node(String item) {
            key = item;
            left = right = null;
        }
    }
  
    // Root of BST
    Node root;
  
    /*
  * Constructor for BinarySearchTree()
  */
    // 
    BinarySearchTree() { 
        root = null; 
    }
     
   /*
  * this method calls insert(root, key) mainly
  * @parameter key in type of String 
  */
    
     public  void insert(String key) { 
       root = insertRec(root, key); 
    } 
      
      /*
  *  A recursive function to insert a new key in BST
  * @parameter root in type of Node
  * @parameter key in type of String
  */
    
    public Node insertRec(Node root, String key) { 
        
        /* If the tree is empty, return a new node */
        if (root == null) { 
            root = new Node(key); 
            return root; 
        } 
  
        /* Otherwise, recur down the tree */
        int k = key.compareTo(root.key);
        Count++;
        
        if (k < 0) 
            root.left = insertRec(root.left, key); 
        else if (k > 0) 
            root.right = insertRec(root.right, key); 
  
        /* return the (unchanged) node pointer */
        return root;  
    }
    
     /*
  * Mainly calls search(Node root, String key) method 
  * @parameter key in type of String
  * @return a Node type
  */
  
    public Node search(String key){
    Count2 = 0;
    return search(root,key);
    }
    
     /*
  * Searches through Tree and finds given String Parameter String
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
  * Writes number of comparisons made to a file
  */
  
   void writeToFile(){
    
  try{
  FileWriter fwrite = new FileWriter("countBST.txt",true);
  fwrite.write(Count+" "+Count2+"\n");
  fwrite.close();
  
  
  }catch(Exception e){
  System.out.println(e.getMessage());
  }
     

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
  * Adds insert and Search Count numbers
  * @return a int type
  */
  
  int getTotalCount(){
   return Count +Count2;
  }

}

 /*
  * This is used for testing efficiency of the BST
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
      
         BinarySearchTree bst2 = new BinarySearchTree();
      for(int i=0;i<500;i++){
       
        bst2.insert(data[i]);
             
      
      
      System.out.print("\n");
      for(int j=0;j<=i;j++){
             
             bst2.search(data[j]);   
             countins[i][j]=bst2.Count;
             countser[i][j]=bst2.Count2;

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
  FileWriter fwrite = new FileWriter("Data2",true);
  for(int i=0;i<500;i++){
  fwrite.write(need[i][0]+", "+need[i][1]+", "+need[i][2]+", "+countins[i][0] +"\n");
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
     BinarySearchTree bst = new BinarySearchTree();
   
    //reads and stores file
     try{
      Scanner sc = new Scanner(new File("cleaned_data.csv"));
      sc.nextLine();
      
      
      while(sc.hasNextLine()){
         String[]row = sc.nextLine().split(",");
         
         bst.insert(row[0]+", "+row[1]+", "+row[3]);
         
      }
      sc.close();
 }catch(Exception e){
  System.out.println(e.getMessage());
  }
   //for args array
   if(args.length<1){
   bst.inOrder();
   }else if(args[0].equals("FileWithSearchKeys.txt")){
    try {Scanner sc2 = new Scanner(new File(args[0]));
    
    while(sc2.hasNextLine()){
         String[]row = sc2.nextLine().split(",");
                 bst.search(row[0]);
          bst.writeToFile();
      }
      sc2.close();

      }catch(Exception e){
  System.out.println(e.getMessage());
  }
   }
   else{
   BinarySearchTree.Node nd = bst.search(args[0]);
   if(nd==null){
      System.out.println("Date/time not found");
   }
   else{
      System.out.println(nd.key);
   }
     
      }
  
   //   testing();
 
    
    
    
    
    }
    
    
    }