import java.util.Scanner;
import java.util.Vector;

class Main {
	
	public static Vector result;
	public static boolean flag = false;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int instances = scanner.nextInt();
		for (int i = 0; i < instances; i++) {
			flag = false;
			int bignumber = scanner.nextInt();
			Node root = new Node(0, null, null);
			result = new Vector();
			for (int j = 0; j < bignumber; j++) {
				int k = scanner.nextInt();
				insert(k, root);
			}
			if (!flag){
				visit(root);
				String fuu = "";
				for (Object e : result.toArray()){
					fuu += e + " ";
				}
				System.out.println(fuu.substring(0,fuu.length() - 1));
				result.clear();
			}
			else {System.out.println("DUPLICATE-KEY");}
			
		}
		scanner.close();
	}
	
	public static void insert(int k, Node root){
		if (root.value == 0) { 
			root.value = k; 
			root.links = new Node(0, null, null); 
			root.rechts = new Node(0, null, null);  
		}
		else{
			if (!flag){
				if (k == root.value) {
					flag = true; 
					//System.out.println("DUPLICATE-KEY"); 
					return;
				}
			} 
			if (k < root.value) { insert(k, root.links); }
			else { insert(k, root.rechts); }
		}
	}
	
	public static int[] visit(Node root){
		int[] ret = new int[2];
		if (root.links.value == 0) {
			if (root.rechts.value == 0){
				result.add(root.value);
				result.add(0);
				result.add(0);
				ret[0] = 0; ret[1] = 0;
				return ret;
			} 
			else { 
				int[] r = visit(root.rechts);
				ret[1] += r[1] + 1; ret[1] += r[0];
				result.add(root.value);
				result.add(0);
				result.add(ret[1]);
				return ret;
				}
		} 
		else {
			if (root.rechts.value == 0){
				int[] l = visit(root.links); 
				ret[0] += l[0] + 1; ret[0] += l[1];
				result.add(root.value);
				result.add(ret[0]);
				result.add(ret[1]);				
				return ret;
			}
			else{
				int[] l = visit(root.links);
				ret[0] += l[0] + 1; ret[0] += l[1];
				int[] r = visit(root.rechts);
				ret[1] += r[0]; ret[1] += r[1] + 1;
				result.add(root.value);
				result.add(ret[0]);
				result.add(ret[1]);
				return ret;
			}
		}
	}
	
	private static final class Node {
		public int value;
		public Node links;
		public Node rechts;
		
		public Node(int i, Node l, Node r){
			value = i;
			links = l;
			rechts = r;
		}
	}

}
