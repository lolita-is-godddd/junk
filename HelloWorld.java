import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
public class Main {
     public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);
        HashMap<String, String> throwableMap = new HashMap<>();
        throwableMap.put("ClassCastException", "excpt@classCastFailed"); // excpt@invalidCmp?
        throwableMap.put("NullPointerException", "excpt@accessViolation");
        throwableMap.put("OutOfMemoryError", "excpt@noMemory");
        throwableMap.put("ArrayIndexOutOfBoundsException", "excpt@dbgArrayIdxOutOfRange");
        throwableMap.put("StackOverflowError", "excpt@stackOverflow");
        throwableMap.put("AssertionError", "excpt@dbgAssertFailed");
        
        String className = "[A-Z][A-Za-z\\d_]*";
        String throwableCheck = "(?:java\\.lang\\.)?(" + className + "(Error|Exception))";
        String multipileLineCommentCheck = "(?m)(?:/\\*\\*?)(.*)(?:\\*/)";
        String intCheck = "\\d+";
        String doubleValueCheck = intCheck + "\\." + intCheck;
        while (scan.hasNext()) {
            String line = scan.nextLine().trim();
            Pattern p = Pattern.compile(throwableCheck);
            Matcher m = p.matcher(line);
            boolean b = m.matches();
            if (b) {
                String matched = m.group(1);
                // System.out.println("matched: " + matched);
                line = line.replaceAll("throw new " + throwableCheck + "\\(.*\\)?" ,"throw " + throwableMap.get(matched));
            }
            // TODO java.util, java.langの書き換えを関数化する
            line = line.replaceAll(multipileLineCommentCheck, "{$1}");
            line = line.replaceAll("Math\\.PI", "lib@pi");
            line = line.replaceAll("Math\\.E", "lib@e");
            line = line.replaceAll("Math\\.sqrt\\(" + doubleValueCheck +"\\)", "lib@sqrt($1)");
            line = line.replaceAll("Integer\\.MAX_VALUE", "lib@intMax");
            line = line.replaceAll("Integer\\.MIN_VALUE", "lib@intMin");
            line = line.replaceAll("(?:java\\.util\\.)?UUID.randomUUID\\(\\).toString\\(\\)","lib@rndUuid");
            line = line.replaceAll("(?:java\\.lang\\.)?System\\.exit\\((" + intCheck + ")\\)", "lib@exitCode($1)");
            // java.lang.String => kuin.array<kuin.array<char>>
            // line = line.replaceAll()
            
            // java.util.List => kuin.list
            // java.util.List#size => kuin.list#^
            
            // java.native.byte => kuin.bit8
            // java.native.short => kuin.bit16
            // java.native.int => kuin.bit32
            // java.native.long => kuin.bit64
            
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
