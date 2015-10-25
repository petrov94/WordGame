import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class WordGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char arr[][]= new char[][]{
				  { 'i', 'i', 'v', 'a', 'n'},
				  { 'n', 'v', 'h', 'n', 'h'},
				  { 'a', 'h', 'a', 'h', 'h'},
				  { 'v', 'v', 'h', 'n', 'h'},
				  { 'i', 'v', 'a', 'n', 'n'}
				};
		try{
			int choice = 0;
			do{
			int rows,cols;
			Scanner sc = new Scanner(System.in);
			System.out.println("Make choice: 1)Enter a valid chars to make a table ");
			System.out.println("2)Generate automatically table");
			System.out.println("3)Show a static table");
			System.out.println("Enter -1 for quit");
			choice = sc.nextInt();
			switch (choice) {
            case 1:  System.out.println("Enter the rows by 100");
            		 rows= sc.nextInt();	
            		 System.out.println("Enter the colums by 100");
            		 cols=sc.nextInt();
            		 make_a_table(rows,cols);
                     break;
            case 2:  Generate();
                     break;
            case 3:  check(arr,"ivan",5,5);
            		 printf_table(arr,"ivan",5,5);
                     break;
            default: System.out.println("quit");
            		 break;
			}}while(choice!=-1);
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Too big array! Check the number of rows and columns as it exeeds the maximum!");
		}
	}
	
	public static void printf_table(char[][] arr, String word, int rowsNum, int colsNum){
		int count = check(arr, word, rowsNum, colsNum);
		if (count > 0) {
			for (int i = 0; i < rowsNum; i++) {
				for (int j = 0; j < colsNum; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println(count);
		}
	}
	
	public static void make_a_table (int rowsNum, int colsNum){
		char[][] arr = new char[100][100];
		String word = null;
		try{
			boolean flagW=true;
			while(flagW){
		Scanner in = new Scanner(System.in);
		System.out.println("Please Enter a valid word");
		word = in.nextLine();
		for(int i=0;i<word.length();i++){
			if(word.charAt(i)< 'a' ||word.charAt(i) > 'z'){
				flagW=true;
				break;
			}
			else flagW=false;
				
		}
		System.out.println("Enter the charecters");
		Scanner sc= new Scanner(System.in);
		for (int i = 0; i < rowsNum; i++) {
			for (int j = 0; j < colsNum; j++) {
				char ch;
				 try {
					ch = (char) System.in.read();
					if(ch < 'a' || ch > 'z') {
						arr [i][j]=ch;
				}} catch (IOException e) {
					e.printStackTrace();
				}
				
			  }
			}
			int count = check(arr, word, rowsNum, colsNum);
				System.out.println(count);
				for (int i = 0; i < rowsNum; i++) {
					for (int j = 0; j < colsNum; j++) {
						System.out.print(arr[i][j] + " ");
						}
				}
				
			}
			}
		catch(InputMismatchException e){
			System.err.println("you haven't entered a valid char");
		}
	}
	public static int check(char[][] ch, String word, int rowsNum, int colsNum) {
		String[] rows = new String[rowsNum * 2];
		String[] cols = new String[colsNum * 2];
		String diagonal = "";
		String diagonal2 = "";
		int counter = 0;
		String revWord = "";
		for (int i = word.length() - 1; i >= 0; i--) {
			revWord += word.charAt(i);
		}
		boolean flagRow=true;
		boolean flagCol=true;
		boolean flagDiag1=true;
		boolean flagDiag2=true;
		for (int i = 0; i < rowsNum; i++) {
			flagRow=true;
			flagCol=true;
			for (int j = 0; j < colsNum; j++) {
				if(flagRow){
				rows[i] += ch[i][j];
				if (rows[i].contains(word) || rows[i].contains(revWord)) {
					counter++;
					flagRow=false;
				}
				}
				if(flagCol){
				cols[i] += ch[j][i];
				if (cols[i].contains(word) || cols[i].contains(revWord)) {
					counter++;
					flagCol=false;
				}
				}
				
				if(flagDiag1==true){
				if (i == j) {
					diagonal += ch[i][j];
				}
				if (diagonal.contains(word) || diagonal.contains(revWord)) {
					counter++;
					flagDiag1= false;
					
				}
				}
					if(flagDiag2){			
				if (i + j + 1 == colsNum) {
					diagonal2 += ch[i][j];
				}
				if (diagonal2.contains(word) || diagonal2.contains(revWord)) {
					counter++;
					flagDiag2 = false;
					}
					}
			}
			}
						
		return counter;
	}

	
	public static void Generate() {
		char[][] arr = new char[100][100];
		int rows = 6, cols = 5;
		char ch = 0;
		boolean flag = true;
		String word = null;
		try{
			boolean flagWord=true;
			while(flagWord){
		Scanner in = new Scanner(System.in);
		System.out.println("Please Enter a valid word");
		word = in.nextLine();
		for(int i=0;i<word.length();i++){
			if(word.charAt(i)< 'a' ||word.charAt(i) > 'z'){
				flagWord=true;
				break;
			}
			else flagWord=false;
				
		}
			}
				
		while (flag) {
			Random rand = new Random();
			if (flag) {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						while (ch < 'a' || ch > 'z') {
							ch = (char) (96 + rand.nextInt(26));
						}
						arr[i][j] = ch;
						ch = 0;
					}

				}

				int count = check(arr, word, rows, cols);
				if (count > 0) {
					for (int i = 0; i < rows; i++) {
						for (int j = 0; j < cols; j++) {
							System.out.print(arr[i][j] + " ");
						}
						System.out.println();
					}
					System.out.println(count);
					flag = false;
				}
			}
		}
	}catch(InputMismatchException e){
		System.err.println("you havent entered a valid char");
	}

	}
}