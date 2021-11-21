import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class WordPuzzle {
    int row,col;
    String[][] grid ;
    int longestWord = 0;
    QuadraticProbingHashTable<String> hashTable = new QuadraticProbingHashTable<>();
    HashSet<String> prefixSet = new HashSet<>();
    
    public void printGrid(int r, int c, String[][] puz){
        row=r;
        col=c;
        grid=puz;
        System.out.println("Printing the grid of the puzzle: ");
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
    public void readDictionary(int algType){
        Scanner scan=null;
        try{
            scan= new Scanner(new File("src/dictionary.txt"));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        while(scan.hasNext()) {
            String nextWord = scan.nextLine();
            if (nextWord.length() > 1) {
                if (longestWord < nextWord.length()) {
                    longestWord = nextWord.length();
                }
                if (algType == 1) {
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < nextWord.length(); i++) {
                        str.append(nextWord.charAt(i));
                        prefixSet.add(str.toString());
                    }
                }
                hashTable.insert(nextWord);
            }
        }
    }
    public TreeSet<String> findWords(int algType){
        TreeSet<String> set = new TreeSet<>();
        for(int i=0; i < row; i++) { //for each row
            StringBuilder wordBuilder;
            //forward string combinations down a row
            for (int j=0;j<col;j++) {
                wordBuilder = new StringBuilder();
                for (int k = j; k< col; k++) {
                    wordBuilder.append(grid[i][k]);
                    if((algType==1 && !prefixSet.contains(wordBuilder.toString())) || wordBuilder.length()>longestWord ){
                        wordBuilder.setLength(0);
                        break;
                    }
                    else if(hashTable.contains(wordBuilder.toString())){
                        set.add(wordBuilder.toString());
                    }
                }
            }
            //reverse string combinations up the row
            for (int j=col-1;j>=0;j--) {
                wordBuilder = new StringBuilder();
                for (int k = j; k >= 0; k--) {
                    wordBuilder.append(grid[i][k]);
                    if((algType==1 && !prefixSet.contains(wordBuilder.toString())) || wordBuilder.length()>longestWord ){
                        wordBuilder.setLength(0);
                        break;
                    }
                    else if(hashTable.contains(wordBuilder.toString())){
                        set.add(wordBuilder.toString());
                    }
                }
            }
        }
        for(int i=0; i < col; i++) { //for each column
            StringBuilder wordBuilder;
            //forward string combinations down a column
            for (int j=0;j<row;j++) {
                wordBuilder = new StringBuilder();
                for (int k = j; k< row; k++) {
                    wordBuilder.append(grid[k][i]);
                    if((algType==1 && !prefixSet.contains(wordBuilder.toString())) || wordBuilder.length()>longestWord ){
                        wordBuilder.setLength(0);
                        break;
                    }
                    else if(hashTable.contains(wordBuilder.toString())){
                        set.add(wordBuilder.toString());
                    }
                }
            }
            //reverse string combinations up the column
            for (int j=row-1;j>=0;j--) {
                wordBuilder = new StringBuilder();
                for (int k = j; k >= 0; k--) {
                    wordBuilder.append(grid[k][i]);
                    if((algType==1 && !prefixSet.contains(wordBuilder.toString())) || wordBuilder.length()>longestWord ){
                        wordBuilder.setLength(0);
                        break;
                    }
                    else if(hashTable.contains(wordBuilder.toString())){
                        set.add(wordBuilder.toString());
                    }
                }
            }
        }
        //top left to bottom right
        for(int i=0;i<row;i++){
            StringBuilder wordBuilder;
            for(int j=0;j<col;j++){
                wordBuilder = new StringBuilder();
                int r=i,c =j;
                for(int k=0;k<longestWord;k++){
                    if(r<0||r==row||c<0|| c==col){
                        break;
                    }
                    wordBuilder.append(grid[r++][c++]);
                    if((algType==1 && !prefixSet.contains(wordBuilder.toString())) || wordBuilder.length()>longestWord ){
                        wordBuilder.setLength(0);
                        break;
                    }
                    else if(hashTable.contains(wordBuilder.toString())){
                        set.add(wordBuilder.toString());
                    }
                }
            }
        }
        //bottom right to top left
        for(int i=row-1;i>=0;i--){
            StringBuilder wordBuilder;
            for(int j=col-1;j>=0;j--){
                wordBuilder = new StringBuilder();
                int r=i,c =j;
                for(int k=0;k<longestWord;k++){
                    if(r<0||r==row||c<0|| c==col){
                        break;
                    }
                    wordBuilder.append(grid[r--][c--]);
                    if((algType==1 && !prefixSet.contains(wordBuilder.toString())) || wordBuilder.length()>longestWord ){
                        wordBuilder.setLength(0);
                        break;
                    }
                    else if(hashTable.contains(wordBuilder.toString())){
                        set.add(wordBuilder.toString());
                    }
                }
            }
        }
        //top right to bottom left
        for(int i=0;i<row;i++){
            StringBuilder wordBuilder;
            for(int j=col-1;j>=0;j--){
                wordBuilder = new StringBuilder();
                int r=i,c =j;
                for(int k=0;k<longestWord;k++){
                    if(r<0||r==row||c<0|| c==col){
                        break;
                    }
                    wordBuilder.append(grid[r++][c--]);
                    if((algType==1 && !prefixSet.contains(wordBuilder.toString())) || wordBuilder.length()>longestWord ){
                        wordBuilder.setLength(0);
                        break;
                    }
                    else if(hashTable.contains(wordBuilder.toString())){
                        set.add(wordBuilder.toString());
                    }
                }
            }
        }
        for(int i=row-1;i>=0;i--){
            StringBuilder wordBuilder;
            for(int j=0;j<=col;j++){
                wordBuilder = new StringBuilder();
                //bottom left to top right
                int r=i,c =j;
                for(int k=0;k<longestWord;k++){
                    if(r<0||r==row||c<0|| c==col){
                        break;
                    }
                    wordBuilder.append(grid[r--][c++]);
                    if((algType==1 && !prefixSet.contains(wordBuilder.toString())) || wordBuilder.length()>longestWord ){
                        wordBuilder.setLength(0);
                        break;
                    }
                    else if(hashTable.contains(wordBuilder.toString())){
                        set.add(wordBuilder.toString());
                    }
                }
            }
        }
        return set;
    }

    public static void main(String[] args){
        WordPuzzle wp = new WordPuzzle();
        int r,c;
        int algType = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows for the grid");
        r = sc.nextInt();
        System.out.println("Enter the number of columns for the grid");
        c = sc.nextInt();
        if(r<1 || c <1){
            System.out.println("Row column length can not be 0 or negative.");
            return;
        }
        System.out.println("Enter 0 for standard search and 1 for optimized search.");
        algType = sc.nextInt();
        if(algType!=0&&algType!=1){
            System.out.println("Selection is invalid. Exiting.");
            return;
        }
        String[][] puz = new String[r][c];
        Random random = new Random();
        for(int i =0;i<r;i++){
            for(int j=0;j<c;j++){
                puz[i][j]=Character.toString((char)(random.nextInt(26)+97));
            }
        }
        wp.printGrid(r,c,puz);
        wp.readDictionary(algType);
        long start=System.nanoTime();
        TreeSet<String> set = wp.findWords(algType);
        long stop=System.nanoTime();
        System.out.println( "Time taken to find words: " + (stop-start)/1000000 + "ms" );
        System.out.println("Total words found are: " + set.size());
        System.out.println("Found words are mentioned below: ");
        for(String foundWord : set){
            System.out.println(foundWord);
        }
    }
}
