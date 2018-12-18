import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
public class Main {
     public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);
        HashMap<String, String> map = new HashMap<>();
        map.put("ClassCastException", "excpt@classCastFailed"); // excpt@invalidCmp
        map.put("NullPointerException", "excpt@accessViolation");
        map.put("OutOfMemoryError", "excpt@noMemory");
        map.put("ArrayIndexOutOfBoundsException", "excpt@dbgArrayIdxOutOfRange");
        map.put("StackOverflowError", "excpt@stackOverflow");
        map.put("AssertionError", "excpt@dbgAssertFailed");
        
        String throwableCheck = "(?:java\\.lang\\.)?([A-Z][A-Za-z]+(Error|Exception))";
        while (scan.hasNext()) {
            String line = scan.nextLine().trim();
            Pattern p = Pattern.compile(".*?" + throwableCheck + ".*");
            Matcher m = p.matcher(line);
            boolean b = m.matches();
            if (b) {
                String matched = m.group(1);
                // System.out.println("matched: " + matched);
                line = line.replaceAll("throw new " + throwableCheck + "\\(.*\\)?" ,"throw " + map.get(matched));
            }
            
            // java.lang.String => kuin.array<kuin.array<char>>
            //line = line.replaceAll()
            
            // java.util.List => kuin.list
            // java.util.List#size => kuin.list#^
            
            // java.native.byte => kuin.b8
            // java.native.short => kuin.b16
            // java.native.int => kuin.b32
            // java.native.long => kuin.b64
            
            // java.native.array#<init> => kuin.array#<init>
            // java.native.array#<init>(int) => kuin.array#<init>(int)
            
            // java.native.boolean => kuin.bool
            
            // java.lang.Object#<init> => kuin.class# #
            
            // java.util.Queue => kuin.queue
            // java.util.Queue#pop => kuin.queue#get
            // java.util.Queue#peek => kuin.queue#peek
            
            // java.util.Map => kuin.dict
            // java.util.Map#put => kuin.dict#add
            // java.util.Map#keySet => kuin.dict#toArrayKey (sorted asc)
            // java.util.Map#values => kuin.dict#toArrayValue (sorted asc)
            
            // java.util.Arrays.fill<T extends java.native.premitive>(java.native.array<T>, T) => kuin.array#fill(kuin.integer)
            // java.util.Arrays.copyOfRange(java.native.array<java.native.premitive>, java.native.int, java.native.int) => kuin.array#sub<T extends kuin.integer>(T, T)
            // java.util.Arrays.parallelSort(java.native.array<java.native.premitive>) => kuin.array#sort()
            // java.util.Arrays.sort(java.native.array<java.native.premitive>) => kuin.array#sort()
            // java.util.Arrays.parallelSort<T extends java.lang.Comparable<? super T>>(java.native.array<T>) => kuin.array#sort()
            // java.util.Arrays.binarySearch<T extends java.native.premitive>(java.native.array<T>, T) => kuin.array#findBin(T)
            
            // java.lang.String#toUpperCase() => kuin.array<kuin.array<char>>#upper()
            // java.lang.String#toLowerCase() => kuin.array<kuin.array<char>>#lower()
            // java.lang.String#trim() => kuin.array<kuin.array<char>>#trim()
            System.out.println(line);
        }
     }
}
